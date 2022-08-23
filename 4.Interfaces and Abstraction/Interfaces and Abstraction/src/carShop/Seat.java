package carShop;


public class Seat extends CarImpl implements Sellable{

     private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced,Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires%n" +
                        "%s sells for %f"
                ,super.getModel(),super.countryProduced(), Car.TIRES,super.getModel(),this.price);
    }
}
