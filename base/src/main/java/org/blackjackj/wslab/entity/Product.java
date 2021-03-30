package org.blackjackj.wslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private Long id;

    private String name;

    private String manufacturer;

    private Double price;

    private String description;

}
