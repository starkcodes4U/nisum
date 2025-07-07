Feature: Login Feature

  Scenario: Successful login with valid credentials
    Given the login page is open
    When user enters username "admin" and password "admin123"
    And user clicks the login button
    Then user should see a success message

  Scenario Outline: Invalid login with wrong credentials
    Given the login page is open
    When user enters username "<username>" and password "<password>"
    And user clicks the login button
    Then user should see a failure message

    Examples:
      | username | password   |
      | admin    | wrongpass  |
      | user     | admin123   |
      | test     | test123    |
