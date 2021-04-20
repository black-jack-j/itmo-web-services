package org.blackjackj.wslab.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateFault {

    private String message = "FAILED TO UPDATE PRODUCT";
    private List<String> violationsMessages = new ArrayList<>();

    public ProductUpdateFault(List<String> violationsMessages) {
        this.violationsMessages = violationsMessages;
    }
}
