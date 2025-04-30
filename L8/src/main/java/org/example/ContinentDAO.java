package org.example;

import java.sql.*;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement("insert into continent(name) values(?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id from continent where name='" + name + "'")) {
            if(rs.next()) {
                return rs.getInt(1);
            } else {
                return null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select name from continent where id=" + id)) {
            if(rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        }
    }
}
