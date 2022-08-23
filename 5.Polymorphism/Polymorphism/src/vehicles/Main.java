package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

     String[]carInfo = scanner.nextLine().split("\\s+");
     Vehicle car = new Car(Double.parseDouble(carInfo[1]),Double.parseDouble(carInfo[2]),
             Integer.parseInt(carInfo[3]));
        String[]truckInfo = scanner.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]),Double.parseDouble(truckInfo[2]),
                Integer.parseInt(truckInfo[3]));
        String[]busInfo = scanner.nextLine().split("\\s+");
        Vehicle bus = new Bus(Double.parseDouble(busInfo[1]),Double.parseDouble(busInfo[2]),
                Integer.parseInt(busInfo[3]));

        int num = Integer.parseInt(scanner.nextLine());

        while (num-- > 0){
            String []tokens = scanner.nextLine().split("\\s+");
            if (tokens[0].equals("Drive")){
              if (tokens[1].equals("Car")){
                  System.out.println(car.drive(Double.parseDouble(tokens[2])));
              }
              else if (tokens[1].equals("Bus")){
                  System.out.println(bus.drive(Double.parseDouble(tokens[2])));
              }
              else {
                  System.out.println(truck.drive(Double.parseDouble(tokens[2])));
              }
            }
            else if (tokens[0].equals("Refuel")){
                if (tokens[1].equals("Car")){
                    car.refuel(Double.parseDouble(tokens[2]));
                }
                else if (tokens[1].equals("Bus")){
                    bus.refuel(Double.parseDouble(tokens[2]));
                }
                else {
                    truck.refuel(Double.parseDouble(tokens[2]));
                }
            }
            else if (tokens[0].equals("DriveEmpty")){
                System.out.println(bus.drive(Double.parseDouble(tokens[2])));
            }
        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
        System.out.println(bus.toString());
    }
}
