package vehicles;

import java.text.DecimalFormat;

public class Bus extends Vehicle{

    private static final boolean DEFAULT_IS_EMPTY = true;
     private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption,int tankCapacity) {
        super(fuelQuantity, fuelConsumption,tankCapacity);
        setEmpty(DEFAULT_IS_EMPTY);
    }
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        if (!isEmpty){
            super.setFuelConsumption(fuelConsumption + 1.4);
        }
    }

    @Override
    public String drive(double distance) {
        return "Bus " + super.drive(distance);
    }
}
