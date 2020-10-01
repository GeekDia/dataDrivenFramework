package it.testware.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import it.testware.base.BasePage;
import it.testware.utilities.Utilities;

public class HomePage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
	private WebElement bankManagerBtn;

	/*
	 * Constructor Initialize objects on this page
	 */
	public HomePage() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(getDriver(), Utilities.getWait());
		PageFactory.initElements(factory, this);
	}

	/*
	 * click on login button
	 */

	public ManagerPage clickOnBankManagerBtn() {

		click(bankManagerBtn, " Bank manager button cliked");

		return new ManagerPage();
	}
}
