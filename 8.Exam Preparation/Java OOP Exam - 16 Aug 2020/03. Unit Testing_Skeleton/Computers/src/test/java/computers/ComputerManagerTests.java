package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    private Computer computer;
   private ComputerManager computerManager;
    @Before
    public void setUp(){
         computerManager = new ComputerManager();
       computer = new Computer("asus","sdf",230);
    }
    @Test
    public void getComputersTest(){
        computerManager.addComputer(computer);
        List<Computer>actual = new ArrayList<>();
        actual.add(computer);
        Assert.assertEquals(actual,computerManager.getComputers());
    }
    @Test
    public void getCountTest(){
        computerManager.addComputer(computer);
        Assert.assertEquals(1,computerManager.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void addWhenExisting(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("asus","sdf",230));
    }
    @Test
    public  void removeComputerTest(){
        computerManager.addComputer(computer);
        Assert.assertEquals(computer,computerManager.removeComputer("asus","sdf"));
    }
    @Test
    public void remove(){
        computerManager.addComputer(computer);
        computerManager.removeComputer("asus","sdf");
        Assert.assertEquals(0,computerManager.getCount());
    }
   @Test(expected = IllegalArgumentException.class)
    public void getComputerWhenIsNull(){
        computerManager.getComputer("qwe","sdf");
   }
   @Test
    public void getComputer(){
        computerManager.addComputer(computer);
        Assert.assertEquals(computer,computerManager.getComputer("asus","sdf"));
   }
   @Test
    public void getComputersByManufacturer(){
        Computer computer1 = new Computer("asus","234",456);
       List<Computer>expected = new ArrayList<>();
       expected.add(computer);
        expected.add(computer1);
       computerManager.addComputer(computer);
       computerManager.addComputer(computer1);
       List<Computer>actual = computerManager.getComputersByManufacturer("asus");
       Assert.assertEquals(expected,actual);

   }
   @Test(expected = IllegalArgumentException.class)
    public  void validateNullValue(){
        computerManager.addComputer(null);
   }
}