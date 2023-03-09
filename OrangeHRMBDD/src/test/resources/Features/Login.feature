Feature: Test login functionality

  Scenario: Check login is succesful with valid credentials
    When user enters username and password
    And user press login button
    Then user is navigated to the home page

  Scenario: Check login is not succesful with invalid credentials
    When user enters invalidUsername and invalidPassword
    And user press login button
    Then it appears invalid creadential message
