package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutTest {
    WebDriver driver;

    public void setup() {
        // Auto-download matching ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Chrome options for headless CI
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // no GUI
        options.addArguments("--no-sandbox"); // required on Linux runners
        options.addArguments("--disable-dev-shm-usage"); // avoid memory issues
        options.addArguments("--disable-gpu"); // optional
        options.addArguments("--window-size=1920,1080"); // consistent window size

        driver = new ChromeDriver(options);
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
