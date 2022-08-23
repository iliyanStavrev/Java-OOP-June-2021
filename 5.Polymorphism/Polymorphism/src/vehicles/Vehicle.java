package vehicles;

import java.text.DecimalFormat;

public  class Vehicle implements VehicleImp{
    private double fuelQuantity;
    private double fuelConsumption;
    private int tankCapacity;

    public int getTankCapacity() {
        return tankCapacity;
    }

    public Vehicle(double fuelQuantity, double fuelConsumption, int tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.setTankCapacity(tankCapacity);
    }
    public String drive(double distance){
       double fuelNeeded = distance * this.fuelConsumption;
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "needs refueling";
        if (fuelQuantity >= fuelNeeded){
            result = String.format("travelled %s km",df.format(distance));
            fuelQuantity -= fuelNeeded;
        }
        return result;
    }
    public void refuel(double litters){
        if (litters <= 0){
            System.out.println("Fuel must be a positive number");
        }
       else if (this.fuelQuantity + litters > this.tankCapacity){
            System.out.println("Cannot fit fuel in tank");
        }
        else {
            this.fuelQuantity += litters;
        }

    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setTankCapacity(int tankCapacity) {
        if (tankCapacity <= 0){
            System.out.println("Fuel must be a positive number");
        }
        this.tankCapacity = tankCapacity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity < 0){
            System.out.println("Fuel must be a positive number");
        }
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(),this.fuelQuantity);
    }
}
