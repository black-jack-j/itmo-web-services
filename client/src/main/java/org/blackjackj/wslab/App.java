package org.blackjackj.wslab;

import org.apache.commons.cli.*;
import org.blackjackj.wslab.service.Product;
import org.blackjackj.wslab.service.ProductService;
import org.blackjackj.wslab.service.ProductWebService;

import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main( String[] args ) throws MalformedURLException, ParseException {
        ProductService productService = getProductService();

        final Options options = new Options();

        options.addOption("i", "id", true, "filter by ID");
        options.addOption("n", "name", true, "filter by NAME");
        options.addOption("m", "manufacturer", true, "filter by MANUFACTURER");
        options.addOption("p", "price", true, "filter by PRICE");
        options.addOption("d", "description", true, "filter by DESCRIPTION");
        options.addOption("h", "help", false, "show help");

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("wslab-client", options);
            return;
        }

        Long id = null;
        String name = null, manufacturer = null, description = null;
        Double price = null;

        if (cmd.hasOption("i")) {
            id = Long.parseLong(cmd.getOptionValue("i"));
        }

        if (cmd.hasOption("n")) {
            name = cmd.getOptionValue("n");
        }

        if (cmd.hasOption("m")) {
            manufacturer = cmd.getOptionValue("m");
        }

        if (cmd.hasOption("d")) {
            description = cmd.getOptionValue("d");
        }

        if (cmd.hasOption("p")) {
            price = Double.parseDouble(cmd.getOptionValue("p"));
        }

        productService.getProducts(id, name, manufacturer, price, description).stream().map(App::parseProduct).forEach(System.out::println);
    }

    private static ProductService getProductService() throws MalformedURLException {
        URL serviceUrl = new URL("http://localhost:8080/wslab-server/ProductWebService?wsdl");
        return new ProductWebService(serviceUrl).getProductServicePort();
    }

    private static String parseProduct(Product product) {
        return String.format("Product{id: %d, name: '%s', manufacturer: '%s', price: %f%s'}\n",
                product.getId(), product.getName(), product.getManufacturer(), product.getPrice(),
                product.getDescription() == null ? "" : String.format(", description: '%s", product.getDescription()));
    }

}
