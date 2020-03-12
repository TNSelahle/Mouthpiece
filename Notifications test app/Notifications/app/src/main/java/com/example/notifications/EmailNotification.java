package com.example.notifications;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class EmailNotification {

    public static void createEmail(JSONObject json)
    {
        try{
            String from = "cosomegatest@gmail.com";
            String emailAddress = json.getString("Email");
            String userName = json.getString("User");
            String emailType = json.getString("Msg");//verify

            String host ="smtp.gmail.com";

            Properties prop = System.getProperties();
            prop.setProperty("mail.smtp.host","localhost");
            Session session=Session.getDefaultInstance(prop);

            MimeMessage message=new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(MimeMessage.RecipientType.TO ,new InternetAddress(emailAddress));
            message.setSubject("This is a test Email");

//            message.setHeader("Hi Cos 301 Omega Notifications");
            message.setText("THe followiing message was sent to user: "+userName+"\nwitht the message: "+emailType);

            Transport.send(message);
            System.out.println("The message was sent corretly");
        }
        catch(JSONException | AddressException e)
        {
            System.out.println("error creating email with json data: "+e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

/*


package emailtest;

        import com.sun.mail.smtp.SMTPTransport;
        import java.util.Properties;
        import javax.mail.*;
        import javax.mail.internet.AddressException;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;
        import javax.mail.Authenticator;
        import javax.mail.PasswordAuthentication;

public class EmailTest {


    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER = "cosomegatst@gmail.com";
    private static final String SMTP_AUTH_PWD  = "Omega321##";
    public static void main(String[] args)  {

        String to ="cosomegatest@gmail.com";
        String from ="cosomegatest@gmail.com";

        String host="localhost";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port","465"); //465
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//    Session session = Session.getInstance(properties, null);
//    session.setDebug(true);
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(properties,auth);

        try{
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO ,new InternetAddress(from));
            message.setSubject("Test the email");
            message.setText("This is the body of the email");

            System.out.println("Sending the email");

            Transport t = session.getTransport();
            t.connect();
            t.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            t.close();
//           SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
//           t.connect("smtp.gmail.com",465,"cosomegatest@gmail.com", "Omega312##");
//           t.sendMessage(message, message.getAllRecipients());
            System.out.println("The email sent successfully");


        }
        catch (AddressException e)
        {
            System.out.println(e);
        }
        catch (MessagingException e)
        {
            System.out.println(e);
        }
        // TODO code application logic here
    }




    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "cosomegatest@gmail.com";
            String password = "Omega312##";
            return new PasswordAuthentication(username, password);
        }
    }
}
 */
