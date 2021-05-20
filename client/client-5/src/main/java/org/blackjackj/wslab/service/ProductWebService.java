package org.blackjackj.wslab.service;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.blackjackj.wslab.transport.ProductCreateTO;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;
import org.blackjackj.wslab.transport.ProductUpdateTO;

import java.util.List;

public interface ProductWebService {

    @RequestLine("GET /api/product")
    @Headers("Content-type: application/json")
    List<ProductTO> searchForProduct(@QueryMap ProductSearchTO productSearchTO);

    @RequestLine("POST /api/product")
    @Headers("Content-type: application/json")
    ProductTO createProduct(@QueryMap ProductCreateTO productCreateTO);

    @RequestLine("PUT /api/product/{id}")
    @Headers("Content-type: application/json")
    ProductTO updateProduct(@Param("id") Long id, @QueryMap ProductUpdateTO productUpdateTO);

    @RequestLine("DELETE /api/product/{id}")
    void deleteProduct(@Param("id") Long id);

}
