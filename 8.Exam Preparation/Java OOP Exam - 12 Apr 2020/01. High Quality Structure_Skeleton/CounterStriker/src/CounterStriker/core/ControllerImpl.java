package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.*;

public class ControllerImpl implements Controller{

    private GunRepository guns;
    private List<Player> players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new ArrayList<>();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        if (type.equals("Pistol")){
           guns.add(new Pistol(name, bulletsCount));
        }
        else if (type.equals("Rifle")){
           guns.add(new Rifle(name, bulletsCount));
        }
        else {
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_GUN,name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = guns.findByName(gunName);
        Player player = null;
        if (gun == null){
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }

        if (type.equals("Terrorist")){
            player = new Terrorist(username,health,armor,gun);
        }
        else if (type.equals("CounterTerrorist")){
         player = new CounterTerrorist(username,health,armor,gun);
        }
        else {
         throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
         players.add(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER,username);
    }

    @Override
    public String startGame() {

        return field.start(players);
    }

    @Override
    public String report() {
        StringBuilder out = new StringBuilder();
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparing(Player::getUsername))
                .sorted(Comparator.comparing(Player::getHealth))
                .sorted(Comparator.comparing(p -> p.getClass().getSimpleName()))
                .collect(Collectors.toList());
        for (Player sortedPlayer : sortedPlayers) {
            out.append(String.format("%s: %s",sortedPlayer.getClass().getSimpleName(),
                    sortedPlayer.getUsername())).append(System.lineSeparator())
                    .append("--Health: " + sortedPlayer.getHealth()).append(System.lineSeparator())
                    .append("--Armor " + sortedPlayer.getArmor()).append(System.lineSeparator())
                    .append("--Gun " + sortedPlayer.getGun().getName()).append(System.lineSeparator());
        }

        return out.toString().trim();
    }
}
