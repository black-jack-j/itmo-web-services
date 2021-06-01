package org.blackjackj.wslab.cli.subcommands;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.blackjackj.wslab.service.ProductWebService;
import org.blackjackj.wslab.service.WslabErrorDecoder;

public interface ProductServiceProvider {

    default ProductWebService get() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new WslabErrorDecoder())
                .target(ProductWebService.class, "http://localhost:8080");
    }

}
