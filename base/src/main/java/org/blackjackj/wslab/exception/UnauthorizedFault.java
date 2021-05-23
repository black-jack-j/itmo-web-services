package org.blackjackj.wslab.exception;

import lombok.Data;

@Data
public class UnauthorizedFault {

    private String message = "Unauthorized access";

}
