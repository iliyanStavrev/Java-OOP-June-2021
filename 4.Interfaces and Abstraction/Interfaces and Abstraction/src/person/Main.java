package person;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      int num = Integer.parseInt(scanner.nextLine());
      List<Buyer>buyers = new ArrayList<>();
      while (num-- > 0){
          String[] tokens = scanner.nextLine().split("\\s+");
          if (tokens.length == 3){
              Rebel rebel = new Rebel(tokens[0],Integer.parseInt(tokens[1]),tokens[2]);
              buyers.add(rebel);
          }
          else {
              Citizen citizen = new Citizen(tokens[0],Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
              buyers.add(citizen);
          }
      }
      String input = scanner.nextLine();
      int total = 0;
      while (!input.equals("End")){
          for (Buyer buyer : buyers) {
              if (buyer.getName().equals(input)){
                 buyer.buyFood();
                  total += buyer.getFood();
                  break;
              }

          }
          input = scanner.nextLine();
      }
        System.out.println(total);

    }
}
