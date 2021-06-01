package org.blackjackj.wslab.service;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.blackjackj.wslab.exception.InvalidProductException;
import org.blackjackj.wslab.exception.ProductNotExistException;

import java.io.IOException;

public class WslabErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 400) {
            try {
                throw  new ProductNotExistException(Util.toString(response.body().asReader(Util.UTF_8)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (response.status() == 409) {
            try {
                throw  new InvalidProductException(Util.toString(response.body().asReader(Util.UTF_8)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new ErrorDecoder.Default().decode(s, response);
    }
}
