package com.test.automation.pages;

import com.test.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
    @FindBy(name = "uname")
    private WebElement usernameInput;
    
    @FindBy(name = "pass")
    private WebElement passwordInput;
    
    @FindBy(css = "[type='submit']")
    private WebElement loginButton;
    
    @FindBy(css = "div.message, div.error, div.success")
    private WebElement messageElement;
    
    @FindBy(css = ".userinfo, div.success, #user-info")
    private WebElement loggedInIndicator;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        setText(usernameInput, username);
    }
    
    public void enterPassword(String password) {
        setText(passwordInput, password);
    }
    
    public void clickLoginButton() {
        click(loginButton);
    }
    
    public boolean isLoggedIn() {
        try {
            return loggedInIndicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getMessage() {
        return getText(messageElement);
    }
}
