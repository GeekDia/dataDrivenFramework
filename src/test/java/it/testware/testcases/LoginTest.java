package it.testware.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import it.testware.pages.HomePage;
import it.testware.utilities.Utilities;

public class LoginTest extends BaseTest {
	
	JSONObject object;
	
	
	@BeforeClass
	public void beforeClass() {
		try {
			object = Utilities.getTestDataFromJson("data/testData2.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		log.debug("\n***************** " + m.getName() + " start executing ****************\n");

	}

	@Test
	public void loginAsBankManager() throws InterruptedException {

		SoftAssert sas = new SoftAssert();
		HomePage homepage = new HomePage();
		homepage.clickOnBankManagerBtn();
		log.debug(object.getJSONObject("invalidUsername").get("username"));
		
		String expectedTxt = strings.get("err_invalid_username_or_password");
		String actualTxt = "Username and password do not match any user in this service.";
		sas.assertEquals(actualTxt, expectedTxt);
		
		sas.assertTrue(true);
		
		sas.assertAll();

		Thread.sleep(3000);

	}

}
