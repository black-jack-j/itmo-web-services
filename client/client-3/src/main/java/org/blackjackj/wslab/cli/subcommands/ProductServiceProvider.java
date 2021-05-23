package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.ProductWebService;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    default ProductService getWithAuth(String username, String password) {
        Map<String, Object> requestContext = new HashMap<>();
        requestContext.put("Username", Collections.singletonList(username));
        requestContext.put("Password", Collections.singletonList(password));
        ProductService service = get();

        ((BindingProvider) service).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, requestContext);

        return service;
    }

}
