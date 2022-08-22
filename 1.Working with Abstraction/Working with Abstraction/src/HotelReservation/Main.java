package HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        runHoliday(scanner);

    }

    private static void runHoliday(Scanner scanner) {
        String []tokens = scanner.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(tokens[0]);
        int days = Integer.parseInt(tokens[1]);
        String season = tokens[2];
        String discount = tokens[3];

        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay,days,
                Season.valueOf(season.toUpperCase()),DiscountType.valueOf(discount.toUpperCase()));
        System.out.printf("%.2f",priceCalculator.calculate());
    }
}
