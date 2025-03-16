public class Airliner extends Aircraft implements PassengerCapable, CargoCapable{
    private int seatCount;
    private double maxPayload;

    public Airliner(int seatCount, double maxPayload, String name, String model, String tailNumber, double wingSpan) {
        super(name, model, tailNumber, wingSpan);
        this.seatCount = seatCount;
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
    public double getMaximumPayload() {
        return maxPayload;
    }

    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    @Override
    public String toString() {
        return super.toString() + "  Airliner";
    }
}
