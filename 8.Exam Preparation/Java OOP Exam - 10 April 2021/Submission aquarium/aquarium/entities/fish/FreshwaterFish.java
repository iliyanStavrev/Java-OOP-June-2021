package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{
    private static int SIZE = 3;
    private int size;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.size = SIZE;
    }

    @Override
    public void eat() {
      this.size += 3;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
