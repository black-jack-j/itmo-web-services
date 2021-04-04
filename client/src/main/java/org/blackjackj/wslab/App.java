package org.blackjackj.wslab;

import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.ProductWebService;

import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main( String[] args ) throws MalformedURLException {
        URL serviceUrl = new URL("http://localhost:8080/ProductService?wsdl");
        ProductService productWebService = new ProductWebService(serviceUrl).getProductServicePort();
        productWebService.getProducts(null, null, null, null, null).forEach(System.out::println);
    }
}
