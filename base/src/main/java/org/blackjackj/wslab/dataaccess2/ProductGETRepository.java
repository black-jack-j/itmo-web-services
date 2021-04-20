package org.blackjackj.wslab.dataaccess2;

import org.blackjackj.wslab.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGETRepository {

    List<Product> getProductsBy(ProductSearchCriteria productSearchCriteria);

}
