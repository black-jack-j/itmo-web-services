package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductNotFoundException;
import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.ProductUpdateException;
import org.blackjackj.wslab.service.ProductUpdateTO;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "update", description = "Update product with specified ID", mixinStandardHelpOptions = true)
public class UpdateProductCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-i", "--id"}, required = true)
    private Long id;

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
        ProductService productService = this.get();
        ProductUpdateTO productUpdateTO = new ProductUpdateTO();
        productUpdateTO.setId(id);
        productUpdateTO.setName(name);
        productUpdateTO.setDescription(description);
        productUpdateTO.setPrice(price);
        productUpdateTO.setManufacturer(manufacturer);
        boolean result = false;
        try {
            productService.updateProduct(productUpdateTO);
            System.out.printf("update product #%d: {name: '%s', manufacturer: '%s', price: %.2f%s}\n",
                    id,
                    name,
                    manufacturer,
                    price,
                    description == null ? "" : String.format(", description: '%s'", description)
            );
        } catch (ProductNotFoundException e) {
            System.out.println(e.getFaultInfo().getMessage());
        } catch (ProductUpdateException e) {
            e.getFaultInfo().getViolationsMessages().forEach(System.out::println);
        }
    }
}
