package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

   private DecorationRepository decorations;
   private List<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (aquariumType.equals("FreshwaterAquarium")){
            aquariums.add(new FreshwaterAquarium(aquariumName));
        }
        else if (aquariumType.equals("SaltwaterAquarium")){
            aquariums.add(new SaltwaterAquarium(aquariumName));
        }
        else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType);
    }

    @Override
    public String addDecoration(String type) {

        if (type.equals("Ornament")){
            decorations.add(new Ornament());
        }
        else if (type.equals("Plant")){
            decorations.add(new Plant());
        }
        else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE,type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null){
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND,decorationType));
        }
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                aquarium.addDecoration(decoration);
                decorations.remove(decoration);
                break;
            }
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM
                ,decorationType,aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType,
                          String fishName, String fishSpecies, double price) {
        boolean canLive = false;
        Aquarium aquarium = null;
        for (Aquarium aqua : aquariums) {
            if (aqua.getName().equals(aquariumName)) {
                aquarium = aqua;
            }
        }
            if (fishType.equals("FreshwaterFish")) {
                if (aquarium.getClass().getSimpleName().startsWith("Freshwater")) {
                      aquarium.addFish(new FreshwaterFish(fishName, fishSpecies, price));
                      canLive = true;
                }
            }
            else if (fishType.equals("SaltwaterFish")) {
                if (aquarium.getClass().getSimpleName().startsWith("Saltwater")) {
                      aquarium.addFish(new SaltwaterFish(fishName, fishSpecies, price));
                      canLive = true;
                }
            }
            else {
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
            }
            if (!canLive){
               return WATER_NOT_SUITABLE;
            }
        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = null;
        for (Aquarium aq : aquariums) {
            if (aq.getName().equals(aquariumName)){
             aquarium = aq;
            }
        }
        aquarium.feed();
        return String.format(FISH_FED,aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst().get();
        double sumFish = aquarium.getFish().stream()
                .mapToDouble(Fish::getPrice).sum();
        double sumDecorations = aquarium.getDecorations().stream()
                .mapToDouble(Decoration::getPrice).sum();

        return String.format(VALUE_AQUARIUM,aquariumName,sumFish + sumDecorations);
    }

    @Override
    public String report() {
        StringBuilder out = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            out.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return out.toString();
    }
}
