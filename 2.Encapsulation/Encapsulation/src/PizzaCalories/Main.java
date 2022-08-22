package PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[]firstLine = scanner.nextLine().split("\\s+");
        String[]secondLine = scanner.nextLine().split("\\s+");

        Pizza pizza = new Pizza(firstLine[1],Integer.parseInt(firstLine[2]));
        Dough dough = new Dough(secondLine[1],secondLine[2],Double.parseDouble(secondLine[3]));

        pizza.setDough(dough);

        String input = scanner.nextLine();
        while (!input.equals("END")){
            String[]tokens = input.split("\\s+");
            Topping topping = new Topping(tokens[1],Double.parseDouble(tokens[2]));

            pizza.addTopping(topping);
            input = scanner.nextLine();
        }
        System.out.println(pizza.toString());
    }
}
