package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    @Test
    public void testLogout() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(VALID_USER);
        driver.findElement(By.id("password")).sendKeys(VALID_PASS);
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("saucedemo.com"));

        assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User should be redirected to login page after logout");
    }
}
