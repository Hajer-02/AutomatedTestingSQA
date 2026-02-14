package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutProcess() {
        driver.get("https://www.saucedemo.com/");

        // Log in
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add a product to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open the cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Start checkout
        driver.findElement(By.id("checkout")).click();

        // Explicit wait for checkout page elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for First Name field and fill it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        driver.findElement(By.id("first-name")).sendKeys("John");

        // Wait for Last Name field and fill it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        driver.findElement(By.id("last-name")).sendKeys("Doe");

        // Wait for Postal Code field and fill it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        // Continue checkout
        driver.findElement(By.id("continue")).click();

        // Wait for Finish button and click
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        driver.findElement(By.id("finish")).click();

        // Verify order completion
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String completionText = driver.findElement(By.className("complete-header")).getText();
        System.out.println("Checkout completed: " + completionText);
    }
}
