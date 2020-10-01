package it.testware.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.annotations.*;

import it.testware.pages.HomePage;
import it.testware.utilities.ExcelUtilities;
import it.testware.utilities.Utilities;

public class AddCustomerTest extends BaseTest {

	HomePage homepage;
	JSONObject object;
	
	@BeforeClass
	public void beforeClass() {
		
		try {
			object = Utilities.getTestDataFromJson("data/testData.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		log.debug("\n***************** " + m.getName() + " start executing ****************\n");

		homepage = new HomePage();

	}

	@Test(dataProviderClass = ExcelUtilities.class, dataProvider = "getTestData")
	public void addCustomer(Hashtable<String, String> data) {
		
		log.debug(object.getJSONObject("invalidUsername").get("username"));

		homepage.clickOnBankManagerBtn().clickOnaddCustomerBtn().addNewCustomer(data.get("FirstName"),
				data.get("LastName"), data.get("PostCode"));

	}

}
