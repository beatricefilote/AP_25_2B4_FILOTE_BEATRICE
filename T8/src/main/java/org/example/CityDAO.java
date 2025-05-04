import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    public void addCity(City city, Connection conn) throws SQLException {
        String sql = "INSERT INTO cities (country, name, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, city.getCountry());
            pstmt.setString(2, city.getName());
            pstmt.setBoolean(3, city.isCapital());
            pstmt.setDouble(4, city.getLatitude());
            pstmt.setDouble(5, city.getLongitude());
            pstmt.executeUpdate();
        }
    }

    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM cities";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setCountry(rs.getString("country"));
                city.setName(rs.getString("name"));
                city.setCapital(rs.getBoolean("capital"));
                city.setLatitude(rs.getDouble("latitude"));
                city.setLongitude(rs.getDouble("longitude"));
                cities.add(city);
            }
        }
        return cities;
    }
}