package org.blackjackj.wslab.cli.subcommands;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.blackjackj.wslab.service.ProductWebService;

public interface ProductServiceProvider {

    default ProductWebService get() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(ProductWebService.class, "http://localhost:8080");
    }

    default ProductWebService getWithAuth(String userName, String password) {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(new BasicAuthRequestInterceptor(userName, password))
                .target(ProductWebService.class, "http://localhost:8080");
    }

}
