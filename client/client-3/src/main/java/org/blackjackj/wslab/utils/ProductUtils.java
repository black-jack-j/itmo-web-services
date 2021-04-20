package org.blackjackj.wslab.utils;

import org.blackjackj.wslab.service.Product;

public class ProductUtils {

    public static String productToString(Product product) {
        return String.format("Product{id: %d, name: '%s', manufacturer: '%s', price: %f%s'}\n",
                product.getId(), product.getName(), product.getManufacturer(), product.getPrice(),
                product.getDescription() == null ? "" : String.format(", description: '%s", product.getDescription()));
    }

}
