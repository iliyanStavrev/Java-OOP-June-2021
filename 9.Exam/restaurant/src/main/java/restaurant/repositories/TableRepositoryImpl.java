package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.Repository;
import restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table> {

    private List<Table>entities;

    public TableRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return entities;
    }

    @Override
    public void add(Table entity) {
          this.entities.add(entity);
    }

    @Override
    public Table byNumber(int number) {

        return this.entities.stream().filter(t -> t.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
