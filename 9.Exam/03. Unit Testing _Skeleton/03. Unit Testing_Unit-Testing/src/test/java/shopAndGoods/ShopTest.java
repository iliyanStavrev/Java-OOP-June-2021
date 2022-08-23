package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {


    private Shop shop;

    @Before
    public void setUp(){
        this.shop = new Shop();
    }
    @Test
    public void testConstructor(){
        Map<String,Goods> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Shelves1", null);
        expectedMap.put("Shelves2", null);
        expectedMap.put("Shelves3", null);
        expectedMap.put("Shelves4", null);
        expectedMap.put("Shelves5", null);
        expectedMap.put("Shelves6", null);
        expectedMap.put("Shelves7", null);
        expectedMap.put("Shelves8", null);
        expectedMap.put("Shelves9", null);
        expectedMap.put("Shelves10", null);
        expectedMap.put("Shelves11", null);
        expectedMap.put("Shelves12", null);
        Map<String,Goods>actualMap = shop.getShelves();
        Assert.assertEquals(expectedMap,actualMap);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addItemWhenCellDoesNotExist() throws OperationNotSupportedException {

        Goods item = new Goods("asd","123");
        shop.addGoods("cell",item);

    }
    @Test(expected = IllegalArgumentException.class)
    public void addItemWhenCellIsAlreadyTaken() throws OperationNotSupportedException {
        Goods item = new Goods("as","12");
        shop.addGoods("A1",item);
        shop.addGoods("A1",new Goods("as","12"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void addItemWhenExist() throws OperationNotSupportedException {
        Goods item = new Goods("asd","123");
        shop.addGoods("A1",item);
        shop.addGoods("A2",item);

    }

    @Test
    public void addItemCorrect() throws OperationNotSupportedException {
        Goods item = new Goods("asd","123");
        Assert.assertEquals("Goods: 123 is placed successfully!", shop.addGoods("Shelves2",item));
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeWhenItemDoesNotExist(){
        Goods item = new Goods("asd","123");
        shop.removeGoods("A1",item);
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeWhenCellDoesNotExist() throws OperationNotSupportedException {
        Goods item = new Goods("asd","123");
        shop.removeGoods("W1",item);
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeItemWhenIsNotEqual() throws OperationNotSupportedException {
        Goods item = new Goods("asd", "123");
        Goods item1 = new Goods("as", "12");
        shop.addGoods("A1",item);
        shop.removeGoods("A1",item1);

    }
    @Test
    public void removeTrue() throws OperationNotSupportedException {
        Goods item = new Goods("asd", "123");
        shop.addGoods("Shelves1", item);

        Assert.assertEquals("Goods: 123 is removed successfully!", shop.removeGoods("Shelves1", item));
    }

    @Test
    public void removeItemTrue() throws OperationNotSupportedException {
        Goods item = new Goods("asd","123");
        shop.addGoods("Shelves1",item);
        shop.removeGoods("Shelves1",item);

        Assert.assertNull(shop.getShelves().get("Shelves1"));
    }

}