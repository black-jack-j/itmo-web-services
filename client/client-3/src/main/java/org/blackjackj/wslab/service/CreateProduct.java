
package org.blackjackj.wslab.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTO" type="{http://service.wslab.blackjackj.org/}productCreateTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createProduct", propOrder = {
    "createTO"
})
public class CreateProduct {

    protected ProductCreateTO createTO;

    /**
     * Gets the value of the createTO property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCreateTO }
     *     
     */
    public ProductCreateTO getCreateTO() {
        return createTO;
    }

    /**
     * Sets the value of the createTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCreateTO }
     *     
     */
    public void setCreateTO(ProductCreateTO value) {
        this.createTO = value;
    }

}
