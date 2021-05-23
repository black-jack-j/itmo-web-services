package org.blackjackj.wslab.service;

import lombok.NoArgsConstructor;
import org.blackjackj.wslab.dataaccess2.ProductRepository;
import org.blackjackj.wslab.dataaccess2.ProductSearchCriteria;
import org.blackjackj.wslab.entity.Product;
import org.blackjackj.wslab.exception.*;
import org.blackjackj.wslab.transport.ProductCreateTO;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductUpdateTO;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.MTOM;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@MTOM
@WebService(serviceName = "ProductWebService")
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private Validator validator;

    public ProductService(ProductRepository productRepository, Validator validator) {
        this.productRepository = productRepository;
        this.validator = validator;
    }

    private WebServiceContext webServiceContext;

    @Resource
    @WebMethod(exclude = true)
    public void setContext(WebServiceContext webServiceContext) {
        this.webServiceContext = webServiceContext;
    }

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
    public Long createProduct(@WebParam(name = "createTO") ProductCreateTO productCreateTO) throws ProductCreateException, UnauthorizedException {
        checkCredentialsAreValidOtherwiseThrowException();

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
    public void deleteProductById(@WebParam(name = "id") Long id) throws ProductNotFoundException, UnauthorizedException {
        checkCredentialsAreValidOtherwiseThrowException();

        validateProductExistOtherwiseThrowException(id);
        
        productRepository.deleteById(id);
    }

    @WebMethod(operationName = "updateProduct")
    public void updateProduct(@WebParam(name = "updateTO") ProductUpdateTO productUpdateTO) throws ProductNotFoundException, ProductUpdateException, UnauthorizedException {
        checkCredentialsAreValidOtherwiseThrowException();

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

    private void checkCredentialsAreValidOtherwiseThrowException() throws UnauthorizedException{
        MessageContext messageContext = webServiceContext.getMessageContext();

        Map httpHeaders = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) httpHeaders.getOrDefault("Username", Collections.emptyList());
        List passwordList = (List) httpHeaders.getOrDefault("Password", Collections.emptyList());
        if (!userList.contains("test") || !passwordList.contains("test")) {
            throw new UnauthorizedException("Unauthorized", new UnauthorizedFault());
        }
    }

    @WebMethod(operationName = "getImage")
    public Image getImage() {
        try {
            return ImageIO.read(new URL("https://www.memesmonkey.com/images/memesmonkey/44/4444666dae9bb6fe0eb61c1d9286d2ad.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateProductExistOtherwiseThrowException(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            ProductNotFoundFault notFoundFault = ProductNotFoundFault.withId(id);
            throw new ProductNotFoundException(notFoundFault.getMessage(), notFoundFault);
        }
    }
}
