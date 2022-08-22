package NeedForSpeed;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FamilyCar car = new FamilyCar(36,125);
        car.drive(40);
        System.out.println(car.getFuel());
        SportCar sportCar = new SportCar(50,235);
        sportCar.drive(4);
        System.out.println(sportCar.getFuel());
        System.out.println(sportCar.getFuelConsumption());
    }
}
