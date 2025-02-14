package notify;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static void sendEmail(String recipient, String doctorName) throws MessagingException {
        // SMTP Server details (Use Gmail as an example)
        final String senderEmail = "bijon2k2k@gmail.com"; // Change this
        final String senderPassword = "lixq ngje sbji erjt"; // Use an App Password

        String subject = "Booking Confirmation";
        String messageText = "Your appointment with Dr. " + doctorName + " has been confirmed.";

        // Mail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create Email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        // Set email content (plain text)
        message.setText(messageText);

        // If you want to send HTML emails, use this instead:
        /*
        String htmlContent = "<h1>Booking Confirmation</h1>"
                + "<p>Your appointment with Dr. " + doctorName + " has been confirmed.</p>";
        message.setContent(htmlContent, "text/html");
        */

        // Send Email
        Transport.send(message);
        System.out.println("Email sent successfully to " + recipient);
    }
}