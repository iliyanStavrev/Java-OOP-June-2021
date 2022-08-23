package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import static CounterStriker.common.ExceptionMessages.*;

public class PlayerRepository implements Repository<Player>{

    private List<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }


    @Override
    public List<Player> getGuns() {
        return players;
    }

    @Override
    public void add(Player player) {
     if (player == null){
         throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
     }
     players.add(player);
    }

    @Override
    public boolean remove(Player player) {

        return players.remove(player);
    }

    @Override
    public Player findByName(String name) {

        return players.stream().filter(p -> p.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }
}
