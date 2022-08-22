package NeedForSpeed;

public class Vehicle {
    final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

    public Vehicle(double fuel, int horsePower) {
        this.fuel = fuel;
        this.horsePower = horsePower;
        this.fuelConsumption = DEFAULT_FUEL_CONSUMPTION;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    public double getFuelConsumption() {
        return fuelConsumption;
    }
    public double getFuel() {
        return fuel;
    }
    public int getHorsePower() {
        return horsePower;
    }
    public void drive(double kilometers){
        this.fuel -= kilometers * fuelConsumption;
        if (this.fuel < 0){
            this.fuel = 0;
        }
    }
}
