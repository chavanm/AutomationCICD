@tag
Feature :  Purchase the order from Ecommerce website
      I want to use this template for my feature file.
      
      Background :          //it executes before each and every scenario.
            Given  I landed on Ecommerce page.
            
     @tag2
      Scenario Outline : Positive Test of submitting the order.
               Given  Logged in with username <name>  and password <password>
               When I add product  <productName> to cart
               And  checkout <productName> and submit the order.
               Then  "THANKYOU FOR THE ORDER."  message is displayed  on ConfirmationPage.
                
    Examples:           
          |  name                                |      password            |
          |  rahulshetty@mail.com   |    IamKing@000      |
        