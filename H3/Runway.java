import java.util.ArrayList;
import java.util.List;

public class Runway {
    private String name;
    private List<Flight> assignedFlights = new ArrayList<>();

    public Runway(String name) {
        this.name = name;
    }

    public boolean canAssignFlight(Flight flight) {
        // verif dc intervalul de timp nu se suprapune cu alt zbor
        return assignedFlights.stream().allMatch(f ->
                flight.getLandingEnd().isBefore(f.getLandingStart()) ||
                        flight.getLandingStart().isAfter(f.getLandingEnd())
        );
    }

    public void assignFlight(Flight flight) {
        assignedFlights.add(flight);
    }

    public String getName() {
        return name;
    }

    public List<Flight> getAssignedFlights() {
        return assignedFlights;
    }
}