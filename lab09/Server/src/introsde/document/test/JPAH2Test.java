package introsde.document.test;

import static org.junit.Assert.*;
import introsde.document.model.Person;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPAH2Test {

		  @Test
		  public void readPerson() {
		    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		    assertEquals("Table has no entity", 0, list.size()); 
		  }

		  @Test
		  public void addPerson() {
		    // Arrange
		    Person at = new Person();
		    at.setName("Pinco");          
		    // Act
		    tx.begin();
		    em.persist(at);
		    tx.commit();            
		    // Assert
		    assertNotNull("Id should not be null", at.getIdPerson());
		    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		    assertEquals("Table has one entities", 1, list.size()); 
		    assertEquals("Table has correct name", "Pinco", list.get(0).getName());
		    
		    //Person p = em.find(Person.class, 2);
		    Person p = list.get(1);
		    
		    em.getTransaction().begin();
		    em.remove(p);
		    em.getTransaction().commit();
		    
		    list = em.createNamedQuery("Person.findAll", Person.class).getResultList();

		    assertEquals("Table has no entity", 0, list.size()); 
		  }

		  
		  @BeforeClass
		  public static void beforeClass() {
		    emf = Persistence.createEntityManagerFactory("introsde-jpa-h2");
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
