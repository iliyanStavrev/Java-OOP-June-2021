package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {

    private List<HealthyFood> healthyFood;
    private List<Beverages>beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.allPeople = 0.0;
    }

    public void setSize(int size) {
        if (size < 0){
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.allPeople = numberOfPeople * pricePerPerson;
        isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {

      this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
           this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double bill = this.allPeople();
        double sumFoods = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        bill += sumFoods;
        double sumDrinks = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        bill += sumDrinks;
        return bill;

    }

    @Override
    public void clear() {
     this.healthyFood.clear();
     this.beverages.clear();
     this.allPeople = 0;
     isReservedTable = false;
    }

    @Override
    public String tableInformation() {

        return String.format("Table - %d%n" +
                "Size - %d%n" +
                "Type - %s%n" +
                "All price - %.2f",number,size,getClass().getSimpleName(),pricePerPerson);
    }
}
