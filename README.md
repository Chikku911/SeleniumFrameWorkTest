# SeleniumFrameWorkTest
Code can be executed by running the TestNGTestRunner.java file as TestNG Test
---4 random items are added to wishlist
---moves to wishlist page
--- lowest priced item is added to bag

used a hashmap to sort the price of items in wishlist. key as price (mark downs and normal prices) and value as looping through number of items and then sorting by key

I have automated the mentioned scenarios using Cucumber Java
Under src/test/java i have created 5 packages: cucumberOptions, features, pageObjects,stepDefinitions and utils

**cucumberOptions** contains the TestNG runner file: here appropriate tags are given to identify the feature files and stepdefinition java classes. Also used tags to mention the scenario to be run and added plugin for html and json reports. Extent report like Spark could have been added to enhance the reporting feature

**features** package contains one feature file: AddtoWishList.feature. The acceptance criteria given for the test I have written as one scenario. To make it more simpler, I could have split the given scenario into **three** scenarios under one feature. 
One scenario for Landing page: here number of items to be added could have been passed as regular expression, so that same test can be reused to add n number of items
   Given: User is on Landing page
   When: User adds "n" items to bag and clicks on wishlist
   Then: WishList page should be loaded
   And: "n" items should be shown
   Similarly one for WishlistPage and CartPage
   
  **pageObjects** package I have created just one page for LandingPage and all locators are written here covering all three pages involved here (wishlist and cart). Ideally as per page object model objects for each page should be written in seperate page object files, but i have added a PageobjectManager (In cases where we have multiple Step definition files to make sure that the same object should not be created again and again but to use a single object for all the step definition files). 
  
  **stepDefinitions** package contains only a single stepdefintion file. To enhance the quality and reusabilty of the framework it would be better if i had created seperate step definition files for each page rather than the whole functionality in one step definition file. Hooks class is used here to to run any pre-requisites or something after completing the scenarios eg here to quit the driver once test is done and taking screenshot if test fails(after each step)
  
  **utils** contains TestContextSetUp.java. Even though i have not used mutliple step definition files, when we have multiple step definition files we need to transfer the state of variables from one step definition to another. This is achieved by Dependency injection (pico container), for this testcontextsetup class is needed, this class tells the preoperties and variables that we will be sharing with other step definition files. eg: i have created a constructor in landing page stepdefinition class and passed the testcontext instance
  TestBase.java: passing driver details , reading url details, which browser to execute etc from properties files, synchronisation
  GenericUtils.java: initialise driver
  
  Also i have used Thread.sleep and System.out.println() to adjust timings for particluar locators and checking whether code is entering the loops and working accordingly. Instead of these i could have used Explicit wait by making use of Webdriver class and setting up proper logs
