package HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private  int days;
    private Season season;
    private DiscountType discountType;

    public PriceCalculator(double pricePerDay, int day, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.days = day;
        this.season = season;
        this.discountType = discountType;
    }
    public double calculate(){
        return pricePerDay * days * season.multiplier * (1.0 - discountType.getPercentage() / 100.0);
    }
}
