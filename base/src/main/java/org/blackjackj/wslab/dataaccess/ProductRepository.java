package org.blackjackj.wslab.dataaccess;

import org.blackjackj.wslab.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getProducts() {
        return searchByCriteria(ProductSearchCriteria.EMPTY_CRITERIA);
    }

    public List<Product> searchByCriteria(ProductSearchCriteria productSearchCriteria) {
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            String query = ProductSearchCriteriaParser.getQueryFromCriteria(productSearchCriteria);

            statement.execute(query);

            ResultSet resultSet = statement.getResultSet();

            return getProductListFromResultSet(resultSet);

        } catch (SQLException throwables) {
            log.error("error during database lookup");
            return Collections.emptyList();
        }
    }

    private List<Product> getProductListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            products.add(getProductFromResultSet(resultSet));
        }

        return products;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ProductSearchCriteriaParser.ID);
        String name = resultSet.getString(ProductSearchCriteriaParser.NAME);
        String manufacturer = resultSet.getString(ProductSearchCriteriaParser.MANUFACTURER);
        Double price = resultSet.getDouble(ProductSearchCriteriaParser.PRICE);
        String description = resultSet.getString(ProductSearchCriteriaParser.DESCRIPTION);
        return new Product(id, name, manufacturer, price, description);
    }

    public static class ProductSearchCriteriaParser {

        private static final String ID = "id";

        private static final String NAME = "name";

        private static final String MANUFACTURER = "manufacturer";

        private static final String PRICE = "price";

        private static final String DESCRIPTION = "description";

        private static final String[] selectors = new String[]{ID, NAME, MANUFACTURER, PRICE, DESCRIPTION};

        private static final String QUERY_TEMPLATE = "SELECT %s FROM products%s";

        public static String getQueryFromCriteria(ProductSearchCriteria criteria) {

            String selectors = getSelectorClause();

            String whereClause = getWhereClause(criteria);

            return String.format(QUERY_TEMPLATE, selectors, whereClause);
        }

        private static String getSelectorClause() {
            StringJoiner selectJoiner = new StringJoiner(", ");

            Arrays.stream(selectors).forEach(selectJoiner::add);

            return selectJoiner.toString();
        }

        private static String getWhereClause(ProductSearchCriteria criteria) {
            if (criteria.isEmptyCriteria()) {
                return ";";
            }
            StringJoiner whereClauseJoiner = new StringJoiner(" AND ", " WHERE ", ";");

            String whereClauseTemplate = "%s=%s";
            String stringWhereClauseTemplate = "%s='%s'";

            if (criteria.getId() != null) {
                whereClauseJoiner.add(String.format(whereClauseTemplate, ID, criteria.getId()));
            }

            if (criteria.getName() != null) {
                whereClauseJoiner.add(String.format(stringWhereClauseTemplate, NAME, criteria.getName()));
            }

            if (criteria.getManufacturer() != null) {
                whereClauseJoiner.add(String.format(stringWhereClauseTemplate, MANUFACTURER, criteria.getManufacturer()));
            }

            if (criteria.getPrice() != null) {
                whereClauseJoiner.add(String.format(whereClauseTemplate, PRICE, criteria.getPrice()));
            }

            if (criteria.getDescription() != null) {
                whereClauseJoiner.add(String.format(stringWhereClauseTemplate, DESCRIPTION, criteria.getDescription()));
            }

            return whereClauseJoiner.toString();
        }

    }

}
