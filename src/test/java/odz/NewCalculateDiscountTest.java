package odz;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NewCalculateDiscountTest {

    private Item item;
    private int expected;

    public NewCalculateDiscountTest(int expected, Item item) {
        this.item = item;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                /*---------- Discount for REGULAR type ----------*/
                {0, new Item("title", 1, 1, Item.ItemType.REGULAR)},
                {0, new Item("title", 1, 9, Item.ItemType.REGULAR)},
                {1, new Item("title", 1, 10, Item.ItemType.REGULAR)},
                {20, new Item("title", 1, 200, Item.ItemType.REGULAR)},
                /*---------- Discount for SECOND FREE type ----------*/
                {0, new Item("title", 1, 1, Item.ItemType.SECOND_FREE)},
                {50, new Item("title", 1, 9, Item.ItemType.SECOND_FREE)},
                {52, new Item("title", 1, 25, Item.ItemType.SECOND_FREE)},
                /*---------- Discount for NEW items tests ----------*/
                {0, new Item("title", 1, 1, Item.ItemType.NEW)},
                {0, new Item("title", 1, 100, Item.ItemType.NEW)},
                /*---------- Discount for FOR SALE items ----------*/
                { 90, new Item("title", 1, 1, Item.ItemType.SALE)},
                { 90, new Item("title", 1, 20, Item.ItemType.SALE)},
                { 90, new Item("title", 1, 50, Item.ItemType.SALE)},
                { 90, new Item("title", 1, 200, Item.ItemType.SALE)},
                /*---------- Max possible discount test ----------*/
                { 90, new Item("title", 1, 1000, Item.ItemType.SALE)}
        });
    }

    @Test
    public void discountTest()
    {
        Assert.assertEquals(expected, item.calculateDiscount());
    }

}