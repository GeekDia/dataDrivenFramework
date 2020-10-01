package it.testware.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;

import it.testware.base.BasePage;
import it.testware.utilities.MonitoringMail;
import it.testware.utilities.TestConfig;

public class CustomListener extends BasePage implements ITestListener, ISuiteListener {
	
	public static String messageBody;

	@Override
	public void onFinish(ISuite suite) {

		try {
			messageBody = "http://"  + InetAddress.getLocalHost().getHostAddress() + ":8080/job/SeleniumAdvancedFramework/HTML_20Extent_20Report/";
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MonitoringMail mail = new MonitoringMail();

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

}
