package org.blackjackj.wslab.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.blackjackj.wslab.dataaccess.ProductRepository;
import org.blackjackj.wslab.dataaccess.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "ProductWebService")
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @WebMethod(operationName = "getProducts")
    public List<Product> getProducts(
            @WebParam(name = "id") Long id,
            @WebParam(name = "name") String name,
            @WebParam(name = "manufacturer") String manufacturer,
            @WebParam(name = "price") Double price,
            @WebParam(name = "description") String description) {
        ProductSearchCriteria searchCriteria = new ProductSearchCriteria(id, name, manufacturer, price, description);

        return productRepository.searchByCriteria(searchCriteria);
    }

}
