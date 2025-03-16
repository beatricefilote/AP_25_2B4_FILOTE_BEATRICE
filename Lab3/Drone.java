public class Drone extends Aircraft implements CargoCapable{
    private double maxPayload;
    private double batteryLife;

    public Drone(double maxPayload, double batteryLife, String name, String model, String tailNumber, double wingSpan) {
        super(name, model, tailNumber, wingSpan);
        this.maxPayload = maxPayload;
        this.batteryLife = batteryLife;
    }

    @Override
    public double getMaximumPayload() {
        return maxPayload;
    }

    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife){
        this.batteryLife = batteryLife;
    }

    @Override
    public String toString() {
        return super.toString() + "  Drone";
    }


}