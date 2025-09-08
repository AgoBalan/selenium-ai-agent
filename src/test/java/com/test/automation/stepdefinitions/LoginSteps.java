package com.test.automation.stepdefinitions;

import com.test.automation.base.TestContext;
import com.test.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final TestContext context;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    private void initializeLoginPage() {
        if (loginPage == null) {
            WebDriver driver = context.getDriver();
            if (driver == null) {
                throw new IllegalStateException("WebDriver not initialized");
            }
            loginPage = new LoginPage(driver);
        }
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        WebDriver driver = context.getDriver();
        driver.get("http://testphp.vulnweb.com/login.php"); // A test site with login form
        initializeLoginPage();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        initializeLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        initializeLoginPage();
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        initializeLoginPage();
        Assert.assertTrue("User should be logged in", loginPage.isLoggedIn());
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        initializeLoginPage();
        String actualMessage = loginPage.getMessage();
        Assert.assertEquals("Message verification failed", message, actualMessage);
    }
}
