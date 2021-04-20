package org.blackjackj.wslab.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "products")
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String manufacturer;

    private Double price;

    private String description;

}
