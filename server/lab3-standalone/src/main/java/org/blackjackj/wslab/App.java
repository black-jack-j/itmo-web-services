package org.blackjackj.wslab;

import org.blackjackj.wslab.dataaccess2.ProductRepository;
import org.blackjackj.wslab.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validator;
import javax.xml.ws.Endpoint;

@ComponentScan(basePackages = "org.blackjackj.wslab")
@Configuration
@EnableJpaRepositories(basePackages = "org.blackjackj.wslab.dataaccess2")
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);

        ProductService service = applicationContext.getBean(ProductService.class);

        Endpoint.publish("http://0.0.0.0:8080/wslab-server/ProductWebService", service);
    }

    @Bean
    public ProductService getProductService(ProductRepository repository, Validator validator) {
        return new ProductService(repository, validator);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getDBTransactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("product");
    }

    @Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

}
