package org.blackjackj.wslab.transport;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductTO {

    private Long id;

    private String name;

    private String manufacturer;

    private Double price;

    private String description;

}