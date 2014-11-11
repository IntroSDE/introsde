
package introsde.document.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePersonHealthProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePersonHealthProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="healthProfile" type="{http://ws.document.introsde/}lifeStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePersonHealthProfile", propOrder = {
    "personId",
    "healthProfile"
})
public class UpdatePersonHealthProfile {

    protected int personId;
    protected LifeStatus healthProfile;

    /**
     * Gets the value of the personId property.
     * 
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     * 
     */
    public void setPersonId(int value) {
        this.personId = value;
    }

    /**
     * Gets the value of the healthProfile property.
     * 
     * @return
     *     possible object is
     *     {@link LifeStatus }
     *     
     */
    public LifeStatus getHealthProfile() {
        return healthProfile;
    }

    /**
     * Sets the value of the healthProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeStatus }
     *     
     */
    public void setHealthProfile(LifeStatus value) {
        this.healthProfile = value;
    }

}
