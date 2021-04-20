package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.*;
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
        ProductCreateTO createTO = new ProductCreateTO();
        createTO.setName(name);
        createTO.setDescription(description);
        createTO.setManufacturer(manufacturer);
        createTO.setPrice(price);
        Long id = null;
        try {
            id = productService.createProduct(createTO);
            System.out.println("product created with id: "+id);
        } catch (ProductCreateException e) {
            ProductCreateFault fault = e.getFaultInfo();
            fault.getViolationMessages().forEach(System.out::println);
        }

    }
}
