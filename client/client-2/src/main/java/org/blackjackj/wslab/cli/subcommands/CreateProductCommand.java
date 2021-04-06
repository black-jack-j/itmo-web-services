package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductService;
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
        ProductService productService = this.get();
        Long id = productService.createProduct(name, manufacturer, price, description);
        if (id != null) {
            System.out.printf("created product #%d: {name: '%s', manufacturer: '%s', price: %.2f%s}\n",
                    id,
                    name,
                    manufacturer,
                    price,
                    description == null ? "" : String.format(", description: '%s'", description)
            );
        } else {
            System.out.println("error creating product");
        }

    }
}
