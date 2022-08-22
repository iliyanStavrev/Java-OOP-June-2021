package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person>personList = new ArrayList<>();
        String[]firstLine = scanner.nextLine().split(";");
        for (int i = 0; i < firstLine.length; i++) {
            String[]tokens = firstLine[i].split("=");
            String name = tokens[0];
            double money = Double.parseDouble(tokens[1]);
            Person person = new Person(name,money);
            personList.add(person);
        }
        List<Product>productList = new ArrayList<>();
        String[]secondLine = scanner.nextLine().split(";");
        for (int i = 0; i < secondLine.length; i++) {
            String[]tokens = secondLine[i].split("=");
            String productName = tokens[0];
            double cost = Double.parseDouble(tokens[1]);
            Product product = new Product(productName,cost);
            productList.add(product);
        }
        String input = scanner.nextLine();
        while (!input.equals("END")){
            String[]tokens = input.split("\\s+");
            String name = tokens[0];
            String products = tokens[1];
            for (Person person : personList) {
                if (name.equals(person.getName())){
                    for (Product product : productList) {
                       if (products.equals(product.getName())){
                           person.buyProduct(product);
                           break;
                       }
                    }
                }
            }
            input = scanner.nextLine();
        }
        for (Person person : personList) {
            System.out.println(person.toString());
        }

    }
}
