package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddProductToCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add product to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // Check if product is in the cart
        String cartItem = driver.findElement(By.className("inventory_item_name")).getText();
        assertTrue(cartItem.contains("Backpack"), "Cart should contain the selected item.");
    }
}
