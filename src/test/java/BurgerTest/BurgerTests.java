package BurgerTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTests {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Test
    public void testSetBuns(){
        Burger burger = new Burger();
        burger.setBuns(mockBun);

        assertEquals(mockBun, burger.bun);
    }
    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();

        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);

        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(3.0f);
        when(mockIngredient1.getPrice()).thenReturn(1.0f);
        when(mockIngredient2.getPrice()).thenReturn(2.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 3.0f * 2 + 1.0f + 2.0f;

        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();

        when(mockBun.getName()).thenReturn("BunName");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("SauceName");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("FillingName");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String expectedReceipt = "(==== BunName ====)\n" +
                "= sauce SauceName =\n" +
                "= filling FillingName =\n" +
                "(==== BunName ====)\n" +
                "\nPrice: 0,000000\n";

        String actualReceipt = burger.getReceipt();
        expectedReceipt = expectedReceipt.replace("\n", System.lineSeparator());
        assertEquals(expectedReceipt, actualReceipt);
    }


}
