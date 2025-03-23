import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.addRunway(new Runway("R1"));
        airport.addRunway(new Runway("R2"));

        List<Flight> flights = Arrays.asList(
                new Flight("F1001", LocalTime.of(10, 0), LocalTime.of(10, 30)),
                new Flight("F1002", LocalTime.of(10, 15), LocalTime.of(10, 45)),
                new Flight("F1003", LocalTime.of(11, 0), LocalTime.of(11, 20)),
                new Flight("F1004", LocalTime.of(10, 35), LocalTime.of(11, 0)),
                new Flight("F1005", LocalTime.of(10, 40), LocalTime.of(11, 10))
        );

        SchedulingProblem problem = new SchedulingProblem(airport, flights);
        problem.solve();
        problem.printSolution();
    }
}