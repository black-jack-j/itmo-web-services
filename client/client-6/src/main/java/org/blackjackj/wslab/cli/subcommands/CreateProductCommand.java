package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.exception.InvalidProductException;
import org.blackjackj.wslab.service.ProductWebService;
import org.blackjackj.wslab.transport.ProductCreateTO;
import org.blackjackj.wslab.transport.ProductTO;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "create", description = "Create product with specified properties", mixinStandardHelpOptions = true)
public class CreateProductCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-n", "--name"}, required = true)
    private String name;

    @Option(names = {"-m", "--manufacturer"}, required = true)
    private String manufacturer;

    @Option(names = {"-p", "--price"}, required = true)
    private Double price;

    @Option(names = {"-d", "--description"})
    private String description;

    @Override
    public void run() {
        ProductCreateTO productCreateTO = new ProductCreateTO();
        productCreateTO.setName(name);
        productCreateTO.setManufacturer(manufacturer);
        productCreateTO.setPrice(price);
        productCreateTO.setDescription(description);
        ProductWebService productWebService = this.get();
        try {
            ProductTO createdProduct = productWebService.createProduct(productCreateTO);
            System.out.println(createdProduct);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }
}
