package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Animal>animals = new ArrayList<>();

        while (!input.equals("Beast!")){
            String[] secondLine = scanner.nextLine().split("\\s+");
            String name = secondLine[0];
            int age = Integer.parseInt(secondLine[1]);

            try {
                Animal animal = null;
                switch (input) {
                    case "Dog":
                        String gender = secondLine[2];
                        animal = new Dog(name, age, gender);
                        break;
                    case "Cat":
                        gender = secondLine[2];
                        animal = new Cat(name, age, gender);
                        break;
                    case "Frog":
                       gender = secondLine[2];
                        animal = new Frog(name, age, gender);
                        break;
                    case "Kitten":
                        if (secondLine.length == 3){
                            gender = secondLine[2];
                        }
                        animal = new Kitten(name, age);
                        break;
                    case "Tomcat":
                        if (secondLine.length == 3){
                            gender = secondLine[2];
                        }
                        animal = new Tomcat(name, age);
                        break;
                }
                animals.add(animal);
            }catch (IllegalArgumentException ex){
                System.out.print(ex.getMessage());
            }

            input = scanner.nextLine();
        }
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}
