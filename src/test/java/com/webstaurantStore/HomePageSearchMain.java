package com.webstaurantStore;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePageSearchMain {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Below to use WebDriverManager
		//WebDriverManager.chromedriver().setup();
		//I have 118 version of  chrome but unable to get 118 version of driver so used earlier one.
		//New selenium version doesn't need setproperty as it will download driver automatically
		//System.setProperty("webdriver.chrome.driver", "C:\\TaskToShare\\MavenWebStaurantstore\\WebstaurantStore\\src\\main\\resources\\chromedriver.exe");
		WebDriver dr = new ChromeDriver();
		dr.get("https://www.webstaurantstore.com/");
		dr.manage().timeouts().implicitlyWait(1100, TimeUnit.MILLISECONDS);
		dr.manage().window().maximize();
		dr.findElement(By.id("searchval")).sendKeys("stainless work table");
		dr.findElement(By.xpath("//div[@class='hidden flex-1 lt:flex']//button[@value='Search']")).click();
		List<WebElement> items;
		List<String> str = new ArrayList<String>();

		List<WebElement> pages = dr.findElements(By.cssSelector("a[aria-label*='page']"));
		for (WebElement p : pages) {
			items = dr.findElements(By.xpath("//div[@id='product_listing']/div"));

			for (WebElement li : items) {
				if (li.getText().contains("Table")) {
					str.add(li.getText());
				}
			}
		}

		String text = str.get(str.size() - 1);
		System.out.println("text : " + text);

		String[] title = text.split("Rated");
		System.out.println(title);
		System.out.println("title : " + title[0].trim());
		String itemIS = title[0].trim();
		dr.findElement(By.linkText(itemIS)).click();
		Thread.sleep(1000);
		dr.findElement(By.id("buyButton")).click();
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(dr).withTimeout(Duration.ofSeconds(60L))
				.pollingEvery(Duration.ofSeconds(60L)).ignoring(NoSuchElementException.class);

		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='amount-in-cart']/a[1]")));

		dr.findElement(By.xpath("//div[@class='amount-in-cart']/a[1]")).click();
		Thread.sleep(2000);
		WebElement emptyCart =dr.findElement(By.xpath("//div[@class='inline']/button"));
		JavascriptExecutor js = (JavascriptExecutor) dr;
		js.executeScript("arguments[0].click();", emptyCart);
		Thread.sleep(1000);
		List<WebElement> btns = dr.findElements(By.xpath("//div/footer/button"));
		for (WebElement btn : btns) {
			btn.click();
			break;
		}
		WebElement emptyCart1 = dr.findElement(By.xpath("//div[@id='page']/div[1]/div[1]/div[1]/div[1]//div/p[1]"));
		JavascriptExecutor js1 = (JavascriptExecutor) dr;
		String textFoundIs = (String) js1.executeScript("return arguments[0].innerText;", emptyCart1);
		if (textFoundIs.contains("Your cart is empty")) {
			Assert.assertEquals(textFoundIs, "Your cart is empty.", "Cart is Empty!!!");
			System.out.println("Cart is Empty!!!");
		}
		dr.quit();
	}

}
