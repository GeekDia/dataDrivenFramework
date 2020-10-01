package it.testware.base;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import it.testware.reports.ExtentReportManager;
import it.testware.utilities.Utilities;

public class BasePage {

	/*
	 * Initialize Webdriver, Properties, Logs, ExtentReports, ReportNG, DB Mail
	 */

	public static WebDriver driver;
	public static Properties props;
	public static Logger log = LogManager.getLogger(BasePage.class.getName());
	public static WebDriverWait wait;
	public static String browser;
	
	InputStream inputStream;
	InputStream xmlStream ;
	public static HashMap<String, String> strings = new HashMap<String, String>();
	Utilities utils;

	public void invokeDriver() throws Exception {

		if (driver == null) {

			
			String configPath = "configs/config.properties";
			String xmlFilePath = "strings/strings.xml";
			
			

			try {
				inputStream = getClass().getClassLoader().getResourceAsStream(configPath);
				props = new Properties();
				props.load(inputStream);

				log.debug("Properties file load from : " + configPath);
				
				
				xmlStream = getClass().getClassLoader().getResourceAsStream(xmlFilePath);
				
				utils = new Utilities();
				
				strings = utils.parseStringXML(xmlStream);
				log.debug("xml file loaded successfuly from : " + xmlFilePath);
				
				

				// Jenkins Browser filter configuration
				if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

					browser = System.getenv("browser");
				} else {

					browser = props.getProperty("browser");

				}

				props.setProperty("browser", browser);

				if (props.getProperty("browser").equalsIgnoreCase("CHROME")) {
					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();
					log.debug("********** Chrome driver load ************* \n");
				} else if (props.getProperty("browser").equalsIgnoreCase("FIREFOX")) {
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
					driver = new FirefoxDriver();
					log.debug("********** Chrome driver load ************* \n");

				} else {

					log.debug("ATTENTION !!!! No driver specified \n");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (inputStream != null) {
					inputStream.close();
					log.debug("Inputstream file closed\n");
				}
				if(xmlStream != null) {
					xmlStream.close();
					log.debug("XML stream file closed\n");
				}

			}

			driver.manage().window().maximize();
			log.debug("Windows maximized\n");
			driver.manage().deleteAllCookies();
			log.debug("All Cookies deleted\n");

			driver.get(props.getProperty("url"));

			log.debug("\nDriver launch and navigate to : " + props.getProperty("url") + "\n");
		}
	}

	/*
	 * Getting the driver instance
	 */

	public WebDriver getDriver() {

		return driver;
	}

	/*
	 * Adding some keywords
	 */

	public void click(WebElement e, String msg) {

		e.click();
		log.info(msg);
		ExtentReportManager.getTest().log(Status.INFO, msg);
	}

	public void tape(WebElement e, String keys, String msg) {

		e.sendKeys(keys);
		log.info(msg);
		ExtentReportManager.getTest().log(Status.INFO, msg);
	}

	public void getText(WebElement e, String msg) {

		e.getText();
		log.info(msg);
		ExtentReportManager.getTest().log(Status.INFO, msg);
	}

}
