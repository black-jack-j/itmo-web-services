package org.blackjackj.wslab;

import org.blackjackj.wslab.dataaccess.ProductSearchCriteria;
import org.junit.Assert;
import org.junit.Test;

public class ProductSearchCriteriaTest {

    @Test
    public void testEmptyCriteriaIsEmpty() {
        Assert.assertTrue(ProductSearchCriteria.EMPTY_CRITERIA.isEmptyCriteria());
    }

    @Test
    public void testCriteriaWithNullsEmpty() {
        ProductSearchCriteria emptyCriteria = new ProductSearchCriteria(null, null, null, null, null);

        Assert.assertTrue(emptyCriteria.isEmptyCriteria());
    }

    @Test
    public void testCriteriaWithIdNotEmpty() {
        ProductSearchCriteria criteria = new ProductSearchCriteria(42L, null, null, null, null);

        Assert.assertFalse(criteria.isEmptyCriteria());
    }

}
