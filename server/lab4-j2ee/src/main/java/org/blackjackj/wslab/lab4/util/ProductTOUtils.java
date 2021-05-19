package org.blackjackj.wslab.lab4.util;

import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;

public class ProductTOUtils {

    public static ProductSearchCriteria getSearchCriteriaFromTO(ProductSearchTO productSearchTO) {
        return ProductSearchCriteria.of(
                productSearchTO.getId(),
                productSearchTO.getName(),
                productSearchTO.getManufacturer(),
                productSearchTO.getPrice(),
                productSearchTO.getDescription()
        );
    }

    public static ProductTO getProductTOFromEntity(Product product) {
        return ProductTO.builder()
                .id(product.getId())
                .name(product.getName())
                .manufacturer(product.getManufacturer())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

}
