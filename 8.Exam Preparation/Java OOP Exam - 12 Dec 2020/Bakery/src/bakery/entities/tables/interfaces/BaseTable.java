package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseTable implements Table{

    private Collection<BakedFood>foodOrders;
    private Collection<Drink>drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        foodOrders = new ArrayList<>();
        drinkOrders = new ArrayList<>();
        price = 0.0;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0){
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public Collection<BakedFood> getFoodOrders() {
        return foodOrders;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.price = numberOfPeople * pricePerPerson;
        isReserved = true;

    }

    @Override
    public void orderFood(BakedFood food) {
    this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
     this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double bill = this.getPrice();
        double sumFoods = foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        bill += sumFoods;
        double sumDrinks = drinkOrders.stream().mapToDouble(Drink::getPrice).sum();
        bill += sumDrinks;
        return bill;
    }

    @Override
    public void clear() {
       foodOrders.clear();
       drinkOrders.clear();
       this.price = 0;
       isReserved = false;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%nType: %s%nCapacity: %d%nPrice per Person: %.2f"
        ,tableNumber,this.getClass().getSimpleName(),capacity,pricePerPerson);
    }
}
