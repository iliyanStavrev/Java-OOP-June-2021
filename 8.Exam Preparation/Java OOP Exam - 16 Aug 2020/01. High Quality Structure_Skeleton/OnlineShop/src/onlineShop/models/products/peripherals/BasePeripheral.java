package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral{

    private String connectionType;

    protected BasePeripheral(int id, String manufacturer, String model,
                          double price, double overallPerformance,String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }


    @Override
    public String getConnectionType() {
        return connectionType;
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
        return super.getPrice();
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s " +
                "(Id: %s) Connection Type: %s",super.getOverallPerformance(),super.getPrice(),
                               this.getClass().getSimpleName(),super.getManufacturer(),super.getModel(),
                               super.getId(),this.connectionType);
    }
}
