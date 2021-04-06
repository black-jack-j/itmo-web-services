package org.blackjackj.wslab;

import org.blackjackj.wslab.cli.subcommands.CreateProductCommand;
import org.blackjackj.wslab.cli.subcommands.DeleteProductCommand;
import org.blackjackj.wslab.cli.subcommands.GetProductsCommand;
import org.blackjackj.wslab.cli.subcommands.UpdateProductCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "ProductCRUDClient", subcommands = {
        CreateProductCommand.class,
        GetProductsCommand.class,
        UpdateProductCommand.class,
        DeleteProductCommand.class
}, description = "CLI client to access Product CRUD Web Service", mixinStandardHelpOptions = true)
public class App {

    @Spec
    CommandSpec spec;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
