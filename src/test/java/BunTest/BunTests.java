package BunTest;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

import java.util.Random;

public class BunTests {

    private final String bunName = RandomStringUtils.randomAlphabetic(5,15);
    private final Random random = new Random();
    private final float bunPrice = random.nextFloat() + 10;

    @Test
    public void testGetName() {
        Bun bun = new Bun(bunName, bunPrice);

        String expectedName = bunName;
        String actualName = bun.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(bunName, bunPrice);

        float expectedPrice = bunPrice;
        float actualPrice = bun.getPrice();

        Assert.assertEquals(expectedPrice, actualPrice, 0.001);
    }
}
