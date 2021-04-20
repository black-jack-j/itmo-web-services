package org.blackjackj.wslab.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductCreateFault {

    private String message = "FAILED TO CREATE PRODUCT";
    private List<String> violationMessages = new ArrayList<>();

    public ProductCreateFault(List<String> violationMessages) {
        this.violationMessages = violationMessages;
    }


}
