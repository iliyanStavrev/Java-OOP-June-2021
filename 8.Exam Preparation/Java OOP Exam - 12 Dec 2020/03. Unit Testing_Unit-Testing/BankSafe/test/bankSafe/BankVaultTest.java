package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {

   private BankVault bankVault;
   
    @Before
    public void setUp(){
       this.bankVault = new BankVault();
    }
    @Test
    public void testConstructor(){
        Map<String,Item>expectedMap = new LinkedHashMap<>();
        expectedMap.put("A1", null);
        expectedMap.put("A2", null);
        expectedMap.put("A3", null);
        expectedMap.put("A4", null);
        expectedMap.put("B1", null);
        expectedMap.put("B2", null);
        expectedMap.put("B3", null);
        expectedMap.put("B4", null);
        expectedMap.put("C1", null);
        expectedMap.put("C2", null);
        expectedMap.put("C3", null);
        expectedMap.put("C4", null);
        Map<String,Item>actualMap = bankVault.getVaultCells();
        Assert.assertEquals(expectedMap,actualMap);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addItemWhenCellDoesNotExist() throws OperationNotSupportedException {

        Item item = new Item("asd","123");
        bankVault.addItem("cell",item);

    }
    @Test(expected = IllegalArgumentException.class)
    public void addItemWhenCellIsAlreadyTaken() throws OperationNotSupportedException {
        Item item = new Item("asd","123");
        bankVault.addItem("A1",item);
        bankVault.addItem("A1",new Item("as","12"));
    }
    @Test(expected = OperationNotSupportedException.class)
    public void addItemWhenExist() throws OperationNotSupportedException {
                      Item item = new Item("asd","123");
     bankVault.addItem("A1",item);
     bankVault.addItem("A2",item);

    }

@Test
    public void addItemCorrect() throws OperationNotSupportedException {
        Item item = new Item("asd","123");
        Assert.assertEquals("Item:123 saved successfully!",bankVault.addItem("C2",item));
}
@Test(expected = IllegalArgumentException.class)
    public void removeWhenItemDoesNotExist(){
    Item item = new Item("asd","123");
    bankVault.removeItem("A1",item);
}
@Test(expected = IllegalArgumentException.class)
    public void removeWhenCellDoesNotExist() throws OperationNotSupportedException {
    Item item = new Item("asd","123");
    bankVault.removeItem("W1",item);
}
@Test(expected = IllegalArgumentException.class)
public void removeItemWhenIsNotEqual() throws OperationNotSupportedException {
    Item item = new Item("asd", "123");
    Item item1 = new Item("as", "12");
    bankVault.addItem("A1",item);
    bankVault.removeItem("A1",item1);

}
 @Test
 public void removeTrue() throws OperationNotSupportedException {
     Item item = new Item("asd", "123");
     bankVault.addItem("A1", item);

     Assert.assertEquals("Remove item:123 successfully!", bankVault.removeItem("A1", item));
 }

    @Test
    public void removeItemTrue() throws OperationNotSupportedException {
        Item item = new Item("asd","123");
        bankVault.addItem("A1",item);
        bankVault.removeItem("A1",item);

        Assert.assertNull(bankVault.getVaultCells().get("A1"));
    }

}