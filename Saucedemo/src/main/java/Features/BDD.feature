Feature:Order Product Feature
  Scenario: Login
  Given browser and open "https://www.saucedemo.com/"
  When user enter Login ID and Password
    Then click on login

    Then find Most Expensive Product
    And add to cart if value is less than 100

    Then Check if the REMOVE option is enabled
    And again click on the same Add to Cart

    Then Click on the CART icon
    And Click on Continue Shopping

    Then find Least Expensive Product
    And Click on the CART icon

    Then Verify Cart items Value with the items present in previous page
    And click on Checkout

    Then Input the required details on CHECKOUT
    And Verify the total amount displayed on CHECKOUT

    Then Click on FINISH and Verify the Success message