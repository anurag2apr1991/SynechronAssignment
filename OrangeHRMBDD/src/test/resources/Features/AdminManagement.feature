Feature: Admin management Page functionalities

  Background:
    When user enters username and password
    And user press login button
    Then user is navigated to the home page
    When user clicks admin button in main menu
    Then user is navigated to the admin management page

  Scenario: Check remove user record functionality
    When user press delete button near particular user
    And user confirm to delete the record in the alert message
    Then the particular user record is deleted

  Scenario: Check counter of user records work properly
    When user press delete button near some user
    And user confirm to delete the record in the alert message
    Then the counter of user records becomes less by one
