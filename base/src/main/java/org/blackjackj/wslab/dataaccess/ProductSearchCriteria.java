package org.blackjackj.wslab.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSearchCriteria {

    private Long id;

    private String name;

    private String manufacturer;

    private Double price;

    private String description;


    public boolean isEmptyCriteria() {
        return id == null && name == null && manufacturer == null && price == null && description == null;
    }

    public final static ProductSearchCriteria EMPTY_CRITERIA = new ProductSearchCriteria(null, null, null, null, null) {
        @Override
        public boolean isEmptyCriteria() {
            return true;
        }
    };

}
