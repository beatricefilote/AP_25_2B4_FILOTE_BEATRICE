import java.util.List;

public class SchedulingProblem {
    private Airport airport;
    private List<Flight> flights;

    public SchedulingProblem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }

    public void solve() {
        for (Flight flight : flights) {
            boolean assigned = false;
            for (Runway runway : airport.getRunways()) {
                if (runway.canAssignFlight(flight)) {
                    runway.assignFlight(flight);
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                System.out.println("! Could not assign flight: " + flight);
            }
        }
    }

    public void printSolution() {
        for (Runway runway : airport.getRunways()) {
            System.out.println("Runway " + runway.getName() + ":");
            for (Flight flight : runway.getAssignedFlights()) {
                System.out.println("   -> " + flight);
            }
        }
    }
}