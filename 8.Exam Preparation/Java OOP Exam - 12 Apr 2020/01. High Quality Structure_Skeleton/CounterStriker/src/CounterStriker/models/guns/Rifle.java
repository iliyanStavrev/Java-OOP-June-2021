package CounterStriker.models.guns;

public class Rifle extends GunImpl{

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {

        return 10;
    }
}
