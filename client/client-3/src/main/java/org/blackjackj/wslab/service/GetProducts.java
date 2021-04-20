
package org.blackjackj.wslab.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProducts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProducts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchTO" type="{http://service.wslab.blackjackj.org/}productSearchTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProducts", propOrder = {
    "searchTO"
})
public class GetProducts {

    protected ProductSearchTO searchTO;

    /**
     * Gets the value of the searchTO property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSearchTO }
     *     
     */
    public ProductSearchTO getSearchTO() {
        return searchTO;
    }

    /**
     * Sets the value of the searchTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSearchTO }
     *     
     */
    public void setSearchTO(ProductSearchTO value) {
        this.searchTO = value;
    }

}
