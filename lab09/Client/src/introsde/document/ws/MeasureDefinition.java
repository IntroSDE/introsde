
package introsde.document.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measureDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measureDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMeasureDef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="measureDefaultRange" type="{http://ws.document.introsde/}measureDefaultRange" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="measureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="measureType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measureDefinition", propOrder = {
    "idMeasureDef",
    "measureDefaultRange",
    "measureName",
    "measureType"
})
public class MeasureDefinition {

    protected int idMeasureDef;
    @XmlElement(nillable = true)
    protected List<MeasureDefaultRange> measureDefaultRange;
    protected String measureName;
    protected String measureType;

    /**
     * Gets the value of the idMeasureDef property.
     * 
     */
    public int getIdMeasureDef() {
        return idMeasureDef;
    }

    /**
     * Sets the value of the idMeasureDef property.
     * 
     */
    public void setIdMeasureDef(int value) {
        this.idMeasureDef = value;
    }

    /**
     * Gets the value of the measureDefaultRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureDefaultRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureDefaultRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasureDefaultRange }
     * 
     * 
     */
    public List<MeasureDefaultRange> getMeasureDefaultRange() {
        if (measureDefaultRange == null) {
            measureDefaultRange = new ArrayList<MeasureDefaultRange>();
        }
        return this.measureDefaultRange;
    }

    /**
     * Gets the value of the measureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureName() {
        return measureName;
    }

    /**
     * Sets the value of the measureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureName(String value) {
        this.measureName = value;
    }

    /**
     * Gets the value of the measureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureType() {
        return measureType;
    }

    /**
     * Sets the value of the measureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureType(String value) {
        this.measureType = value;
    }

}
