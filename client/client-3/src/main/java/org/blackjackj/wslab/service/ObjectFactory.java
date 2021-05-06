
package org.blackjackj.wslab.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.blackjackj.wslab.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProductCreateException_QNAME = new QName("http://service.wslab.blackjackj.org/", "ProductCreateException");
    private final static QName _GetImageResponse_QNAME = new QName("http://service.wslab.blackjackj.org/", "getImageResponse");
    private final static QName _ProductUpdateException_QNAME = new QName("http://service.wslab.blackjackj.org/", "ProductUpdateException");
    private final static QName _UpdateProduct_QNAME = new QName("http://service.wslab.blackjackj.org/", "updateProduct");
    private final static QName _CreateProduct_QNAME = new QName("http://service.wslab.blackjackj.org/", "createProduct");
    private final static QName _GetProductsResponse_QNAME = new QName("http://service.wslab.blackjackj.org/", "getProductsResponse");
    private final static QName _DeleteProductByIdResponse_QNAME = new QName("http://service.wslab.blackjackj.org/", "deleteProductByIdResponse");
    private final static QName _DeleteProductById_QNAME = new QName("http://service.wslab.blackjackj.org/", "deleteProductById");
    private final static QName _CreateProductResponse_QNAME = new QName("http://service.wslab.blackjackj.org/", "createProductResponse");
    private final static QName _ProductSearchException_QNAME = new QName("http://service.wslab.blackjackj.org/", "ProductSearchException");
    private final static QName _GetImage_QNAME = new QName("http://service.wslab.blackjackj.org/", "getImage");
    private final static QName _ProductNotFoundException_QNAME = new QName("http://service.wslab.blackjackj.org/", "ProductNotFoundException");
    private final static QName _GetProducts_QNAME = new QName("http://service.wslab.blackjackj.org/", "getProducts");
    private final static QName _UpdateProductResponse_QNAME = new QName("http://service.wslab.blackjackj.org/", "updateProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.blackjackj.wslab.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetImageResponse }
     * 
     */
    public GetImageResponse createGetImageResponse() {
        return new GetImageResponse();
    }

    /**
     * Create an instance of {@link ProductCreateFault }
     * 
     */
    public ProductCreateFault createProductCreateFault() {
        return new ProductCreateFault();
    }

    /**
     * Create an instance of {@link ProductUpdateFault }
     * 
     */
    public ProductUpdateFault createProductUpdateFault() {
        return new ProductUpdateFault();
    }

    /**
     * Create an instance of {@link UpdateProduct }
     * 
     */
    public UpdateProduct createUpdateProduct() {
        return new UpdateProduct();
    }

    /**
     * Create an instance of {@link CreateProduct }
     * 
     */
    public CreateProduct createCreateProduct() {
        return new CreateProduct();
    }

    /**
     * Create an instance of {@link GetProductsResponse }
     * 
     */
    public GetProductsResponse createGetProductsResponse() {
        return new GetProductsResponse();
    }

    /**
     * Create an instance of {@link DeleteProductByIdResponse }
     * 
     */
    public DeleteProductByIdResponse createDeleteProductByIdResponse() {
        return new DeleteProductByIdResponse();
    }

    /**
     * Create an instance of {@link DeleteProductById }
     * 
     */
    public DeleteProductById createDeleteProductById() {
        return new DeleteProductById();
    }

    /**
     * Create an instance of {@link CreateProductResponse }
     * 
     */
    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    /**
     * Create an instance of {@link ProductNotFoundFault }
     * 
     */
    public ProductNotFoundFault createProductNotFoundFault() {
        return new ProductNotFoundFault();
    }

    /**
     * Create an instance of {@link GetProducts }
     * 
     */
    public GetProducts createGetProducts() {
        return new GetProducts();
    }

    /**
     * Create an instance of {@link ProductSearchFault }
     * 
     */
    public ProductSearchFault createProductSearchFault() {
        return new ProductSearchFault();
    }

    /**
     * Create an instance of {@link GetImage }
     * 
     */
    public GetImage createGetImage() {
        return new GetImage();
    }

    /**
     * Create an instance of {@link UpdateProductResponse }
     * 
     */
    public UpdateProductResponse createUpdateProductResponse() {
        return new UpdateProductResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link ProductSearchTO }
     * 
     */
    public ProductSearchTO createProductSearchTO() {
        return new ProductSearchTO();
    }

    /**
     * Create an instance of {@link ProductUpdateTO }
     * 
     */
    public ProductUpdateTO createProductUpdateTO() {
        return new ProductUpdateTO();
    }

    /**
     * Create an instance of {@link ProductCreateTO }
     * 
     */
    public ProductCreateTO createProductCreateTO() {
        return new ProductCreateTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductCreateFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "ProductCreateException")
    public JAXBElement<ProductCreateFault> createProductCreateException(ProductCreateFault value) {
        return new JAXBElement<ProductCreateFault>(_ProductCreateException_QNAME, ProductCreateFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "getImageResponse")
    public JAXBElement<GetImageResponse> createGetImageResponse(GetImageResponse value) {
        return new JAXBElement<GetImageResponse>(_GetImageResponse_QNAME, GetImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductUpdateFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "ProductUpdateException")
    public JAXBElement<ProductUpdateFault> createProductUpdateException(ProductUpdateFault value) {
        return new JAXBElement<ProductUpdateFault>(_ProductUpdateException_QNAME, ProductUpdateFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "updateProduct")
    public JAXBElement<UpdateProduct> createUpdateProduct(UpdateProduct value) {
        return new JAXBElement<UpdateProduct>(_UpdateProduct_QNAME, UpdateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "createProduct")
    public JAXBElement<CreateProduct> createCreateProduct(CreateProduct value) {
        return new JAXBElement<CreateProduct>(_CreateProduct_QNAME, CreateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "getProductsResponse")
    public JAXBElement<GetProductsResponse> createGetProductsResponse(GetProductsResponse value) {
        return new JAXBElement<GetProductsResponse>(_GetProductsResponse_QNAME, GetProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "deleteProductByIdResponse")
    public JAXBElement<DeleteProductByIdResponse> createDeleteProductByIdResponse(DeleteProductByIdResponse value) {
        return new JAXBElement<DeleteProductByIdResponse>(_DeleteProductByIdResponse_QNAME, DeleteProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "deleteProductById")
    public JAXBElement<DeleteProductById> createDeleteProductById(DeleteProductById value) {
        return new JAXBElement<DeleteProductById>(_DeleteProductById_QNAME, DeleteProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "createProductResponse")
    public JAXBElement<CreateProductResponse> createCreateProductResponse(CreateProductResponse value) {
        return new JAXBElement<CreateProductResponse>(_CreateProductResponse_QNAME, CreateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSearchFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "ProductSearchException")
    public JAXBElement<ProductSearchFault> createProductSearchException(ProductSearchFault value) {
        return new JAXBElement<ProductSearchFault>(_ProductSearchException_QNAME, ProductSearchFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "getImage")
    public JAXBElement<GetImage> createGetImage(GetImage value) {
        return new JAXBElement<GetImage>(_GetImage_QNAME, GetImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductNotFoundFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "ProductNotFoundException")
    public JAXBElement<ProductNotFoundFault> createProductNotFoundException(ProductNotFoundFault value) {
        return new JAXBElement<ProductNotFoundFault>(_ProductNotFoundException_QNAME, ProductNotFoundFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "getProducts")
    public JAXBElement<GetProducts> createGetProducts(GetProducts value) {
        return new JAXBElement<GetProducts>(_GetProducts_QNAME, GetProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.wslab.blackjackj.org/", name = "updateProductResponse")
    public JAXBElement<UpdateProductResponse> createUpdateProductResponse(UpdateProductResponse value) {
        return new JAXBElement<UpdateProductResponse>(_UpdateProductResponse_QNAME, UpdateProductResponse.class, null, value);
    }

}
