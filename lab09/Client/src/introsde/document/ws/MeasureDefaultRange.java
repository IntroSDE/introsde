
package introsde.document.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measureDefaultRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="measureDefaultRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alarmLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idRange" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rangeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measureDefaultRange", propOrder = {
    "alarmLevel",
    "endValue",
    "idRange",
    "rangeName",
    "startValue"
})
public class MeasureDefaultRange {

    protected String alarmLevel;
    protected String endValue;
    protected int idRange;
    protected String rangeName;
    protected String startValue;

    /**
     * Gets the value of the alarmLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmLevel() {
        return alarmLevel;
    }

    /**
     * Sets the value of the alarmLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmLevel(String value) {
        this.alarmLevel = value;
    }

    /**
     * Gets the value of the endValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndValue() {
        return endValue;
    }

    /**
     * Sets the value of the endValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndValue(String value) {
        this.endValue = value;
    }

    /**
     * Gets the value of the idRange property.
     * 
     */
    public int getIdRange() {
        return idRange;
    }

    /**
     * Sets the value of the idRange property.
     * 
     */
    public void setIdRange(int value) {
        this.idRange = value;
    }

    /**
     * Gets the value of the rangeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeName() {
        return rangeName;
    }

    /**
     * Sets the value of the rangeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeName(String value) {
        this.rangeName = value;
    }

    /**
     * Gets the value of the startValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartValue() {
        return startValue;
    }

    /**
     * Sets the value of the startValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartValue(String value) {
        this.startValue = value;
    }

}
