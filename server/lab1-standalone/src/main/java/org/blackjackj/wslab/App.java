package org.blackjackj.wslab;

import org.blackjackj.wslab.dataaccess.ProductRepository;
import org.blackjackj.wslab.service.ProductService;
import org.postgresql.ds.PGSimpleDataSource;

import javax.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName("wslab");
        ds.setUser("wsadmin");
        ds.setPassword("wspass");
        ds.setServerName("wslab-database");
        ProductRepository repository = new ProductRepository(ds);
        ProductService service = new ProductService(repository);
        Endpoint.publish("http://0.0.0.0:8080/wslab-server/ProductWebService", service);
    }
}
