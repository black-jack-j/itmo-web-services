package org.blackjackj.wslab.lab4.services;

import org.blackjackj.wslab.dataaccess2.ProductRepository;
import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ThrottlingExecutionService throttlingExecutionService;

    public CompletableFuture<List<Product>> searchProducts(ProductSearchCriteria productSearchCriteria) {
        return throttlingExecutionService.submit(() -> productRepository.getProductsBy(productSearchCriteria));
    }

    public CompletableFuture<Product> createProduct(Product productToCreate) {
        return throttlingExecutionService.submit(() -> productRepository.save(productToCreate));
    }

    public CompletableFuture<Product> updateProduct(Product productUpdate) {
        return throttlingExecutionService.submit(() -> productRepository.save(productUpdate));
    }

    public CompletableFuture<?> deleteProduct(Long id) {
        return throttlingExecutionService.submit(() -> productRepository.deleteById(id));
    }

    public CompletableFuture<?> longRunningTask() {
        return throttlingExecutionService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
