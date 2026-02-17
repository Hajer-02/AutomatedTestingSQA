package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortProductsTest extends BaseTest {

    private final String VALID_USER = "standard_user";
    private final String VALID_PASS = "secret_sauce";

    @Test
    public void testSortProductsByPriceHighToLow() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(VALID_USER);
        driver.findElement(By.id("password")).sendKeys(VALID_PASS);
        driver.findElement(By.id("login-button")).click();

        WebElement firstItemBefore = driver.findElement(By.className("inventory_item_name"));
        String firstNameBefore = firstItemBefore.getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sortDropdownElement = wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.className("product_sort_container"))
        ));
        Select sortDropdown = new Select(sortDropdownElement);
        sortDropdown.selectByValue("hilo"); 

  
        wait.until(driver -> !driver.findElement(By.className("inventory_item_name")).getText().equals(firstNameBefore));

        WebElement firstItemAfter = driver.findElement(By.className("inventory_item_name"));
        String firstNameAfter = firstItemAfter.getText();

        assertNotEquals(firstNameBefore, firstNameAfter,
                "The first product should change after sorting by price (high to low).");
    }
}
