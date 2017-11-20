package main;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import schedule.Booking;


public class EmailSender {
	private Booking b;
	
	public EmailSender(Booking b) {
		this.b = b;
	}
	
	public Booking getB() {
		return b;
	}
	
	public void setB(Booking b) {
		this.b = b;
		
	}
	

   public void SendEmail() {
    	
    	

        final String username = "justindelaney94@gmail.com"; /** We can create dummy e-mail */
        final String password = ""; // Gmail Password

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("justindelaney94@gmail.com")); /** We can create a dummy e-mail */
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("jpdd65@mun.ca"));
            message.setSubject("Testing Subject");
            message.setText("Your booking for room....." + b.getRoom().getRoomId() + " " + "by user" + " " + b.getUser().getName() + " " + "for the date of" + " " + b.getDate());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

