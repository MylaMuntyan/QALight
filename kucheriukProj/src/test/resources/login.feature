
@LoginTest @FullRegression
  Feature: User login

    @R001
   Scenario Outline: R001 Login with invalid login '<login>'
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees alert message with text 'Invalid username / password.'

      Examples:
      | login        | password     |
      | wrong login  | 123456qwerty |
      | wrong login1 | 1532yreccc   |

      @R002
      Scenario Outline: R002 Login with valid login '<login>'
        Given User opens 'Login' page
        When User enters '<login>' login into 'Login' input on 'Login' page
        And User enters '<password>' passWord into 'PassWord' input on 'Login' page
        And User click on 'SingIn' button on 'Login' page
        Then User sees 'SignOut' button

        Examples:
          | login      | password             |
          | kosof68966 | Qwerty12!@Qwerty12!@ |