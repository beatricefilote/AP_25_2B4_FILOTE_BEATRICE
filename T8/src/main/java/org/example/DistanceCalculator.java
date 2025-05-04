public class DistanceCalculator {
    public static double calculate(City c1, City c2) {
        final int R = 6371; // Raza Pământului în km

        double lat1 = Math.toRadians(c1.getLatitude());
        double lon1 = Math.toRadians(c1.getLongitude());
        double lat2 = Math.toRadians(c2.getLatitude());
        double lon2 = Math.toRadians(c2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}