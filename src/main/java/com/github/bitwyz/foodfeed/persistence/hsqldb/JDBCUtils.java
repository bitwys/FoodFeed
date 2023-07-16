package com.github.bitwyz.foodfeed.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String DB_URL = "jdbc:hsqldb:file:db_files/db_files";

    public static Connection getConnection() {
        final Connection connection;

        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (final SQLException e) {
            throw new RuntimeException("Failed to load hsql database.", e);
        }

        return connection;
    }

    public static void printSqlException(final SQLException ex) {
        for (final Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    private JDBCUtils() {}
}
