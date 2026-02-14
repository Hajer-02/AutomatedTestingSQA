package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";
    private final String INVALID_USER = "wrong_user";
    private final String INVALID_PASS = "wrong_pass";

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(VALID_USER);
        driver.findElement(By.id("password")).sendKeys(VALID_PASS);
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.getCurrentUrl().endsWith("inventory.html"),
                "User should be redirected to the inventory page after successful login.");
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(INVALID_USER);
        driver.findElement(By.id("password")).sendKeys(INVALID_PASS);
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h3[data-test='error']"))).getText();

        assertTrue(errorText.contains("Epic sadface"),
                "An error message should be displayed for invalid credentials.");
    }
}
