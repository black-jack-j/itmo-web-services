package org.blackjackj.wslab;

import org.blackjackj.wslab.dataaccess.ProductRepository;
import org.blackjackj.wslab.dataaccess.ProductSearchCriteria;
import org.junit.Assert;
import org.junit.Test;

public class ProductSearchCriteriaParserTest {

    @Test
    public void testEmptyCriteriaQuery() {

        String expectedQuery = "SELECT id, name, manufacturer, price, description FROM products;";

        String actualQuery = ProductRepository.ProductSearchCriteriaParser.getQueryFromCriteria(ProductSearchCriteria.EMPTY_CRITERIA);

        Assert.assertEquals(expectedQuery, actualQuery);

    }

    @Test
    public void testCriteriaWithOneWhereIDClause() {

        String expectedQuery = "SELECT id, name, manufacturer, price, description FROM products WHERE id=42;";

        ProductSearchCriteria criteria = new ProductSearchCriteria(42L, null, null, null, null);

        String actualQuery = ProductRepository.ProductSearchCriteriaParser.getQueryFromCriteria(criteria);

        Assert.assertEquals(expectedQuery, actualQuery);

    }

    @Test
    public void testCriteriaWithIdAndNameClauses() {
        String expectedQuery = "SELECT id, name, manufacturer, price, description FROM products WHERE id=42 AND name='FOO';";

        ProductSearchCriteria criteria = new ProductSearchCriteria(42L, "FOO", null, null, null);

        String actualQuery = ProductRepository.ProductSearchCriteriaParser.getQueryFromCriteria(criteria);

        Assert.assertEquals(expectedQuery, actualQuery);

    }

}
