package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private List<Computer> computers;
    private List<Component>components;
    private List<Peripheral>peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null){
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
       if (!computerType.equals("DesktopComputer") && !computerType.equals("Laptop")){
           throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
       }
       if (computerType.equals("DesktopComputer")){
           computers.add(new DesktopComputer(id, manufacturer, model, price));
       }
       else {
           computers.add(new Laptop(id, manufacturer, model, price));
       }

        return String.format(ADDED_COMPUTER,id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType,
                                String manufacturer, String model, double price,
                                double overallPerformance, String connectionType) {
        if (checkComputerId(computerId)){
            if (peripherals.stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null){
                throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
            }
            if (!peripheralType.equals("Headset") && !peripheralType.equals("Keyboard") &&
                    !peripheralType.equals("Monitor") && !peripheralType.equals("Mouse")){
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
            }

            Peripheral peripheral = null;
            switch (peripheralType){
                case "Headset":
                   peripheral = new Headset
                            (id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Keyboard":
                    peripheral = new Keyboard
                            (id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Monitor":
                   peripheral = new Monitor
                (id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Mouse":
                    peripheral = new Mouse
                            (id, manufacturer, model, price, overallPerformance, connectionType);
                    break;

            }
            peripherals.add(peripheral);
            Computer computer = computers.stream().filter(c ->c.getId() == computerId).findAny().get();
            computer.addPeripheral(peripheral);
        }
        return String.format(ADDED_PERIPHERAL,peripheralType,id,computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Peripheral peripheral = null;
        if (checkComputerId(computerId)){
            Computer computer = computers.stream().filter(c -> c.getId() == computerId).findAny().get();
            peripheral = computer.removePeripheral(peripheralType);
            peripherals.remove(peripheral);
            BaseProduct.totalPrice -= peripheral.getPrice();
        }
        return String.format(REMOVED_PERIPHERAL,peripheralType,peripheral.getId());

    }

    @Override
    public String addComponent(int computerId, int id, String componentType,
                               String manufacturer, String model, double price,
                               double overallPerformance, int generation) {
        if (checkComputerId(computerId)){
          if (components.stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null){
              throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
          }
          String out = BaseComponent.class.getSimpleName();
          if (!componentType.equals("CentralProcessingUnit") && !componentType.equals("Motherboard") &&
                  !componentType.equals("PowerSupply") && !componentType.equals("RandomAccessMemory") &&
                  !componentType.equals("SolidStateDrive") && !componentType.equals("VideoCard")){
              throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
          }

          Component component = null;
          switch (componentType){
              case "CentralProcessingUnit":
                  component = new CentralProcessingUnit
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
              case "Motherboard":
                  component = new Motherboard
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
              case "PowerSupply":
                  component = new PowerSupply
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
              case "RandomAccessMemory":
                  component = new RandomAccessMemory
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
              case "SolidStateDrive":
                  component = new SolidStateDrive
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
              case "VideoCard":
                  component = new VideoCard
                          (id, manufacturer, model, price, overallPerformance, generation);
                  break;
          }
          components.add(component);
          Computer computer = computers.stream().filter(c ->c.getId() == computerId).findAny().get();
          computer.addComponent(component);
        }
        return String.format(ADDED_COMPONENT,componentType,id,computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Component component = null;
        if (checkComputerId(computerId)){
            Computer computer = computers.stream().filter(c -> c.getId() == computerId).findAny().get();
           component = computer.removeComponent(componentType);
            components.remove(component);
            BaseProduct.totalPrice -= component.getPrice();
        }

        return String.format(REMOVED_COMPONENT,componentType,component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = null;
        if (checkComputerId(id)){
            computer = computers.stream().filter(c -> c.getId() == id)
                    .findAny().orElse(null);
        }
        computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer computer = null;
        double highest = 0.0;
        for (Computer c : computers) {
          double current = c.getOverallPerformance();
          if (current > highest && budget >= c.getPrice()){
              highest = current;
              computer = c;
          }
        }
        if(computer != null){
            computers.remove(computer);
        }
        else {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER,budget));
        }

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
      Computer computer = computers.stream().filter(c -> c.getId() == id)
              .findFirst()
              .orElse(null);
      if (computer == null){
          throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
      }

        return computer.toString();
    }
    public boolean checkComputerId(int id){
        Computer computer = computers.stream().filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return true;
    }
}
