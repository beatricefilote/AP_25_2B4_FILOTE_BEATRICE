
public abstract class Aircraft implements Comparable<Aircraft> {

    private String name;
    private String model;
    private String tailNumber;
    private double wingSpan;

    public Aircraft(String name, String model, String tailNumber, double wingSpan) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
        this.wingSpan = wingSpan;
    }

    @Override
    public int compareTo(Aircraft other) {
        if (this.name == null && other.name == null) {
            return 0;
        }
        if (this.name == null) {
            return -1;
        }
        if (other.name == null) {
            return 1;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + model + ")";
    }
}

