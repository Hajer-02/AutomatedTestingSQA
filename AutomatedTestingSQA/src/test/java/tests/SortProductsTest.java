package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortProductsTest extends BaseTest {

    @Test
    public void testSortProductsByPriceHighToLow() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement firstItemBefore = driver.findElement(By.className("inventory_item_name"));
        String firstNameBefore = firstItemBefore.getText();

        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("hilo"); // high to low

        WebElement firstItemAfter = driver.findElement(By.className("inventory_item_name"));
        String firstNameAfter = firstItemAfter.getText();

        assertNotEquals(firstNameBefore, firstNameAfter,
                "The first product should change after sorting by price (high to low).");
    }
}