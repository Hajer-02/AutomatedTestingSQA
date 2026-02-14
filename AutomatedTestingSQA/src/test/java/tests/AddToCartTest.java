package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddProductToCart() {
        System.out.println("Opening SauceDemo website...");
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Logging in...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        System.out.println("Adding product to cart...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();

        System.out.println("Opening cart...");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();

        System.out.println("Checking if product is in the cart...");
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        String itemName = cartItem.getText();
        System.out.println("Item in cart: " + itemName);

        assertTrue(itemName.toLowerCase().contains("backpack"), "Cart should contain the selected item.");

        System.out.println("✅ Test completed successfully!");
    }
}
