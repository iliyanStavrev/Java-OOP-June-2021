package carShop1;

import java.io.Serializable;

public interface Car extends Serializable {

    int TIRES = 4;

    String getModel();
    String getColor();
    Integer getHorsePower();
    String countryProduced();

}
