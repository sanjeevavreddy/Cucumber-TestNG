@AllTests
Feature: Tests Feature


  @User1
  Scenario Outline: Test 1
    Given user is ready with Test Data excel "TestData.xlsx" and sheet "TestData"
    Given user launch url
    Then user login using username and password using dataKey as "<dataKey>"

    Examples:
      | dataKey   |
      | Scenario1 |
