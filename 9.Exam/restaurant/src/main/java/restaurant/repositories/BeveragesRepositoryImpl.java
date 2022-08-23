package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeveragesRepositoryImpl implements BeverageRepository<Beverages> {

    private List<Beverages>entities;

    public BeveragesRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return entities;
    }

    @Override
    public void add(Beverages entity) {
        this.entities.add(entity);
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return this.entities.stream().filter(b -> b.getName().equals(drinkName) &&
                b.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }
}
