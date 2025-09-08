Feature: Login Functionality
  As a user
  I want to login to the application
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "testuser" and password "password123"
    And I click on the login button
    Then I should be logged in successfully

  Scenario Outline: Login with different credentials
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should see the message "<message>"

    Examples:
      | username | password    | message                    |
      | testuser | password123 | Login successful          |
      | invalid  | wrong123    | Invalid credentials       |
