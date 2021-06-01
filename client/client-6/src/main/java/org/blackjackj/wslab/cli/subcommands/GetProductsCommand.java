package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.exception.InvalidProductException;
import org.blackjackj.wslab.service.ProductWebService;
import org.blackjackj.wslab.transport.ProductSearchTO;
import org.blackjackj.wslab.transport.ProductTO;
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
        ProductWebService productService = this.get();
        ProductSearchTO productSearchTO = new ProductSearchTO();
        productSearchTO.setId(id);
        productSearchTO.setDescription(description);
        productSearchTO.setName(name);
        productSearchTO.setManufacturer(manufacturer);
        productSearchTO.setPrice(price);
        try {
            List<ProductTO> products = productService.searchForProduct(productSearchTO);
            products.forEach(System.out::println);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }
}
