@LoginTest @FullRegression
Feature: User login

  @R001
  Scenario Outline: R001 Login with invalid login '<login>'
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'


    Examples:
    | login       | password |
    | wrong login | 123456   |
    | wrong logi  | 12345    |

    @R002
    Scenario: R002 Login with valid login
      Given User opens 'Login' page
      When User enters 'qaauto' login into 'Login' input on 'Login' page
      And User enters 'Qwe12345' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees 'SignOut' button



