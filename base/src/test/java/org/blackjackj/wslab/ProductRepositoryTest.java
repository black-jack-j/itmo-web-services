package org.blackjackj.wslab;

import org.blackjackj.wslab.dataaccess.ProductRepository;
import org.blackjackj.wslab.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductRepositoryTest {

    private static final Long id = 42L;
    private static final String name = "FOO";
    private static final String manufacturer = "BAR";
    private static final Double price = 3.14;
    private static final String description = "FOOBAR";

    @Test
    public void testGetProducts() throws SQLException {

        DataSource mockedDataSource = getDataSource();

        Product expectedProduct = new Product(id, name, manufacturer, price, description);

        ProductRepository repository = new ProductRepository(mockedDataSource);

        Assert.assertArrayEquals(new Product[]{expectedProduct}, repository.getProducts().toArray());

    }

    private DataSource getDataSource() throws SQLException {

        Connection mockedConnection = getMockedConnection();

        DataSource dataSource = Mockito.mock(DataSource.class);

        Mockito.when(dataSource.getConnection()).thenReturn(mockedConnection);

        return dataSource;

    }

    private Connection getMockedConnection() throws SQLException {

        Statement mockedStatement = getMockedStatement();

        Connection mockedConnection = Mockito.mock(Connection.class);

        Mockito.when(mockedConnection.createStatement()).thenReturn(mockedStatement);

        return mockedConnection;

    }

    private Statement getMockedStatement() throws SQLException {

        ResultSet mockedResultSet = getMockResultSet();

        Statement mockedStatement = Mockito.mock(Statement.class);

        Mockito.when(mockedStatement.getResultSet()).thenReturn(mockedResultSet);

        return mockedStatement;
    }

    private ResultSet getMockResultSet() throws SQLException {

        ResultSet mockedResultSet = Mockito.mock(ResultSet.class);

        Mockito.when(mockedResultSet.next()).thenReturn(true).thenReturn(false);

        Mockito.when(mockedResultSet.getLong("id")).thenReturn(id);

        Mockito.when(mockedResultSet.getString("name")).thenReturn(name);

        Mockito.when(mockedResultSet.getString("manufacturer")).thenReturn(manufacturer);

        Mockito.when(mockedResultSet.getDouble("price")).thenReturn(price);

        Mockito.when(mockedResultSet.getString("description")).thenReturn(description);

        return mockedResultSet;
    }

    @Test
    public void testPlain() {
        String test = "hello";

        List<String> container = Collections.singletonList(test);

        test = "hello2";

        container.forEach(System.out::println);
    }

    

}
