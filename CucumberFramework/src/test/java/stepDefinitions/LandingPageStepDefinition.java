package stepDefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinition {
	public WebDriver driver;
	TestContextSetup testContextSetup;
	LandingPage landingPage;
	
	public LandingPageStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.landingPage =testContextSetup.pageObjectManager.getLandingPage();
	}
	@Given("^I add four different products to wishlist$")
	public void i_add_four_different_products_to_wishlist() throws Throwable {

		landingPage.addToWishList(4);

	}

	@When("^I view my wishlist$")
	public void i_view_my_wishlist() throws Throwable {
		//Thread.sleep(5000);
		landingPage.viewWishList();
		//Thread.sleep(5000);
	}
	@Then("^I find four selected items in wishlist$")
	public void i_find_four_selected_items_in_wishlist() throws Throwable {
		landingPage.validateWishListCount();
	}
	
	@When("^I search for lowest price product$")
	public void i_search_for_lowest_price_product() throws Throwable {

		landingPage.findLowestPricedItem(); // 
		//Thread.sleep(5000);
	}

	@And("^I am able to add the lowest price item to cart$")
	public void i_am_able_to_add_the_lowest_price_item_to_cart() throws Throwable {
		landingPage.addtoCart();
	}
	
	@Then("^I am able to verify the item in my cart$")
	public void i_am_able_to_verify_the_item_in_my_cart() throws Throwable {
		landingPage.itemAddedInCart();
	}

	

}



