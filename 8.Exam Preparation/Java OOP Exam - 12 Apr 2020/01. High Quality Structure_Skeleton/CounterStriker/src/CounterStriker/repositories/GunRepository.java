package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

import static CounterStriker.common.ExceptionMessages.*;

public class GunRepository implements Repository<Gun>{

    private Collection<Gun> guns;

    public GunRepository() {
        this.guns = new ArrayList<>();
    }

    @Override
    public Collection getGuns() {
        return guns;
    }

    @Override
    public void add(Gun gun) {
        if (gun == null){
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }
        guns.add(gun);
    }

    @Override
    public boolean remove(Gun gun) {

        return guns.remove(gun);
    }

    @Override
    public Gun findByName(String name) {
        return guns.stream().filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
