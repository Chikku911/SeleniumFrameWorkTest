
Feature: Adding the lowest price item from wishlist to cart

@Wishlist
  Scenario: Adding multiple items to wishlist and moving the lowest priced one to cart
    Given I add four different products to wishlist
    When I view my wishlist
    Then I find four selected items in wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to cart
    Then I am able to verify the item in my cart
