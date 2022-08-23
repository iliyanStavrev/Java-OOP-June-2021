package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{
    private static int SIZE = 5;
    private int size;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.size = SIZE;
    }

    @Override
    public void eat() {
        this.size += 2;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
