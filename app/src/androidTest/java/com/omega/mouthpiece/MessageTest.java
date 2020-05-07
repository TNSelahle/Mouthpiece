package com.omega.mouthpiece;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.function.Consumer;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;

public class MessageTest {

    ArrayList<JSONObject> units;
    int count=0;
    ArrayList<String[]> test;
    Session session;

    public MessageTest() throws JSONException {
        units = new ArrayList();
        test = new ArrayList();
        //test cases
        //to add case
        //add
        //
        //a =new String[]{"<email>", "<name>", "<tipe>", "<heding>"};
        //test.add(a);
        String[] a =new String[]{"cosomegatest@gmail.com", "MadMan1", "SecurityCode", "hello "};
        test.add(a);
        a =new String[]{"cosomegatest@gmail.com", "MadMan2", "PasswordChange", "Password Change"};
        test.add(a);
        a =new String[]{"cosomegatest@gmail.com", "MadMan3", "SuccessfullUpload", "SuccessfullUpload"};

        test.add(a);
        a =new String[]{"cosomegatest@gmail.com", "MadMan4", "<h3>user inputted message body<h3>", "user inputted message"};
        test.add(a);
        test.forEach(new Consumer<String[]>() {
            @Override
            public void accept(String[] n) {
                try {
                    units.add(Email.createJSON(n[0], n[1], n[2], n[3]));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        int num =12345;
        units.get(0).put("SecurityCode",num );
        units.get(1).put("ChangeLink","https://www.agegeek.com/" );
        units.get(1).put("ErrorLink","https://www.boredbutton.com/coronavirus.php" );
        units.get(2).put("ViewLink","https://www.anothersadtrombone.com/" );
        units.get(2).put("ID","159159" );

    }
    /**
     * Test of getSecurityCodeMessage method, of class Message.
     */
    @Test
    public void testGetSecurityCodeMessage() throws JSONException {
        System.out.println("getSecurityCodeMessage");
        JSONObject json = units.get(0);

        String expResult ="";
        expResult+="<h3>Dear MadMan1<br></h3>";
        expResult+="<h3>Before we welcome you to the mouth piece community</h3>";
        expResult+="<h3>Help us secure your Mouthpiece account by verifying your email address(cosomegatest@gmail.com). This lets you access all of Mouthpiece's features.</h3>";
        expResult+="<h2>Your Mouthpiece Security Code:<br>12345</h2>";
        expResult+="<h4>From<br>Mouthpiece Omega Team</h4>";

        String result = Message.getSecurityCodeMessage(json);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPasswordChangeMessage method, of class Message.
     */
    @Test
    public void testGetPasswordChangeMessage() throws JSONException {
        System.out.println("getPasswordChangeMessage");
        JSONObject json = units.get(1);

        String expResult = "";
        expResult+="<h3>Dear MadMan2<br></h3>";
        expResult+="<h3>We recieved a request to reset your Mouthpiece password. Click the link bellow to choose a new one:</h3>";
        expResult+="<a href='https://www.agegeek.com/' >Reset Your Password</a>";
        expResult+="<h4>If you did not make this request or need assistance, please click <a href='https://www.boredbutton.com/coronavirus.php'>here</a>.</h4>";
        expResult+="<h4>From<br>Mouthpiece Omega Team</h4>";

        String result = Message.getPasswordChangeMessage(json);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuccessfullUploadMessage method, of class Message.
     */
    @Test
    public void testGetSuccessfullUploadMessage() throws JSONException {
        System.out.println("getSuccessfullUploadMessage");
        JSONObject json = units.get(2);

        String expResult = "";
        String date = Message.getDate();
        expResult+="<h3>Dear MadMan3<br></h3>";
        expResult+="<h3>Your mouth piece has been successfully uploaded</h3>";
        expResult+="<a href='https://www.anothersadtrombone.com/'>View your upload</a>";
        expResult+="<h4>Dates:"+date+" <br> ID:159159</h4>";
        expResult+="<h4>From<br>Mouthpiece Omega Team</h4>";

        String result = Message.getSuccessfullUploadMessage(json);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNewUserMessage method, of class Message.
     */
    @Test
    public void testGetNewUserMessage() throws JSONException {
        System.out.println("getNewUserMessage");
        JSONObject json = units.get(2);
        String expResult = "";
        String date = Message.getDate();
        expResult+="<h3>Dear MadMan3<br></h3>";
        expResult+="<h3>Thank you for registering with Mouthpiece!</h3>";
        expResult+="We hope this app works for you in every way you want it to, if not please feel free to use the built in feedback tool in the app if there is anything you want to tell us.";
        expResult+="<h4>Now you get to do some talking too!</h4>";
        expResult+="<h4>Dates:"+date+" <br></h4>";
        expResult+="<h4>From<br>Mouthpiece Omega Team</h4>";
        String result = Message.getNewUserMessage(json);
        assertEquals(expResult, result);
    }


    /**
     * Test of createMessage method, of class Message.
     */
    @Test
    public void testCreateMessage() throws Exception {
        System.out.println("createMessage");
        JSONObject json = units.get(3);

        Properties prop = Email.getProperties();
        Authenticator auth = new SMTPAuthenticator();
        session = Session.getDefaultInstance(prop,auth);

        String to=json.getString("UserEmail");
        String subject=json.getString("Subject");
        MimeMessage result = Message.createMessage(json, session);

        MimeMessage expResult = new MimeMessage(session);

        expResult.setFrom(new InternetAddress(Email.getSMTPAuthUser()));
        assertEquals(expResult.getFrom()[0], result.getFrom()[0]);

        expResult.addRecipient(javax.mail.Message.RecipientType.TO ,new InternetAddress(to));
        assertEquals(expResult.getAllRecipients()[0], result.getAllRecipients()[0]);

        expResult.setSubject(subject);
        assertEquals(expResult.getSubject(),result.getSubject());

        expResult.setSentDate(new Date());

        String msgContent = Message.getMessageContent(json);
        expResult.setContent(msgContent,"text/html");
        assertEquals(expResult.getContent(),result.getContent());
    }
}