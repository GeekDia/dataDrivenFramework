package it.testware.testcases;

import org.testng.annotations.*;


import it.testware.base.BasePage;

public class BaseTest extends BasePage {
	
	
	@BeforeMethod
	public void setUp() {
		
		try {
			invokeDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
			driver = null;
			log.debug("\n Quit the driver after Test\n");
			
		}
		
	}

}
