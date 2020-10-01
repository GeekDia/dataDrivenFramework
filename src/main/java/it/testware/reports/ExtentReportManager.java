package it.testware.reports;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtentReportManager {

	static ExtentReports extent;
	public static String pathToReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator;
			

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	// we use synchronized because we need to support parallel execution
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {

			ExtentSparkReporter html = new ExtentSparkReporter(pathToReport + "extent.html");
			extent = new ExtentReports();
			// add configuration

			String xmlFile = System.getProperty("user.dir") + "/src/test/resources/configs/html-report-config.xml";
			try {
				html.loadXMLConfig(xmlFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			extent.attachReporter(html);

			extent.setSystemInfo("Organization", "Dialab");
			extent.setSystemInfo("Build-N°", "1.9.7");

		}

		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));

	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = getReporter().createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return test;
	}

}
