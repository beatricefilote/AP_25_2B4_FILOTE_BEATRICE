import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class DataImporter {
    public void importCities(String filePath) {
        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);
            CityDAO cityDAO = new CityDAO();

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                City city = new City();
                city.setCountry(data[0]);
                city.setName(data[1]);
                city.setCapital(Boolean.parseBoolean(data[2]));
                city.setLatitude(Double.parseDouble(data[3]));
                city.setLongitude(Double.parseDouble(data[4]));

                cityDAO.addCity(city, conn);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}