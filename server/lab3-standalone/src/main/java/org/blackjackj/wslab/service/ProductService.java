package org.blackjackj.wslab.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.blackjackj.wslab.dataaccess2.ProductRepository;
import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.blackjackj.wslab.exception.*;
import org.blackjackj.wslab.transport.ProductCreateTO;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductUpdateTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@WebService(serviceName = "ProductWebService")
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private Validator validator;

    @WebMethod(operationName = "getProducts")
    public List<Product> getProducts(@WebParam(name = "searchTO") ProductSearchTO productSearchTO) throws ProductSearchException {
        Set<ConstraintViolation<ProductSearchTO>> constraintViolations = validator.validate(productSearchTO);
        if (!constraintViolations.isEmpty()) {
            List<String> messages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new ProductSearchException("Exception", new ProductSearchFault(messages));
        }
        ProductSearchCriteria searchCriteria = ProductSearchCriteria.of(
                productSearchTO.getId(),
                productSearchTO.getName(),
                productSearchTO.getManufacturer(),
                productSearchTO.getPrice(),
                productSearchTO.getDescription()
        );

        return productRepository.getProductsBy(searchCriteria);
    }

    @WebMethod(operationName = "createProduct")
    public Long createProduct(@WebParam(name = "createTO") ProductCreateTO productCreateTO) throws ProductCreateException {
        Set<ConstraintViolation<ProductCreateTO>> constraintViolations = validator.validate(productCreateTO);
        if (!constraintViolations.isEmpty()) {
            List<String> violations = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new ProductCreateException("Exception", new ProductCreateFault(violations));
        }
        Product product = Product.builder()
                .name(productCreateTO.getName())
                .manufacturer(productCreateTO.getManufacturer())
                .price(productCreateTO.getPrice())
                .description(productCreateTO.getDescription())
                .build();
        return productRepository.save(product).getId();
    }

    @WebMethod(operationName = "deleteProductById")
    public void deleteProductById(@WebParam(name = "id") Long id) throws ProductNotFoundException {
        validateProductExistOtherwiseThrowException(id);
        
        productRepository.deleteById(id);
    }

    @WebMethod(operationName = "updateProduct")
    public void updateProduct(@WebParam(name = "updateTO") ProductUpdateTO productUpdateTO) throws ProductNotFoundException, ProductUpdateException {
        Set<ConstraintViolation<ProductUpdateTO>> constraintViolations = validator.validate(productUpdateTO);
        if (!constraintViolations.isEmpty()) {
            List<String> violationMessages = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.toList());
            ProductUpdateFault updateFault = new ProductUpdateFault(violationMessages);
            throw new ProductUpdateException("Exception", updateFault);

        }
        
        validateProductExistOtherwiseThrowException(productUpdateTO.getId());
        
        Product product = productRepository.findById(productUpdateTO.getId()).get();
        product.setName(productUpdateTO.getName());
        product.setManufacturer(productUpdateTO.getManufacturer());
        product.setPrice(productUpdateTO.getPrice());
        product.setDescription(productUpdateTO.getDescription());

        productRepository.save(product);
    }

    private void validateProductExistOtherwiseThrowException(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            ProductNotFoundFault notFoundFault = ProductNotFoundFault.withId(id);
            throw new ProductNotFoundException(notFoundFault.getMessage(), notFoundFault);
        }
    }
}
