package org.blackjackj.wslab.lab4.services;

import org.blackjackj.wslab.dataaccess2.ProductRepository;
import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(ProductSearchCriteria productSearchCriteria) {
        return productRepository.getProductsBy(productSearchCriteria);
    }
}
