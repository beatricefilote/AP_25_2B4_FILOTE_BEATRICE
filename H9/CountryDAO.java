package org.example;

import java.sql.*;

public class CountryDAO {

    public void create(String name, String code, int continentID) throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }

        // First verify that the continent exists
        try (PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM continent WHERE id = ?")) {
            checkStmt.setInt(1, continentID);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Continent with ID " + continentID + " does not exist");
            }
        }

        // If continent exists, create the country
        try (PreparedStatement pstmt = con
                .prepareStatement("insert into countries (name, code, continent_id) values(?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continentID);
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Successfully created country: " + name + " with continent ID: " + continentID);
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
        try (PreparedStatement pstmt = con.prepareStatement("select id from countries where name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        if (con == null) {
            throw new SQLException("Database connection is null");
        }
        try (PreparedStatement pstmt = con.prepareStatement("select name from countries where id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        }
    }
}
