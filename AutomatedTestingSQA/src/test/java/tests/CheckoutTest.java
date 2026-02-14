package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutTest {

    @Test
    public void testCheckoutProcess() {
        // Setup driver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1️⃣ Open SauceDemo and log in
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // 2️⃣ Add product to cart
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();

            // 3️⃣ Open cart
            driver.findElement(By.className("shopping_cart_link")).click();

            // 4️⃣ Click checkout
            driver.findElement(By.id("checkout")).click();

            // 5️⃣ Fill checkout info
            driver.findElement(By.id("first-name")).sendKeys("Hajer");
            driver.findElement(By.id("last-name")).sendKeys("Test");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();

            // 6️⃣ Finish checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

            // 7️⃣ Wait for confirmation message
            WebElement confirmationHeader = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
            );

            // 8️⃣ Assert the checkout is completed
            Assertions.assertTrue(
            	    confirmationHeader.getText().equalsIgnoreCase("Thank you for your order!"),
            	    "Checkout not completed!"
            	);


        } finally {
            // 9️⃣ Close the browser
            driver.quit();
        }
    }
}
