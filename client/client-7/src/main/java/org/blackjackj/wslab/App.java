package org.blackjackj.wslab;

import org.blackjackj.wslab.cli.subcommands.CreateServiceCommand;
import org.blackjackj.wslab.cli.subcommands.GetServiceCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "CRUDProductClient", subcommands = {
        GetServiceCommand.class,
        CreateServiceCommand.class,
}, description = "CLI client to access Product Web Service", mixinStandardHelpOptions = true)
public class App {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
