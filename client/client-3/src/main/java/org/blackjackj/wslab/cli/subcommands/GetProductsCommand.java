package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.Product;
import org.blackjackj.wslab.service.ProductSearchException;
import org.blackjackj.wslab.service.ProductSearchTO;
import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.utils.ProductUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;

@Command(name = "get", description = "Get list of products satisfying specified criteria", mixinStandardHelpOptions = true)
public class GetProductsCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-i", "--id"})
    private Long id;

    @Option(names = {"-n", "--name"})
    private String name;

    @Option(names = {"-m", "--manufacturer"})
    private String manufacturer;

    @Option(names = {"-p", "--price"})
    private Double price;

    @Option(names = {"-d", "--description"})
    private String description;

    @Override
    public void run() {
        ProductService productService = this.get();
        ProductSearchTO productSearchTO = new ProductSearchTO();
        productSearchTO.setId(id);
        productSearchTO.setDescription(description);
        productSearchTO.setName(name);
        productSearchTO.setManufacturer(manufacturer);
        List<Product> products = null;
        try {
            products = productService.getProducts(productSearchTO);
            products.stream().map(ProductUtils::productToString).forEach(System.out::println);
        } catch (ProductSearchException e) {
            e.getFaultInfo().getViolationsMessages().forEach(System.out::println);
        }
    }
}
