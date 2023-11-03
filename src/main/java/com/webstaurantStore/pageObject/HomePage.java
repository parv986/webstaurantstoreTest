package com.webstaurantStore.pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	
	@FindBy(id="searchval")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@value='Search'][1]")
	WebElement searchButton;
	
	
	public void goTo(String url) {
		// TODO Auto-generated method stub
		
		driver.manage().timeouts().implicitlyWait(1100, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().window().maximize();
	}
	public void search(String stringTypedIs) {
		searchBox.sendKeys(stringTypedIs);
		//Select s =new Select(searchBox.sendKeys(stringTypedIs));
		searchButton.click();
	}
	
	public void searchButton() {
		
	}
	
}
