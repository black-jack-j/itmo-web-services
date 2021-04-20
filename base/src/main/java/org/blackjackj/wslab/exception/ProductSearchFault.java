package org.blackjackj.wslab.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductSearchFault {

    private List<String> violationsMessages = new ArrayList<>();
    private String message;

    public ProductSearchFault(List<String> violationsMessages) {
        this.violationsMessages = violationsMessages;
    }

}
