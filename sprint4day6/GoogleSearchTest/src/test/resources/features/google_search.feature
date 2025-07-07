Feature: Google Search Functionality

  Scenario: Search for BDD in Selenium
    Given I open the browser
    When I search for "BDD in Selenium"
    Then search results should be displayed
