package bakery.core;

import bakery.common.ExceptionMessages.*;
import bakery.common.OutputMessages.*;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.bakedFoods.interfaces.Bread;
import bakery.entities.bakedFoods.interfaces.Cake;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.drinks.interfaces.Tea;
import bakery.entities.drinks.interfaces.Water;
import bakery.entities.tables.interfaces.InsideTable;
import bakery.entities.tables.interfaces.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.List;
import java.util.stream.Collectors;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {


    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository,
                          DrinkRepository<Drink> drinkRepository,
                          TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.totalIncome = 0.0;

    }


    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = foodRepository.getByName(name);
        if (bakedFood != null){
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,type,name));
        }
       if (type.equals("Bread")){
        bakedFood = new Bread(name, price);

       }
       else if (type.equals("Cake")){
       bakedFood = new Cake(name, price);
       }
      foodRepository.add(bakedFood);

        return String.format(FOOD_ADDED,name,type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
     Drink drink = drinkRepository.getByNameAndBrand(name,brand);

     if (drink != null){
         throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,type,name));
     }
     if (type.equals("Tea")){
         drink = new Tea(name,portion,brand);
     }
     else if (type.equals("Water")){
         drink = new Water(name,portion,brand);
     }
     drinkRepository.add(drink);

        return String.format(DRINK_ADDED,name,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table != null){
            throw new IllegalArgumentException(String.format(TABLE_EXIST,tableNumber));
        }
        if (type.equals("InsideTable")){
            table = new InsideTable(tableNumber,capacity);
        }
        else if (type.equals("OutsideTable")){
            table = new OutsideTable(tableNumber, capacity);
        }
        tableRepository.add(table);

        return String.format(TABLE_ADDED,tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {

        Table table = tableRepository.getAll().stream().
                filter(t -> t.getCapacity() >= numberOfPeople)
                .filter(t -> !t.isReserved())
                .findFirst()
                .orElse(null);

        if (table == null){
            return String.format(RESERVATION_NOT_POSSIBLE,numberOfPeople);
        }
            table.reserve(numberOfPeople);

        return String.format(TABLE_RESERVED,table.getTableNumber(),numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
      Table table = tableRepository.getByNumber(tableNumber);

      if (table == null || !table.isReserved()){
          return String.format(WRONG_TABLE_NUMBER,tableNumber);
      }
      BakedFood bakedFood = foodRepository.getByName(foodName);
      if (bakedFood == null){
          return String.format(NONE_EXISTENT_FOOD,foodName);
      }
      table.orderFood(bakedFood);

        return String.format(FOOD_ORDER_SUCCESSFUL,tableNumber,foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {

        Table table = tableRepository.getByNumber(tableNumber);

        if (table == null || !table.isReserved()){
            return String.format(WRONG_TABLE_NUMBER,tableNumber);
        }

       Drink drink = drinkRepository.getByNameAndBrand(drinkName,drinkBrand);

       if (drink == null){
           return String.format(NON_EXISTENT_DRINK,drinkName,drinkBrand);
       }
       
        table.orderDrink(drink);

        return String.format(DRINK_ORDER_SUCCESSFUL,tableNumber,drinkName,drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = tableRepository.getByNumber(tableNumber);
        double bill =  table.getBill();
        totalIncome += bill;
        table.clear();

        return String.format(BILL,tableNumber,bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder out = new StringBuilder();
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved()){
                out.append(table.getFreeTableInfo())
                   .append(System.lineSeparator());
            }
        }
        return out.toString().trim();
    }

    @Override
    public String getTotalIncome() {

        return String.format(TOTAL_INCOME,totalIncome);
    }
}
