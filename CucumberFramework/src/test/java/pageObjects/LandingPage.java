package pageObjects;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LandingPage {
	public WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		this.driver = driver;

	}


	private By wishLists = By.xpath("(//span[text()='Add to wishlist'])");
	private By wishListPage = By.cssSelector(".heading-row .header-wishlist");
	private By addToCart = By.xpath("//td[@class='product-add-to-cart']/a");
	private By priceList = By.xpath(" //td[@class='product-price']");





	public void addToWishList(int numberOfItems) throws InterruptedException{

		int count=0;
		for (WebElement element : driver.findElements(wishLists)) 
		{
			count++;
			element.click(); 
			Thread.sleep(500);
			if(count>= numberOfItems) break;
		}


	}

	public void viewWishList() {
		// TODO Auto-generated method stub
		driver.findElement(wishListPage).click();

	}
	public void validateWishListCount() {

		int numberOfItems = driver.findElements(priceList).size();
		Assert.assertEquals(numberOfItems, numberOfItems, 4);

	}
	public void findLowestPricedItem() throws InterruptedException {

		int count = 0;
		int numberOfItems = driver.findElements(priceList).size();
		System.out.println("items count"+numberOfItems);
		HashMap<String,Integer> hm = new HashMap<String,Integer>();

		String normalPriceXpath = "(//td[@class=\"product-price\"])[text]//bdi";
		String deletePriceXpath = "(//td[@class=\"product-price\"])[text]//ins//bdi";
		for(int i=1;i<=numberOfItems;i++)	{

			String currentPriceXpath = deletePriceXpath.replaceAll("text", i+"");

			if(driver.findElements(By.xpath(currentPriceXpath)).size()!=0) {


				hm.put(driver.findElement(By.xpath(currentPriceXpath)).getText(), i);
				System.out.println("deleted row"+driver.findElement(By.xpath(currentPriceXpath)).getText());
			}

			else {

				String newCurrentPriceXpath = normalPriceXpath.replaceAll("text", i+"");
				hm.put(driver.findElement(By.xpath(newCurrentPriceXpath)).getText(), i);
				System.out.println("deleted2 row"+driver.findElement(By.xpath(newCurrentPriceXpath)).getText());

			}

		}



		int expectedOrder =0;
		TreeMap<String, Integer> sorted = new TreeMap<>(hm); 
		Set<Entry<String, Integer>> mappings = sorted.entrySet(); 
		System.out.println("HashMap after sorting by keys in ascending order "); 
		for(Entry<String, Integer> mapping : mappings)
		{ 
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
			expectedOrder = mapping.getValue();
			break;
		}
		System.out.println("order"+expectedOrder);


		for (WebElement element: driver.findElements(addToCart)){

			count++;
			if(count== expectedOrder){
				element.click();


			}  
		}
	}


	public void addtoCart() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[@class='cart-contents'])[1]")).click();
	}
	public void itemAddedInCart() {
		Assert.assertEquals(driver.findElement(By.cssSelector(".cart_item")).isDisplayed(), true);

	}










}