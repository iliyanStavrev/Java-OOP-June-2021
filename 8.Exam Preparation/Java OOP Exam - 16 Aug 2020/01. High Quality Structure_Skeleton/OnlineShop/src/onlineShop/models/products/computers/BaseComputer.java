package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer{

    private List<Component>components;
    private List<Peripheral>peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public void addComponent(Component component) {
       if (components.contains(component)){
           throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                   component.getClass().getSimpleName(),getClass().getSimpleName(),getId()));
       }
       components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = null;
        for (Component c : components) {
            if (c.getClass().getSimpleName().equals(componentType)){
                component = c;
            }
        }
        if (component == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType,getClass().getSimpleName(),getId()));
        }
        components.remove(component);

        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.contains(peripheral)){
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                   peripheral.getClass().getSimpleName(),getClass().getSimpleName(),getId()));
        }
        peripherals.add(peripheral);

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
       Peripheral peripheral = null;
        for (Peripheral p : peripherals) {
            if (p.getClass().getSimpleName().equals(peripheralType)){
                peripheral = p;
            }
        }
        if (peripheral == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType,getClass().getSimpleName(),getId()));
        }
        peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {

        return super.getPrice() + components.stream()
                .mapToDouble(Product::getPrice)
                .sum()  + peripherals.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()){
            return super.getOverallPerformance();
        }
        double sum = 0;
        for (Component component : components) {
            sum += component.getOverallPerformance();
        }
     sum = sum / components.size();
        return sum + super.getOverallPerformance();
    }

    @Override
    public String toString() {
    StringBuilder out = new StringBuilder(System.lineSeparator());
    out.append("Components (" + components.size() + "):").append(System.lineSeparator());
        for (Component component : components) {
            out.append(component.toString()).append(System.lineSeparator());
        }
        double sum = 0.0;
        for (Peripheral peripheral : peripherals) {
            sum += peripheral.getOverallPerformance();
        }
        if (sum == 0){
            sum = 0;
        }
        else {
            sum = sum / peripherals.size();
        }
        out.append(String.format("Peripherals (%d); Average Overall Performance (%.2f):"
                ,peripherals.size(),sum)).append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            out.append(peripheral.toString()).append(System.lineSeparator());
        }

        return super.toString() + out.toString().trim();
    }
}
