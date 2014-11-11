package introsde.document.model;

import introsde.document.dao.LifeCoachDao;
import introsde.document.model.MeasureDefinition;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the "MeasureDefaultRange" database table.
 * 
 */
@Entity
@Table(name="MeasureDefaultRange")
@NamedQuery(name="MeasureDefaultRange.findAll", query="SELECT m FROM MeasureDefaultRange m")
@XmlRootElement
public class MeasureDefaultRange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_range")
	@TableGenerator(name="sqlite_range", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="MeasureDefaultRange")
	@Column(name="idRange")
	private int idRange;

	@Column(name="rangeName")
	private String rangeName;

	@Column(name="startValue")
	private String startValue;
	
	@Column(name="alarmLevel")
	private String alarmLevel;

	@Column(name="endValue")
	private String endValue;

	@ManyToOne
	@JoinColumn(name="idMeasureDef",referencedColumnName="idMeasureDef",insertable=true,updatable=true)
	private MeasureDefinition measureDefinition;

	public MeasureDefaultRange() {
	}

	public String getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getEndValue() {
		return this.endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public int getIdRange() {
		return this.idRange;
	}

	public void setIdRange(int idRange) {
		this.idRange = idRange;
	}

	public String getRangeName() {
		return this.rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public String getStartValue() {
		return this.startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	@XmlTransient
	public MeasureDefinition getMeasureDefinition() {
	    return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition param) {
	    this.measureDefinition = param;
	}
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 	
	public static MeasureDefaultRange getMeasureDefaultRangeById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureDefaultRange p = em.find(MeasureDefaultRange.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<MeasureDefaultRange> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<MeasureDefaultRange> list = em.createNamedQuery("MeasureDefaultRange.findAll", MeasureDefaultRange.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static MeasureDefaultRange saveMeasureDefaultRange(MeasureDefaultRange p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static MeasureDefaultRange updateMeasureDefaultRange(MeasureDefaultRange p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasureDefaultRange(MeasureDefaultRange p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
