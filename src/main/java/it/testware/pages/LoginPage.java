package it.testware.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import it.testware.base.BasePage;
import it.testware.utilities.Utilities;

public class LoginPage extends BasePage {

	public LoginPage() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(getDriver(), Utilities.getWait());
		PageFactory.initElements(factory, this);
	}

}
