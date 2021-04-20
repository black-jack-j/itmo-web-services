
package org.blackjackj.wslab.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateTO" type="{http://service.wslab.blackjackj.org/}productUpdateTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateProduct", propOrder = {
    "updateTO"
})
public class UpdateProduct {

    protected ProductUpdateTO updateTO;

    /**
     * Gets the value of the updateTO property.
     * 
     * @return
     *     possible object is
     *     {@link ProductUpdateTO }
     *     
     */
    public ProductUpdateTO getUpdateTO() {
        return updateTO;
    }

    /**
     * Sets the value of the updateTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductUpdateTO }
     *     
     */
    public void setUpdateTO(ProductUpdateTO value) {
        this.updateTO = value;
    }

}
