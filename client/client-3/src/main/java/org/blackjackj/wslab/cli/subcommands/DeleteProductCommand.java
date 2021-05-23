package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductNotFoundException;
import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.UnauthorizedException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "delete", description = "Delete product by its ID", mixinStandardHelpOptions = true)
public class DeleteProductCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-i", "--id"}, required = true)
    private Long id;

    @Option(names = {"--user"})
    private String username;

    @Option(names = {"--password"})
    private String password;

    @Override
    public void run() {
        ProductService productService;
        if (username != null || password != null) {
            productService = getWithAuth(username, password);
        } else {
            productService = this.get();
        }

        try {
            productService.deleteProductById(id);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getFaultInfo().getMessage());
        } catch (UnauthorizedException e) {
            System.out.println("specify valid credentials using --user and --password options");
        }
    }
}
