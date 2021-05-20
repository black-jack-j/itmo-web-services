package org.blackjackj.wslab.lab4.controllers;

import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.blackjackj.wslab.lab4.services.ProductService;
import org.blackjackj.wslab.lab4.util.ProductTOUtils;
import org.blackjackj.wslab.transport.ProductCreateTO;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;
import org.blackjackj.wslab.transport.ProductUpdateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    public ResponseEntity<List<ProductTO>> searchProducts(@Valid ProductSearchTO productSearchTO) {
        ProductSearchCriteria searchCriteria = ProductTOUtils.getSearchCriteriaFromTO(productSearchTO);
        List<ProductTO> searchResult = productService.searchProducts(searchCriteria)
                .stream().map(ProductTOUtils::getProductTOFromEntity).collect(Collectors.toList());

        return ResponseEntity.ok(searchResult);
    }

    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductTO> createProduct(@Valid ProductCreateTO productCreateTO) {
        Product productToSave = Product.builder()
                .name(productCreateTO.getName())
                .manufacturer(productCreateTO.getManufacturer())
                .price(productCreateTO.getPrice())
                .description(productCreateTO.getDescription())
                .build();
        Product createdProduct = productService.createProduct(productToSave);
        return ResponseEntity.ok(ProductTOUtils.getProductTOFromEntity(createdProduct));
    }

    @ResponseBody
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductTO> updateProduct(@PathVariable Long id, @Valid ProductUpdateTO productUpdateTO) {
        Product productUpdate = Product.builder()
                .id(id)
                .name(productUpdateTO.getName())
                .manufacturer(productUpdateTO.getManufacturer())
                .price(productUpdateTO.getPrice())
                .description(productUpdateTO.getDescription())
                .build();
        Product updatedProduct = productService.updateProduct(productUpdate);
        return ResponseEntity.ok(ProductTOUtils.getProductTOFromEntity(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
