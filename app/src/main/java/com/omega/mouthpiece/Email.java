package com.omega.mouthpiece;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import org.json.*;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;

public class Email {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER="omega301team@gmail.com";
    private static final String SMTP_AUTH_PASSWORD="Omega3012020";
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public static void email(JSONObject json)
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(tp);
        }
        else
        {
            Log.e("sendingEmail","Email error");
        }

        //System.out.println("Preparing the data to be email");
        //determine if the email is being sent to multiple or a single person

        //determine who the email is being sent to

        //set the properties
        Properties prop = getProperties();

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(prop,auth);

        //determine the type of message to email

        //create the message to send depending on the json data
        try{

            MimeMessage message = Message.createMessage(json,session);

            //send  message that we will send a email
            Log.e("sendingEmail","Sending Email now");
            //System.out.println("Sending the email");
            Transport t = session.getTransport();
            t.connect();
            t.sendMessage(message,message.getRecipients(javax.mail.Message.RecipientType.TO));
            t.close();
            //System.out.println("Email sent");

        }catch (AddressException e)
        {
            e.printStackTrace();
            Log.e("sendingEmail",e.toString());
        }
        catch (MessagingException | JSONException e)
        {
            e.printStackTrace();
            Log.e("sendingEmail",e.toString());
        }
    }

    public static JSONObject createJSON(String email,String userName, String msgType, String subject) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserEmail",email);
        json.put("UserName",userName);
        json.put("MsgType", msgType);
        json.put("Subject", subject);
        return json;
    }

    public static void SendSecurityCodeEmail(String email,String userName, int SecurityCode ) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserEmail",email);
        json.put("UserName",userName);
        json.put("MsgType","SecurityCode");
        json.put("Subject","Mouthpiece Security Code");
        json.put("SecurityCode",SecurityCode);

        email(json);
    }

    public static void SendPasswordChangeEmail(String email,String userName, String changeLink,String notYouLink) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserEmail",email);
        json.put("UserName", userName);
        json.put("Subject","Moutpiece Password change");
        json.put("MsgType","PasswordChange");
        json.put("ChangeLink",changeLink);
        json.put("ErrorLink",notYouLink);

        email(json);
    }

    public static void SendSuccessfullUploadEmail(String email,String userName, String link, String ID) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserEmail",email);
        json.put("UserName",userName);
        json.put("MsgType","SuccessfullUpload");
        json.put("Subject","Mouthpiece Upload Successfull");
        json.put("ViewLink",link);
        json.put("ID",ID);

        email(json);
    }

    public static void SendNewUserCreated(String email,String userName) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserEmail",email);
        json.put("UserName",userName);
        json.put("MsgType","NewUser");
        json.put("Subject","Welcome to Mouthpiece!");

        email(json);
    }

    public static Properties getProperties()
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_HOST_NAME);
        prop.put("mail.smtp.port","465"); //465
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return prop;
    }


    public static String getSMTPHostName()
    {
        return SMTP_HOST_NAME;
    }
    public static String getSMTPAuthUser()
    {
        return SMTP_AUTH_USER;
    }
    public static String getSMTPAuthPassword()
    {
        return SMTP_AUTH_PASSWORD;
    }
}
