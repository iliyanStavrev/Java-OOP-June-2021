package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerRepository implements Repository<Player>{

    private List<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return null;
    }

    @Override
    public void add(Player model) {

    }

    @Override
    public boolean remove(Player model) {
        return false;
    }

    @Override
    public Player findByName(String name) {
        return null;
    }
}
