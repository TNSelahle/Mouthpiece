package com.omega.mouthpiece;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;


public class Message {
   private static String companyName="COS301 OMEGA Group";
   private static String PASSWORD_CHANGE_URL="passwordChange.html";
   private static String VERIFY_EMAIL_URL="verify.html";
   private static String UPDATES_PAGE_URL="updates.html";
    
    public static MimeMessage createMessage(JSONObject json,Session session) throws AddressException, MessagingException, JSONException {
        //set to from the json data
        String to=json.getString("UserEmail");        
        String subject=json.getString("Subject");
        String MessageType=json.getString("MsgType");      
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(Email.getSMTPAuthUser()));
        msg.addRecipient(javax.mail.Message.RecipientType.TO ,new InternetAddress(to));

        msg.setSubject(subject);
        msg.setSentDate(new Date());
        
        String msgContent = getMessageContent(json);
        msg.setContent(msgContent,"text/html");
       // msg.setText(MessageType+"    "+json.getString("Message"));
        
        
        return msg;
    }
   
    
    public static String getMessageContent(JSONObject json) throws JSONException {
        String content="";
        String type=json.getString("MsgType");
        
        switch(type)
        {
            case "SecurityCode":
                    content= getSecurityCodeMessage(json);
                    break;
                    
            case "PasswordChange":
                    content=getPasswordChangeMessage(json);
                    break;
            case "SuccessfullUpload":
                    content= getSuccessfullUploadMessage(json);
                    break;
            default:
                content=json.getString("MsgType");
            break;
        }
        
        
        return content;
    }
    
    
    // we can add messages here in the form of using htmlformat that would be
    //inside of the body tag 
    //must return a string containing this info 
   public static String getSecurityCodeMessage(JSONObject json) throws JSONException {
        String msg ="";
        /*
        <h3>Dear @Username<br></h3>
        <h3>Before we welcome you to the mouth piece community</h3>
        <h3>Help us secure your Mouthpiece account by verifying your email address(@emailAddresss)This lets you access all of Mouthpiece's features.</h3>
        <h2>Your Mouthpiece Security Code:<br>639881</h2>
        <h4>From<br>Mouthpiece Omega Team</h4>
        */
        msg+="<h3>Dear "+json.getString("UserName")+"<br></h3>";
        msg+="<h3>Before we welcome you to the mouth piece community</h3>";
        msg+="<h3>Help us secure your Mouthpiece account by verifying your email address("+json.getString("UserEmail")+"). This lets you access all of Mouthpiece's features.</h3>";
        msg+="<h2>Your Mouthpiece Security Code:<br>"+json.getInt("SecurityCode")+"</h2>";
        msg+="<h4>From<br>Mouthpiece Omega Team</h4>";
        return msg;
   }

   public static String getPasswordChangeMessage(JSONObject json) throws JSONException {
        String msg="";
        /*
            <h3>Dear @Username<br></h3>
            <h3>We received a request to reset your Mouthpiece password. Click the link below to choose a new one:</h3>
            <a href="url">Reset Your Password</a>
            <h4>If you did not make this request or need assistance, please click <a href="url">here</a>.</h4>
            <h4>From<br>Mouthpiece Omega Team</h4>
        */
        msg+="<h3>Dear "+json.getString("UserName")+"<br></h3>";
        msg+="<h3>We recieved a request to reset your Mouthpiece password. Click the link bellow to choose a new one:</h3>";
        msg+="<a href='"+json.getString("ChangeLink")+"' >Reset Your Password</a>";
        msg+="<h4>If you did not make this request or need assistance, please click <a href='"+json.getString("ErrorLink")+"'>here</a>.</h4>";
        msg+="<h4>From<br>Mouthpiece Omega Team</h4>";

        return msg;
   }

   public static String getSuccessfullUploadMessage(JSONObject json) throws JSONException {
        String msg="";
        /*
            <h3>Dear @Username<br></h3>
            <h3>Your mouth piece has been successfully uploaded</h3>
            <a href="url">View your upload</a>
            <h4>Dates:04/04/2020 <br> ID:8bnj2983m582k4i76</h4>
            <h4>From<br>Mouthpiece Omega Team</h4>
        */
        String date = getDate();
        msg+="<h3>Dear "+json.getString("UserName")+"<br></h3>";
        msg+="<h3>Your mouth piece has been successfully uploaded</h3>";
        msg+="<a href='"+json.getString("ViewLink")+"'>View your upload</a>";
        msg+="<h4>Dates:"+date+" <br> ID:"+json.getString("ID")+"</h4>";
        msg+="<h4>From<br>Mouthpiece Omega Team</h4>";
        return msg;
   }

   static String getDate()
   {
       Date date = new Date();
       SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
       String sDate = formatter.format(date);
       return sDate;
   }
    
}
