package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.ProductWebService;

import java.net.MalformedURLException;
import java.net.URL;

public interface ProductServiceProvider {

    default ProductService get() {
        URL serviceUrl = null;
        try {
            serviceUrl = new URL("http://localhost:8080/wslab-server/ProductWebService?wsdl");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Can't access web service");
        }
        return new ProductWebService(serviceUrl).getProductServicePort();
    }

}
