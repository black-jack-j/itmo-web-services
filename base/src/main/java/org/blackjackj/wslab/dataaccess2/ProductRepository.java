package org.blackjackj.wslab.dataaccess2;

import org.blackjackj.wslab.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, ProductGETRepository {
}
