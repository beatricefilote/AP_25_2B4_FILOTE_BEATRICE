package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5433/beatricefilote";
    private static final String USER = "postgres";
    private static final String PASSWORD = "student";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    public static void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Successfully connected to database at: " + URL);
            checkDatabaseStructure();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error creating connection: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        }
    }

    private static void checkDatabaseStructure() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'continent')")) {
                if (rs.next() && !rs.getBoolean(1)) {
                    System.out.println("Creating continent table...");
                    stmt.execute("""
                                CREATE TABLE continent (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL UNIQUE
                                )
                            """);
                }
            }

            try (ResultSet rs = stmt.executeQuery(
                    "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'countries')")) {
                if (rs.next() && !rs.getBoolean(1)) {
                    System.out.println("Creating countries table...");
                    stmt.execute("""
                                CREATE TABLE countries (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    code VARCHAR(3) NOT NULL UNIQUE,
                                    continent_id INTEGER NOT NULL,
                                    FOREIGN KEY (continent_id) REFERENCES continent(id)
                                )
                            """);
                }
            }

            System.out.println("\nExisting continents in database:");
            try (ResultSet rs = stmt.executeQuery("SELECT id, name FROM continent ORDER BY id")) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2));
                }
            }

            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error checking database structure: " + e.getMessage());
            rollback();
            throw e;
        }
    }

    public static void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println("Error rolling back transaction: " + e.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed successfully");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}