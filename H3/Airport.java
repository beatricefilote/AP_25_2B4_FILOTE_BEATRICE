import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<Runway> runways = new ArrayList<>();

    public void addRunway(Runway runway) {
        runways.add(runway);
    }

    public List<Runway> getRunways() {
        return runways;
    }
}