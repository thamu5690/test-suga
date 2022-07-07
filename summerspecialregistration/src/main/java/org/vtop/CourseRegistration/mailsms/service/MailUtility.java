package org.vtop.CourseRegistration.mailsms.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;


public class MailUtility
{
	public static void main(String[] args) {
		//triggerMail();
	}

	public static String triggerMail(String subject, String body, String attachementFilePath, String to)
	{
		String FromEmailID = "";
		String FromEmailPass = "";			
 
		Properties props = new Properties();		 
		//props.put("mail.smtp.host", "5");
		props.put("mail.smtp.host", "smtp.vit.ac.in");
		props.put("mail.smtp.port", "25"); 		  
		props.put("mail.smtp.auth", "true");		 
		props.put("mail.smtp.ssl.trust", "*");
		
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FromEmailID, FromEmailPass);
			}
		});
		 
		 
		try
		{		
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FromEmailID));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(body ,"text/html");		
 
			Transport.send(message);
			//System.out.println("<<<<<< Email Sent Successfully >>>>>>>");
			
			return "SUCCESS";
		 }
		 catch(Exception e)
		 {
			 //e.printStackTrace();
			 //System.out.println("<<<<<<<<< Error Sending Email >>>>>>>>>>");
			 return e.toString();
		 }
	}
}
