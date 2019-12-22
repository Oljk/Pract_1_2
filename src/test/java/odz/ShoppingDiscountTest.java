package odz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class ShoppingDiscountTest {

    private static final int[] Q_VALUES = {1, 9, 10, 50};
    private static final Item.ItemType[] T_VALUES =
            {
                    Item.ItemType.NEW,
                    Item.ItemType.REGULAR,
                    Item.ItemType.SALE,
                    Item.ItemType.SECOND_FREE
            };
    private static final int[][] D_VALUES =
            {
                    { 0, 0, 0, 0 },
                    { 0, 0, 1, 5},
                    { 90 },
                    { 0, 50, 51, 55}
            };
    private Item.ItemType _type;
    private int _quantity;
    private int _discount;
    public ShoppingDiscountTest(Item.ItemType type, int quantity, int discount){
        _type = type;
        _quantity = quantity;
        _discount = discount;
    }

    @Parameterized.Parameters
    public static Collection getTypeQuantityPairs()
    {
        Collection pairs = new ArrayList();
        for (int q = 0; q < Q_VALUES.length; q++)
            for (int t = 0; t < T_VALUES.length; t++)
                pairs.add(new Object[] {
                        T_VALUES[t],
                        Q_VALUES[q],
                        (D_VALUES[t].length > q)
                                ? D_VALUES[t][q]
                                : D_VALUES[t][D_VALUES[t].length - 1]
                });
        return pairs;
    }

    @Test
    public void calculateDiscount(){
        assertEquals("type: "+ _type + ", quantity: " + _quantity,
                _discount,
                        new Item("Title", 5.99f, _quantity, _type).calculateDiscount()
                ,
                0.01f
        );
    }

}