import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;

import static org.junit.Assert.assertEquals;

public class DummyTest {
    private static final int HEALTH = 10;
    private static final int EXPERIENCE = 13;
    private Dummy aliveDummy;
    private Dummy deadDummy;
    @Before
    public void setUp(){
        this.aliveDummy = new Dummy(HEALTH,EXPERIENCE);
        this.deadDummy = new Dummy(0,EXPERIENCE);
    }
@Test
    public void LosesHealthWhenAttacked(){

    aliveDummy.takeAttack(3);
    assertEquals(HEALTH - 3,aliveDummy.getHealth());
}
@Test(expected = IllegalStateException.class)
    public void DeadDummyThrowsExceptionWhenAttacked(){

    deadDummy.takeAttack(11);

}
@Test
    public void testDeadDummyCanGiveXP(){

    int xP = deadDummy.giveExperience();
    assertEquals(EXPERIENCE,xP);
}
@Test(expected = IllegalStateException.class)
    public void testAliveDummyCantGiveXP(){

    aliveDummy.giveExperience();
}

}
