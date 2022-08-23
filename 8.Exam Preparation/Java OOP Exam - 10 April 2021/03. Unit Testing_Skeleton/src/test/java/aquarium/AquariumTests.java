package aquarium;

import org.junit.*;
import org.junit.Test;

public class AquariumTests {


    @Test(expected = NullPointerException.class)
    public void setNameWhenNull(){

        Aquarium aquarium = new Aquarium(null,10);
    }
    @Test(expected = NullPointerException.class)
    public void setNameWhenIsEmpty(){

        Aquarium aquarium = new Aquarium(" ",10);
    }
    @Test(expected =IllegalArgumentException.class)
    public void setCapacityWhenIsBelowZero(){

        Aquarium aquarium = new Aquarium("asd",-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addNameWhenIsFull(){

        Aquarium aquarium = new Aquarium("asd",1);
        aquarium.add(new Fish("sdf"));
        aquarium.add(new Fish("sdfg"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFishWhenIsNull(){
        Aquarium aquarium = new Aquarium("asd",2);
        aquarium.add(new Fish("fish"));
        aquarium.remove("null");

    }
    @Test
    public void removeFishTrue(){
        Aquarium aquarium = new Aquarium("asd",2);
        aquarium.add(new Fish("fish"));
        aquarium.remove("fish");
       Assert.assertEquals(0,aquarium.getCount());

    }
    @Test(expected = IllegalArgumentException.class)
    public void sellFishWhenIsNull(){
        Aquarium aquarium = new Aquarium("asd",2);
        aquarium.add(new Fish("fish"));
        aquarium.sellFish("null");

    }
    @Test
    public void sellFishWhenIsTrue(){
        Fish fish = new Fish("fish");
        Aquarium aquarium = new Aquarium("asd",2);
        aquarium.add(fish);

      Assert.assertEquals(fish,aquarium.sellFish("fish"));
    }
    @Test
    public void sellFishSetAvailable(){
        Fish fish = new Fish("fish");
        Aquarium aquarium = new Aquarium("asd",2);
        aquarium.add(fish);
      aquarium.sellFish("fish");
      Assert.assertFalse(fish.isAvailable());
    }
@Test
    public void getCountTest(){
        Aquarium aquarium = new Aquarium("asd",10);
        aquarium.add(new Fish("sdf"));
        Assert.assertEquals(1,aquarium.getCount());
    }
  @Test
  public void reportTest(){
      Aquarium aquarium = new Aquarium("asd",10);
      aquarium.add(new Fish("sdf"));
      Assert.assertEquals("Fish available at asd: sdf",aquarium.report());
  }
  
}

