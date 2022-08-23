package vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle{

    public Truck(double fuelQuantity, double fuelConsumption,int tankaCapacity) {
        super(fuelQuantity, fuelConsumption,tankaCapacity);
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.6);
    }

    @Override
    public String drive(double distance) {
        return "Truck " + super.drive(distance);
    }

    @Override
   public void refuel(double litters) {
        if (litters <= 0){
            System.out.println("Fuel must be a positive number");
        }
        else if (super.getFuelQuantity()+ litters > super.getTankCapacity()){
            System.out.println("Cannot fit fuel in tank");
        }
        else {
            this.setFuelQuantity(getFuelQuantity() + litters * 0.95);
        }

    }
}
