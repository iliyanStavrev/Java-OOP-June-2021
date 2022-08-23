package halfLife;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTests {

    @Test(expected = NullPointerException.class)
    public void setNameWhenNull(){
       Player player = new Player("  ",10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setHealthWhenIsNull(){
        Player player = new Player("ili",-1);
    }
   @Test
    public void takeDamageWhenHealthIsCorrect(){
        Player player = new Player("ili",10);
        player.takeDamage(9);
       Assert.assertEquals(1,player.getHealth());

   }
    @Test(expected = IllegalStateException.class)
    public void takeDamageWhenHealthIsNull(){
        Player player = new Player("ili",10);
        player.takeDamage(11);
        player.takeDamage( player.getHealth());

    }
    @Test(expected = NullPointerException.class)
    public void addGunWhenIsNull(){
        Player player = new Player("ili",10);
        player.addGun(null);
    }
    @Test
    public void addGunWhenIsCorrect(){
        Player player = new Player("ili",10);
        player.addGun(new Gun("rifle",20));
        Assert.assertEquals(1,player.getGuns().size());
    }
    @Test
    public void removeGunWhenIsCorrect(){
        Player player = new Player("ili",10);
        Gun gun = new Gun("rifle", 20);
        player.addGun(gun);

        Assert.assertTrue(player.removeGun(gun));
    }
    @Test
    public void getGunWhenIsCorrect(){
        Player player = new Player("ili",10);
        Gun gun = new Gun("rifle", 20);
        player.addGun(gun);

        Assert.assertEquals(gun,player.getGun("rifle"));
    }
}
