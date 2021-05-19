package org.blackjackj.wslab.lab4.controllers;

import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.lab4.services.ProductService;
import org.blackjackj.wslab.lab4.util.ProductTOUtils;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductTO>> searchProducts(@RequestBody @Valid ProductSearchTO productSearchTO) {
        ProductSearchCriteria searchCriteria = ProductTOUtils.getSearchCriteriaFromTO(productSearchTO);
        List<ProductTO> searchResult = productService.searchProducts(searchCriteria)
                .stream().map(ProductTOUtils::getProductTOFromEntity).collect(Collectors.toList());

        return ResponseEntity.ok(searchResult);
    }

}
