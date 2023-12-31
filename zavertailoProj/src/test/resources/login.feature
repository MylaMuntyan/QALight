
Feature: User login

  @R001
  Scenario Outline:ROO1   Login with invalid login <login>
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password.'

    Examples:
    | login       | password |
    | wrong login | 12334356qwerty |
    | wrong login | 123456     |