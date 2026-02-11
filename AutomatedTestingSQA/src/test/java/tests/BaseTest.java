@BeforeEach
public void setUp() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();

    String ciEnv = System.getenv("CI");
    if ("true".equalsIgnoreCase(ciEnv)) {
      
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
    } else {
       
        options.addArguments("--start-maximized");
    }

    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}
