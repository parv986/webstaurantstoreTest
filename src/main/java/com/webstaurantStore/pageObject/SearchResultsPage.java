package com.webstaurantStore.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchResultsPage {
	WebDriver driver;
	
	public SearchResultsPage(WebDriver driver) {
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
		driver.get(url);
		driver.manage().window().maximize();
	}
	public void search(String stringTypedIs) {
		searchBox.sendKeys(stringTypedIs);
		//Select s =new Select(searchBox.sendKeys(stringTypedIs));
		searchButton.click();
	}
	
	
}
