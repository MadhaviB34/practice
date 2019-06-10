package com.omniwyse.emailservice;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	String html ="<html>\n" + 
			"  <head>\n" + 
			"\n" + 
			"<script type=\"application/ld+json\">\n" + 
			"{\n" + 
			"  \"@context\": \"http://schema.org\",\n" + 
			"  \"@type\": \"EventReservation\",\n" + 
			"  \"reservationNumber\": \"E123456789\",\n" + 
			"  \"reservationStatus\": \"http://schema.org/Confirmed\",\n" + 
			"  \"underName\": {\n" + 
			"    \"@type\": \"Person\",\n" + 
			"    \"name\": \"John Smith\"\n" + 
			"  },\n" + 
			"  \"reservationFor\": {\n" + 
			"    \"@type\": \"Event\",\n" + 
			"    \"name\": \"Foo Fighters Concert\",\n" + 
			"    \"performer\": {\n" + 
			"      \"@type\": \"Organization\",\n" + 
			"      \"name\": \"The Foo Fighters\",\n" + 
			"      \"image\": \"http://www.amprocktv.com/wp-content/uploads/2027/01/foo-fighters-1-680x383.jpg\"\n" + 
			"    },\n" + 
			"    \"startDate\": \"2019-07-12T19:30:00-08:00\",\n" + 
			"    \"location\": {\n" + 
			"      \"@type\": \"Place\",\n" + 
			"      \"name\": \"AT&T Park\",\n" + 
			"      \"address\": {\n" + 
			"        \"@type\": \"PostalAddress\",\n" + 
			"        \"streetAddress\": \"24 Willie Mays Plaza\",\n" + 
			"        \"addressLocality\": \"San Francisco\",\n" + 
			"        \"addressRegion\": \"CA\",\n" + 
			"        \"postalCode\": \"94107\",\n" + 
			"        \"addressCountry\": \"US\"\n" + 
			"      }\n" + 
			"    }\n" + 
			"  },\n" + 
			"  \"venueSeat\": \"12\",\n" + 
			"  \"venueRow\": \"A\",\n" + 
			"  \"venueSection\": \"101\",\n" + 
			"  \"ticketToken\": \"qrCode:AB34\",\n" + 
			"  \"ticketNumber\": \"abc123\"\n" + 
			"}\n" + 
			"</script>\n" + 
			"\n" + 
			"  </head>\n" + 
			"  <body>\n" + 
			"    <p>\n" + 
			"      This a test for a Go-To action in Gmail.\n" + 
			"    </p>\n" + 
			"  </body>\n" + 
			"</html>\n" + 
			"";


	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		new EmailService().sendEmail();
	}

	public String sendEmail() throws AddressException, MessagingException, IOException {
		sendmail();
		return "Email sent successfully";
	}

	private void sendmail() throws AddressException, MessagingException, IOException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("madhavi.cse34@gmail.com", "madhucse34");
			}
		});

		Message msg = new MimeMessage(session);
//		msg.addHeader("MIME-Version", "1.0");
//		msg.addHeader("Content-Type", "text/html; charset=ISO-8859-1");
		msg.setFrom(new InternetAddress("madhavi.cse34@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("madhavi.cse34@gmail.com"));
		msg.setSubject("Test event");
		msg.setContent(html, "text/html");
		msg.setSentDate(new Date());

//	MimeBodyPart messageBodyPart = new MimeBodyPart();
//	messageBodyPart.setContent(emailmessage.getBody(), "text/html");
//
//	Multipart multipart = new MimeMultipart();
//	multipart.addBodyPart(messageBodyPart);
//	MimeBodyPart attachPart = new MimeBodyPart();
//
//	attachPart.attachFile("/home/omni/Downloads/smsapi.txt");
//
//	multipart.addBodyPart(attachPart);
//	msg.setContent(multipart);
// sends the e-mail
		Transport.send(msg);
		System.out.println(html);
	}
}