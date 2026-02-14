package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutProcess() {
        System.out.println("Opening SauceDemo website...");
        driver.get("https://www.saucedemo.com/");

        // Login
        System.out.println("Logging in...");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add product to cart
        System.out.println("Adding product to cart...");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Go to cart
        System.out.println("Opening cart...");
        driver.findElement(By.className("shopping_cart_link")).click();

        // Checkout
        System.out.println("Starting checkout...");
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Hajer");
        driver.findElement(By.id("last-name")).sendKeys("Tester");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        // Wait for confirmation
        System.out.println("Waiting for order confirmation...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement completeHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        );

        String actualText = completeHeader.getText().trim();
        System.out.println("Header text: " + actualText);

        // Flexible assertion
        assertTrue(actualText.equalsIgnoreCase("THANK YOU FOR YOUR ORDER") ||
                   actualText.equalsIgnoreCase("Thank you for your order!"),
                   "Checkout failed! Header text: " + actualText);

        System.out.println("? Test completed successfully!");
    }
}
