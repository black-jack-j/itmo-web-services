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
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    public CompletableFuture<ResponseEntity<List<ProductTO>>> searchProducts(@Valid ProductSearchTO productSearchTO) {
        ProductSearchCriteria searchCriteria = ProductTOUtils.getSearchCriteriaFromTO(productSearchTO);
        return productService.searchProducts(searchCriteria).handle((products, err) -> {
            if (err == null) {
                return ResponseEntity.ok(
                        products.stream().map(ProductTOUtils::getProductTOFromEntity).collect(Collectors.toList())
                );
            }
            else return ResponseEntity.badRequest().build();
        });

    }

    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<ProductTO>> createProduct(@Valid ProductCreateTO productCreateTO) {
        Product productToSave = Product.builder()
                .name(productCreateTO.getName())
                .manufacturer(productCreateTO.getManufacturer())
                .price(productCreateTO.getPrice())
                .description(productCreateTO.getDescription())
                .build();
        return productService.createProduct(productToSave).handle((createdProduct, err) -> {
            if (err == null) {
                return ResponseEntity.ok(ProductTOUtils.getProductTOFromEntity(createdProduct));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        });
    }

    @ResponseBody
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<ProductTO>> updateProduct(@PathVariable Long id, @Valid ProductUpdateTO productUpdateTO) {
        Product productUpdate = Product.builder()
                .id(id)
                .name(productUpdateTO.getName())
                .manufacturer(productUpdateTO.getManufacturer())
                .price(productUpdateTO.getPrice())
                .description(productUpdateTO.getDescription())
                .build();
        return productService.updateProduct(productUpdate).handle((updatedProduct, err) -> {
            if (err == null) {
                ResponseEntity.ok(ProductTOUtils.getProductTOFromEntity(updatedProduct));
            }
            return ResponseEntity.badRequest().build();
        });
    }

    @GetMapping(path = "/long")
    public CompletableFuture<ResponseEntity> longTask() {
        return productService.longRunningTask().handle((res, err) -> {
            if (err == null) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id).handle((res, err) -> {
            if (err == null) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        });
    }

}
