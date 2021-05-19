package org.blackjackj.wslab.service;

import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;

import java.util.List;

public interface ProductWebService {

    @RequestLine("GET /api/product")
    @Headers("Content-type: application/json")
    List<ProductTO> searchForProduct(@QueryMap ProductSearchTO productSearchTO);

}
