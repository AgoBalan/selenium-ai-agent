package com.test.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.time.Duration;

public class TestContext {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestContext.class);
    
    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        initializeDriver();
    }
    
    private void initializeDriver() {
        if (driver != null) {
            return;
        }

        String browser = System.getProperty("browser", "chrome");
        logger.info("Initializing {} browser", browser);
        
        try {
            switch(browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.warn("Browser {} not recognized, defaulting to Chrome", browser);
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions defaultOptions = new ChromeOptions();
                    defaultOptions.addArguments("--remote-allow-origins=*");
                    defaultOptions.addArguments("--disable-dev-shm-usage");
                    defaultOptions.addArguments("--no-sandbox");
                    defaultOptions.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome_profile_" + System.currentTimeMillis());
                    driver = new ChromeDriver(defaultOptions);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            
            logger.info("Browser setup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                if (driver != null) {
                    try {
                        byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                            .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                        scenario.attach(screenshot, "image/png", "Screenshot");
                        logger.warn("Scenario failed: {}. Screenshot taken.", scenario.getName());
                    } catch (Exception e) {
                        logger.error("Failed to capture screenshot: {}", e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error in teardown: {}", e.getMessage(), e);
        } finally {
            try {
                if (driver != null) {
                    driver.quit();
                    driver = null;
                    logger.info("Browser closed successfully");
                }
            } catch (Exception e) {
                logger.error("Error while closing browser: {}", e.getMessage());
            }
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
}
