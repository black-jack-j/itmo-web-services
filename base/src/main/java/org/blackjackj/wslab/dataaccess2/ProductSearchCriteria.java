package org.blackjackj.wslab.dataaccess2;

import java.util.Optional;

public class ProductSearchCriteria {

    private final Long id;
    private final String name;
    private final String manufacturer;
    private final Double price;
    private final String description;

    private ProductSearchCriteria(
            Long id,
            String name,
            String manufacturer,
            Double price,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(this.name);
    }

    public Optional<String> getManufacturer() {
        return Optional.ofNullable(this.manufacturer);
    }

    public Optional<Double> getPrice() {
        return Optional.ofNullable(this.price);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    public static ProductSearchCriteria of(Long id, String name, String manufacturer, Double price, String description) {
        return new ProductSearchCriteria(id, name, manufacturer, price, description);
    }
}
