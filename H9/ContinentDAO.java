package org.example;

import java.sql.*;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }
        try (PreparedStatement pstmt = con.prepareStatement("insert into continent(name) values(?)",
                Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Created continent '" + name + "' with ID: " + id);
                }
            }

            con.commit();
        } catch (SQLException e) {
            Database.rollback();
            throw e;
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }
        try (PreparedStatement pstmt = con.prepareStatement("select id from continent where name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Found continent '" + name + "' with ID: " + id);
                return id;
            } else {
                System.out.println("Continent '" + name + "' not found");
                return null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }
        try (PreparedStatement pstmt = con.prepareStatement("select name from continent where id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        }
    }

    public void listAllContinents() throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id, name from continent order by id")) {
            System.out.println("\nExisting continents in database:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2));
            }
        }
    }
}