package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood>{

    private Collection<BakedFood> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return models.stream().filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);

    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(BakedFood bakedFood) {
         this.models.add(bakedFood);
    }
}
