package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private List<HealthyFood>entities;

    public HealthFoodRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return entities;
    }

    @Override
    public void add(HealthyFood entity) {
       this.entities.add(entity);
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.entities.stream().filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);
    }
}
