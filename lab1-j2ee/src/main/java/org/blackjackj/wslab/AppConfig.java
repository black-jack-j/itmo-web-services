package org.blackjackj.wslab;

import lombok.Data;
import org.blackjackj.wslab.dataaccess.ProductRepository;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

/**
 * Hello world!
 *
 */
@Data
@ApplicationScoped
public class AppConfig {

    @Resource(lookup = "java:comp/env/jdbc/products")
    private DataSource dataSource;

    @Produces
    public ProductRepository getProductRepository() {
        return new ProductRepository(dataSource);
    }

}
