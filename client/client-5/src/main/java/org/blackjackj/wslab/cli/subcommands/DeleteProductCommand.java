package org.blackjackj.wslab.cli.subcommands;

import org.blackjackj.wslab.service.ProductWebService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "delete", description = "Delete product by its ID", mixinStandardHelpOptions = true)
public class DeleteProductCommand implements Runnable, ProductServiceProvider {

    @Option(names = {"-i", "--id"}, required = true)
    private Long id;

    @Override
    public void run() {
        ProductWebService productWebService = this.get();
        productWebService.deleteProduct(id);
        System.out.println("deleted product #"+id);
    }
}
