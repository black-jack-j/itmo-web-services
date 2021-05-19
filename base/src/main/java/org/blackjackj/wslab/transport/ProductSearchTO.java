package org.blackjackj.wslab.transport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchTO {

    private Long id;
    private String name;
    private String manufacturer;
    @Min(value = 0, message = "price must be greater or equal to 0")
    private Double price;
    private String description;

}

