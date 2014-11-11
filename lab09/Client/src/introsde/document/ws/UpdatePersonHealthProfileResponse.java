
package introsde.document.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePersonHealthProfileResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePersonHealthProfileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hpId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePersonHealthProfileResponse", propOrder = {
    "hpId"
})
public class UpdatePersonHealthProfileResponse {

    protected int hpId;

    /**
     * Gets the value of the hpId property.
     * 
     */
    public int getHpId() {
        return hpId;
    }

    /**
     * Sets the value of the hpId property.
     * 
     */
    public void setHpId(int value) {
        this.hpId = value;
    }

}
