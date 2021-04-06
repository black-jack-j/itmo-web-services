package org.blackjackj.wslab.dataaccess;

import org.blackjackj.wslab.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
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
        log.info("search by criteria: {}", productSearchCriteria);
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

    public Long createProduct(String name, String manufacturer, Double price, String description) {
        log.info("create product [name: {}, manufacturer: {}, price: {}, description: {}]",
                name,
                manufacturer,
                price,
                description
        );
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ProductSearchCriteriaParser.CREATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, manufacturer);
            statement.setDouble(3, price);
            if (description == null) {
                statement.setNull(4, Types.VARCHAR);
            } else {
                statement.setString(4, description);
            }
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Failed to create product, no ID obtained");
                }
            }
        } catch (SQLException throwables) {
            log.error("error during create operation");
            throwables.printStackTrace();
        }
        return -1L;
    }

    public boolean deleteProductById(Long id) {
        log.info("deleting product: {}", id);

        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSearchCriteriaParser.DELETE_STATEMENT);

            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean updateProduct(Product productUpdate) {
        log.info("updating product: {}", productUpdate);

        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSearchCriteriaParser.UPDATE_STATEMENT);

            preparedStatement.setString(1, productUpdate.getName());
            preparedStatement.setString(2, productUpdate.getManufacturer());
            preparedStatement.setDouble(3, productUpdate.getPrice());
            if (productUpdate.getDescription() == null) {
                preparedStatement.setNull(4, Types.VARCHAR);
            } else {
                preparedStatement.setString(4, productUpdate.getDescription());
            }
            preparedStatement.setLong(5, productUpdate.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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

        public static final String CREATE_STATEMENT = "INSERT INTO products VALUES (DEFAULT, ?, ?, ?, ?);";

        public static final String DELETE_STATEMENT = "DELETE FROM products WHERE id=?;";

        public static final String UPDATE_STATEMENT = "UPDATE products SET name=?, manufacturer=?, price=?, description=? WHERE id=?;";

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
