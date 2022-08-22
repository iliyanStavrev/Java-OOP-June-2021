package FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Team>teamList = new ArrayList<>();
        while (!input.equals("END")){
            String[]tokens = input.split(";");

            switch (tokens[0]){
                case "Team":
                    String teamName = tokens[1];
                    Team team = new Team(teamName);
                    teamList.add(team);
                    break;
                case "Add":
                    boolean isThere = false;
                    teamName = tokens[1];
                    try {

                        Player player =  new Player(tokens[2],Integer.parseInt(tokens[3]),
                                Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),
                                Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]));
                        for (Team t : teamList) {
                            if (t.getName().equals(teamName)){
                                t.addPlayer(player);
                                isThere = true;
                            }
                        }if (!isThere){
                            System.out.printf("Team %s does not exist.%n",teamName);
                        }
                    }catch (IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }

                    break;
                case "Remove":
                  String  nameTeam = tokens[1];
                  String  playerName = tokens[2];
                  try {
                      for (Team t : teamList) {
                          if (t.getName().equals(nameTeam)){
                              t.removePlayer(playerName);
                          }
                      }
                  }catch (IllegalArgumentException ex){
                      System.out.println(ex.getMessage());
                  }
                    break;
                case "Rating":
                    boolean doesExit = false;
                    teamName = tokens[1];
                    for (Team t : teamList) {
                        if (t.getName().equals(teamName)){
                            System.out.println(t.toString());
                            doesExit = true;
                            break;
                        }
                    }
                    if (!doesExit){
                        System.out.printf("Team %s does not exist.",teamName);
                    }
                    break;

            }
            input = scanner.nextLine();
        }
    }
}
