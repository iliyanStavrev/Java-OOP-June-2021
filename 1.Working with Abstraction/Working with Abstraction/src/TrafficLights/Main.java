package TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[]states = scanner.nextLine().split("\\s+");
        int timesToChange = Integer.parseInt(scanner.nextLine());

        List<TrafficLight>trafficLights = new ArrayList<>();
        for (String state : states) {
            TrafficLight trafficLight = new TrafficLight(TrafficLightState.valueOf(state));
            trafficLights.add(trafficLight);
        }
        for (int i = 0; i < timesToChange; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.update();
                System.out.print(trafficLight + " ");
            }
            System.out.println();
        }
    }
}
