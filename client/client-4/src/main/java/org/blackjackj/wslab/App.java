package org.blackjackj.wslab;

import org.blackjackj.wslab.cli.subcommands.GetProductsCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "GetProductClient", subcommands = {
        GetProductsCommand.class
}, description = "CLI client to access Product Web Service", mixinStandardHelpOptions = true)
public class App {

    @Spec
    CommandSpec spec;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
