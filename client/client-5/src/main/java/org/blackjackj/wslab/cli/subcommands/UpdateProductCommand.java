package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductWebService;
import org.blackjackj.wslab.transport.ProductTO;
import org.blackjackj.wslab.transport.ProductUpdateTO;
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

    @Option(names = {"--user"})
    private String username;

    @Option(names = {"--password"})
    private String password;

    @Override
    public void run() {
        ProductUpdateTO productUpdateTO = new ProductUpdateTO();
        productUpdateTO.setName(name);
        productUpdateTO.setManufacturer(manufacturer);
        productUpdateTO.setId(id);
        productUpdateTO.setDescription(description);
        productUpdateTO.setPrice(price);
        ProductWebService productWebService;
        if (username != null || password != null) {
            productWebService = getWithAuth(username, password);
        } else {
            productWebService = this.get();
        }
        ProductTO updatedProduct = productWebService.updateProduct(id, productUpdateTO);
        System.out.println(updatedProduct);
    }
}
