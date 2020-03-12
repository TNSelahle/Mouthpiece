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
