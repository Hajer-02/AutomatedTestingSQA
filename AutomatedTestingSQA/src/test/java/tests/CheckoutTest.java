package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutProcess() {
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add a product to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Go to cart
        driver.findElement(By.id("shopping_cart_container")).click();

        // Proceed to checkout
        driver.findElement(By.id("checkout")).click();

        // Fill checkout info
        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Finish checkout
        driver.findElement(By.id("finish")).click();

        // Explicit wait for success page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        String successMessage = driver.findElement(By.className("complete-header")).getText();
        // Fix assertion mismatch: match the exact case and punctuation
        assertEquals("Thank you for your order!", successMessage);
    }
}
