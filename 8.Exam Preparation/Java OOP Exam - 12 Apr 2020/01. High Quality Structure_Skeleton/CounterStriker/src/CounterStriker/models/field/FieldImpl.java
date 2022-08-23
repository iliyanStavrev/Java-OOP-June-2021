package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.PlayerRepository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public  class FieldImpl implements Field{


    @Override
    public String start(Collection<Player> players) {

        List<Player> terrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist") && p.isAlive())
                .collect(Collectors.toList());
        List<Player>counterTerrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist") && p.isAlive())
                .collect(Collectors.toList());


        while (!terrorists.isEmpty() && !counterTerrorists.isEmpty()){
            
                for (int i = 0; i < terrorists.size(); i++) {
                    for (int j = 0; j < counterTerrorists.size(); j++) {
                        counterTerrorists.get(j).takeDamage(terrorists.get(i).getGun().fire());
                        if (!counterTerrorists.get(j).isAlive()){
                            counterTerrorists.remove(j);
                        }
                    }
                }
                for (int i = 0; i < counterTerrorists.size(); i++) {
                    for (int j = 0; j < terrorists.size(); j++) {
                        terrorists.get(j).takeDamage(counterTerrorists.get(i).getGun().fire());
                        if (!terrorists.get(j).isAlive()){
                           terrorists.remove(j);
                        }
                    }
                }

        }
         if (terrorists.isEmpty()){
             return COUNTER_TERRORIST_WINS;
         }

        return TERRORIST_WINS;
    }
}
