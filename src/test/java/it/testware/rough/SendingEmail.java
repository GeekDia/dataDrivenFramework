package it.testware.rough;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import it.testware.utilities.MonitoringMail;
import it.testware.utilities.TestConfig;

public class SendingEmail {

	public static void main(String[] args) throws AddressException, MessagingException {
		MonitoringMail mail = new MonitoringMail();
		
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, "Testing mail sending");
		
		

	}

}
