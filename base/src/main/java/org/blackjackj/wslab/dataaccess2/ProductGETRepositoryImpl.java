package org.blackjackj.wslab.dataaccess2;

import lombok.Getter;
import lombok.Setter;
import org.blackjackj.wslab.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProductGETRepositoryImpl implements ProductGETRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProductsBy(ProductSearchCriteria productSearchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> products = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        productSearchCriteria.getId().ifPresent(id -> {
            predicates.add(cb.equal(products.get("id"), id));
        });

        productSearchCriteria.getName().ifPresent(name -> {
            predicates.add(cb.equal(products.get("name"), name));
        });

        productSearchCriteria.getManufacturer().ifPresent(manufacturer -> {
            predicates.add(cb.equal(products.get("manufacturer"), manufacturer));
        });

        productSearchCriteria.getPrice().ifPresent(price ->{
            predicates.add(cb.equal(products.get("price"), price));
        });

        productSearchCriteria.getDescription().ifPresent(description -> {
            predicates.add(cb.equal(products.get("description"), description));
        });

        query.select(products).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(query).getResultList();
    }

}
