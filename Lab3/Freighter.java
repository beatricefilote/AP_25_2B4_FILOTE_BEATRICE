public class Freighter extends Aircraft implements CargoCapable, PassengerCapable {

    private double maxPayload;
    private int seatCount;

    public Freighter(int seatCount, double maxPayload, String name, String model, String tailNumber, double wingSpan) {
        super(name, model, tailNumber, wingSpan);
        this.seatCount = seatCount;
        this.maxPayload = maxPayload;
    }

    @Override
    public double getMaximumPayload() {
        return maxPayload;
    }

    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    @Override
    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    @Override
    public String toString() {
        return super.toString() + "  Freighter";
    }


}