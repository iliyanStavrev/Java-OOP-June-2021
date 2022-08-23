package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

         List<Identifiable>identifiables = new ArrayList<>();
        while (!input.equals("End")){
            String[]tokens = input.split("\\s+");
            switch (tokens.length){
                case 2:
                    String model = tokens[0];
                    String id = tokens[1];
                   Identifiable robot = new Robot(id,model);
                   identifiables.add(robot);
                    break;
                case 3:
                    String name = tokens[0];
                    int age = Integer.parseInt(tokens[1]);
                    id = tokens[2];
                    Identifiable citizen = new Citizen(name,age,id);
                    identifiables.add(citizen);
                    break;
            }

            input = scanner.nextLine();
        }
        String lastDigit = scanner.nextLine();
        for (Identifiable identifiable : identifiables) {
           if (identifiable.getId().endsWith(lastDigit)){
               System.out.println(identifiable.getId());
           }
        }
    }
}
