package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    @Test
    public void testCheckoutProcess() {
        // Set up ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1. Open SauceDemo website
            driver.get("https://www.saucedemo.com/");

            // 2. Log in
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // 3. Add product to cart
            driver.findElement(By.cssSelector(".inventory_item button")).click();

            // 4. Open cart
            driver.findElement(By.id("shopping_cart_container")).click();

            // 5. Checkout
            driver.findElement(By.id("checkout")).click();

            // 6. Fill in checkout info
            driver.findElement(By.id("first-name")).sendKeys("Hajer");
            driver.findElement(By.id("last-name")).sendKeys("Tester");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();

            // 7. Finish checkout
            driver.findElement(By.id("finish")).click();

            // 8. Wait for confirmation header
            WebElement thankYouHeader = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header"))
            );

            // 9. Assertion (ignore case and punctuation differences)
            assertEquals("Thank you for your order!", thankYouHeader.getText());

        } finally {
            // Close browser
            driver.quit();
        }
    }
}
