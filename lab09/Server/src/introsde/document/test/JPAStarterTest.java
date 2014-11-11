package introsde.document.test;

import static org.junit.Assert.*;
import introsde.document.model.LifeStatus;
import introsde.document.model.MeasureDefinition;
import introsde.document.model.Person;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPAStarterTest {

	@Test
	public void readPerson() {
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
				.getResultList();
		assertEquals("Table has one entity", 1, list.size());
		assertEquals("Table has correct name", "Chuck", list.get(0).getName());
	}

	// test for adding person without using the DAO object, but isntead using the entity manager 
	// created in the testing unit by the beforetest method
	@Test
	public void addPerson() {
		Person at = new Person();
		at.setName("Pinco");
		tx.begin();
		em.persist(at);
		tx.commit();
		assertNotNull("Id should not be null", at.getIdPerson());
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
				.getResultList();
		assertEquals("Table has two entities", 2, list.size());
		assertEquals("Table has correct name", "Pinco", list.get(1).getName());
		Person p = list.get(1);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		list = em.createNamedQuery("Person.findAll", Person.class)
				.getResultList();
		assertEquals("Table has two entities", 1, list.size());
		assertEquals("Table has correct name", "Chuck", list.get(0).getName());
	}

	// same adding person test, but using the DAO object
	@Test
	public void addPersonWithDao() {
		Person p = new Person();
		p.setName("Pinco");
		p.setLastname("Pallino");
		Calendar c = Calendar.getInstance();
		c.set(1984, 6, 21);
		p.setBirthdate(c.getTime());
		Person.savePerson(p);
		assertNotNull("Id should not be null", p.getIdPerson());
		List<Person> list = Person.getAll();
		assertEquals("Table has two entities", 2, list.size());
		assertEquals("Table has correct name", "Pinco", list.get(1).getName());
		Person created = list.get(1);
		Person.removePerson(created);
		list = Person.getAll();
		assertEquals("Table has two entities", 1, list.size());
		assertEquals("Table has correct name", "Chuck", list.get(0).getName());
	}

	@Test
	public void testLifeStatusListDao() {
		List<LifeStatus> mList = LifeStatus.getAll();
		assertEquals("Only one LifeStatus in DB", 1, mList.size());
	}

	@Test
	public void testLifeStatusPersonRelationship() {
		// setting weight for an existing person with existing measures
		Person chuck = Person.getPersonById(1);
		assertEquals("Chuck norris is here", "Chuck", chuck.getName());
		// add a new measure value to the list of measurements of chuck
		MeasureDefinition md = MeasureDefinition.getMeasureDefinitionById(1);
		LifeStatus l = new LifeStatus();
		l.setMeasureDefinition(md);
		l.setValue("85");
		chuck.getLifeStatus().add(l);
		chuck = Person.updatePerson(chuck);
		assertEquals("Person should have now two measures", 2, chuck
				.getLifeStatus().size());
		l = chuck.getLifeStatus().get(1);
		assertNotNull("LifeStatus measure was created", l.getIdMeasure());
		chuck.getLifeStatus().remove(1);
		Person.updatePerson(chuck);
		assertEquals("Person should have now just one measure", 1, chuck
				.getLifeStatus().size());
	}

	@BeforeClass
	public static void beforeClass() {
		emf = Persistence.createEntityManagerFactory("introsde-jpa");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void afterClass() {
		em.close();
		emf.close();
	}

	@Before
	public void before() {
		tx = em.getTransaction();
	}

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private EntityTransaction tx;
}
