package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     String input = scanner.nextLine();

     List<Animal> animalList = new ArrayList<>();
     while (!input.equals("End")){
         String []animalInfo = input.split("\\s+");
         String type = animalInfo[0];
         String name = animalInfo[1];
         double weight = Double.parseDouble(animalInfo[2]);
         String livingRegion = animalInfo[3];

         Animal animal = null;
         switch (type){
             case "Mouse":
                 animal = new Mouse(type,name,weight,livingRegion);
                 break;
             case "Zebra":
                 animal = new Zebra(type,name,weight,livingRegion);
                 break;
             case "Cat":
                 String breed = animalInfo[4];
                 animal = new Cat(type,name,weight,livingRegion,breed);
                 break;
             case "Tiger":
                 animal = new Tiger(type,name,weight,livingRegion);
                 break;
         }
             String[] foodInfo = scanner.nextLine().split("\\s+");
              Food food = null;
              String foodType = foodInfo[0];
              int foodQuantity = Integer.parseInt(foodInfo[1]);
              switch (foodType){
                  case "Vegetable":
                   food = new Vegetable(foodQuantity);
                      break;
                  case "Meat":
                      food = new Meat(foodQuantity);
                      break;
              }
              animal.makeSound();
           animal.eat(food);

        animalList.add(animal);


         input = scanner.nextLine();
     }
     animalList.forEach(System.out::println);
    }
}
