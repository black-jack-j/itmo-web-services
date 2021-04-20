package org.blackjackj.wslab.transport;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class ProductCreateTO {

    @Size(min = 1, max=255)
    @NotNull(message = "name must be specified")
    private String name;

    @Size(min = 1, max=255)
    @NotNull(message = "manufacturer must be specified")
    private String manufacturer;

    @Min(value = 0, message = "price must be greater or equal to 0")
    private Double price;

    @Null
    @Size(min = 1, max = 255)
    private String description;
}
