package com.github.bitwyz.foodfeed.persistence.hsqldb;

import com.github.bitwyz.foodfeed.model.FoodItem;
import com.github.bitwyz.foodfeed.persistence.ItemStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HsqldbItemStorage implements ItemStorage {

    private static final String CREATE_TABLE_SQL =
            """
                    CREATE TABLE IF NOT EXISTS foodItems (
                        internalId INT IDENTITY NOT NULL PRIMARY KEY,
                        id VARCHAR(50) NOT NULL,
                        name VARCHAR(30) NOT NULL,
                        url VARCHAR(100) NOT NULL,
                        cost DECIMAL NOT NULL
                    );
                    """;

    private static final String INSERT_ITEM_SQL =
            """
                    INSERT INTO foodItems
                        (id, name, url, cost) VALUES
                        (?, ?, ?, ?);
                    """;

    private static final String GET_ITEM_SQL =
            """
                    SELECT id, name, url, cost FROM foodItems WHERE id =?;
                    """;

    @Override
    public void initialize() {
        try (final Connection connection = JDBCUtils.getConnection()) {
            connection.createStatement().execute(CREATE_TABLE_SQL);
        } catch (final SQLException e) {
            JDBCUtils.printSqlException(e);
        }
    }

    @Override
    public void addFoodItem(final FoodItem item) {
        try (final Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL);
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getPageUrl());
            preparedStatement.setFloat(4, item.getCost());

            preparedStatement.execute();

        } catch (final SQLException e) {
            JDBCUtils.printSqlException(e);
        }
    }

    @Override
    public Optional<FoodItem> getFoodItem(final String itemId) {
        try (final Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_SQL);
            preparedStatement.setString(1, itemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(FoodItem.builder()
                    .id(resultSet.getString("id"))
                    .name(resultSet.getString("name"))
                    .pageUrl(resultSet.getString("url"))
                    .cost(resultSet.getFloat("cost"))
                    .build());

        } catch (final SQLException e) {
            JDBCUtils.printSqlException(e);
        }
        return Optional.empty();
    }
}
