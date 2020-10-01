package it.testware.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import it.testware.base.BasePage;
import it.testware.utilities.Utilities;

public class AddCustomerPage extends BasePage {

	/*
	 * Constructor Initialize objects on this page
	 */
	public AddCustomerPage() {

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(getDriver(), Utilities.getWait());
		PageFactory.initElements(factory, this);

	}

	@FindBy(xpath = "//input[@placeholder='First Name']")
	@CacheLookup
	private WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	@CacheLookup
	private WebElement lastName;

	@FindBy(xpath = "//input[@placeholder='Post Code']")
	@CacheLookup
	private WebElement postCode;

	@FindBy(xpath = "//button[@class='btn btn-default']")
	@CacheLookup
	private WebElement submit;

	/*
	 * Enter FirstName
	 */

	public AddCustomerPage enterFirstName(String firstName) {

		tape(this.firstName, firstName, "Entered the firstname : " + firstName);

		return this;
	}

	/*
	 * Enter LasttName
	 */

	public AddCustomerPage enterLastName(String lastName) {

		tape(this.lastName, lastName, "Entered the firstname : " + lastName);

		return this;
	}

	/*
	 * Create new customer
	 */

	public AddCustomerPage addNewCustomer(String firstName, String lastName, String postCode) {

		tape(this.firstName, firstName, "Entered the firstname : " + firstName);

		tape(this.lastName, lastName, "Entered the lastname : " + lastName);

		tape(this.postCode, postCode, "Entered the postCode : " + postCode);

		click(this.submit, "Cliking on submit button element : " + submit);

		getDriver().switchTo().alert().accept();

		return this;
	}

}
