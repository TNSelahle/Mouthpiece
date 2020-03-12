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

public class EmailNotification {

    public static void createEmail(JSONObject json)
    {
        try{

            String emailAddress = json.getString("Email");
            String userName = json.getString("User");
            String emailType = json.getString("Msg");//verify


            Properties prop = new Properties();
            Session session=Session.getDefaultInstance(prop,null);

            MimeMessage message=new MimeMessage(session);

            message.setFrom(new InternetAddress("u18140565@tuks.co.za"));
            message.addRecipient(MimeMessage.RecipientType.TO ,new InternetAddress(emailAddress));
//            message.setHeader("Hi Cos 301 Omega Notifications");
            message.setText("THe followiing message was sent to user: "+userName+"\nwitht the message: "+emailType);

            Transport.send(message);

        }
        catch(JSONException | AddressException e)
        {
            System.out.println("error creating email with json data: "+e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail()
    {

    }

}
