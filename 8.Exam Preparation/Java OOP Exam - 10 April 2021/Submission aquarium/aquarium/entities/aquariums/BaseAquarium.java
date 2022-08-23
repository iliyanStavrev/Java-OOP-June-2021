package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.SaltwaterFish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium{

    private String name;
    private int capacity;
    private List<Decoration>decorations;
    private List<Fish>fish;

    protected BaseAquarium(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public int calculateComfort() {
        return decorations.stream().map(Decoration::getComfort)
                .mapToInt(Integer::intValue).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
     if (this.fish.size() >= capacity){
         throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
     }
     this.fish.add(fish);
  }

    @Override
    public void removeFish(Fish fish) {
     this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
      this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish f : fish) {
           f.eat();
        }
    }

    @Override
    public String getInfo() {
      StringBuilder out = new StringBuilder();
      List<String>strings = new ArrayList<>();
      out.append(name).append(String.format(" (%s):%n",this.getClass().getSimpleName()))
              .append("Fish: ");
      if (fish.isEmpty()){
          out.append("none");
      }
      else {
          for (Fish f : fish) {
              strings.add(f.getName());
          }
          out.append(String.join(" ",strings))
                  .append(System.lineSeparator());
      }
      out.append("Decorations: ").append(this.decorations.size())
              .append(System.lineSeparator())
              .append("Comfort: ").append(this.calculateComfort());


        return out.toString();
    }

    @Override
    public List<Fish> getFish() {
        return this.fish;
    }

    @Override
    public List<Decoration> getDecorations() {
        return this.decorations;
    }
}
