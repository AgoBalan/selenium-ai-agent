package com.test.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 10;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this);
        logger.debug("Initialized BasePage with WebDriver instance");
    }

    protected void waitForElementToBeVisible(WebElement element) {
        try {
            logger.debug("Waiting for element to be visible: {}", element);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element {} not visible after {} seconds. Page title: '{}', URL: '{}'", 
                element, TIMEOUT, driver.getTitle(), driver.getCurrentUrl());
            if (e instanceof org.openqa.selenium.NoSuchWindowException) {
                logger.error("Browser window was closed unexpectedly");
            }
            throw e;
        }
    }

    protected void waitForElementToBeClickable(WebElement element) {
        try {
            logger.debug("Waiting for element to be clickable: {}", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Element {} not clickable after {} seconds. Page title: '{}', URL: '{}'", 
                element, TIMEOUT, driver.getTitle(), driver.getCurrentUrl());
            if (e instanceof org.openqa.selenium.NoSuchWindowException) {
                logger.error("Browser window was closed unexpectedly");
            }
            throw e;
        }
    }

    protected void click(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
            logger.debug("Clicked on element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", element, e);
            throw e;
        }
    }

    protected void setText(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);
            logger.debug("Set text '{}' in element: {}", text, element);
        } catch (Exception e) {
            logger.error("Failed to set text in element: {}", element, e);
            throw e;
        }
    }

    protected String getText(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            String text = element.getText();
            logger.debug("Got text '{}' from element: {}", text, element);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", element, e);
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
