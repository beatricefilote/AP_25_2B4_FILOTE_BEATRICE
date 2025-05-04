import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //  orase
        new DataImporter().importCities("cities.csv");

        // distante
        try {
            List<City> cities = new CityDAO().getAllCities();
            if (cities.size() >= 2) {
                City c1 = cities.get(0);
                City c2 = cities.get(1);
                double distance = DistanceCalculator.calculate(c1, c2);
                System.out.printf("Distanța dintre %s și %s: %.2f km\n",
                        c1.getName(), c2.getName(), distance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}