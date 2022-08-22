package FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name){
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void addPlayer(Player player){
        this.players.add(player);
    }
    public void removePlayer(String name){
        boolean isFound = false;

        for (Player player : players) {
            if (name.equals(player.getName())){
                players.remove(player);
                isFound = true;
                break;
            }
        }
        if (!isFound){
           throw new IllegalArgumentException(String.format("Player %s is not in %s team.",name,this.name));
        }
    }
    public double getRating(){
       return players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average().orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("%s - %.0f",this.name,this.getRating());
    }
}
