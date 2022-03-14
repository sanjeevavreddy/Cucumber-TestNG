Feature: Tests2 Feature


  @OddTests
  Scenario Outline: Test 3
    Given user is ready with Test Data excel "TestData.xlsx" and sheet "TestData"
    Given user launch url
    Then user login using username and password using dataKey as "<dataKey>"
        Then user navigate to fund transfer
    Examples:
      | dataKey   |
      | Scenario1 |


  @EvenTests
  Scenario Outline: Test 4
    Given user is ready with Test Data excel "TestData.xlsx" and sheet "TestData"
    Given user launch url
    Then user login using username and password using dataKey as "<dataKey>"
        Then user navigate to fund transfer
    Examples:
      | dataKey   |
      | Scenario2 |
