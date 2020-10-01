package it.testware.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import it.testware.base.BasePage;
import it.testware.utilities.Utilities;

public class ManagerPage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Add Customer')]")
	@CacheLookup
	private WebElement addCustomerBtn;

	/*
	 * Constructor Initialize objects on this page
	 */
	public ManagerPage() {

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(getDriver(), Utilities.getWait());
		PageFactory.initElements(factory, this);
	}

	/*
	 * click on add customer button
	 */

	public AddCustomerPage clickOnaddCustomerBtn() {

		click(addCustomerBtn, "add customer button cliked");
		return new AddCustomerPage();
	}
}
