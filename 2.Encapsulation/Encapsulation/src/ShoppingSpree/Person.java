package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product>products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }
    private void setName(String name) {
        if (name.trim().length() < 1){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public void buyProduct (Product product){
        if (money >= product.getCost()){
            products.add(product);
            setMoney(money - product.getCost());
            System.out.printf("%s bought %s%n",name,product.getName());
        }
        else {
            System.out.printf("%s can't afford %s%n",name,product.getName());
        }
    }

    @Override
    public String toString() {
       String output = this.products.isEmpty()
               ?"Nothing bought"
               :this.products.stream()
               .map(Product::getName)
               .collect(Collectors.joining(", "));
       return name + " - " + output;
    }
}
