package ferrari;

public class Ferrari implements Car{

    private String driverName;
    final static String MODEL = "488-Spider";

    public Ferrari(String name) {
        this.driverName = name;

    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "brum-brum-brum-brrrrr";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s",this.MODEL,brakes(),gas(),this.driverName);
    }
}
