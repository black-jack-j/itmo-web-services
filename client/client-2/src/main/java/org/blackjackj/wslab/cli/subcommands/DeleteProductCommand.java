package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "delete", description = "Delete product by its ID", mixinStandardHelpOptions = true)
public class DeleteProductCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-i", "--id"}, required = true)
    private Long id;

    @Override
    public void run() {
        ProductService productService = this.get();
        boolean result = productService.deleteProductById(id);
        if (result) {
            System.out.printf("deleted product #%d\n", id);
        } else {
            System.out.println("error deleting product");
        }
    }
}
