
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONObject;


public class Message {
   private static String companyName="COS301 OMEGA Group";
   private static String PASSWORD_CHANGE_URL="passwordChange.html";
   private static String VERIFY_EMAIL_URL="verify.html";
   private static String UPDATES_PAGE_URL="updates.html";
    
    public static MimeMessage createMessage(JSONObject json,Session session) throws AddressException, MessagingException
    {
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
   
    
    public static String getMessageContent(JSONObject json)
    {
        String content="";
        String type=json.getString("MsgType");
        
        switch(type)
        {
            case "validate":
                    content= getValidateMessage(json);
                    break;
                    
            case "passwordChange":
                    content=getPasswordChangeMessage(json);
                    break;
            case "update":
                    content= getUpdateMessage(json);
                    break;
            default:
                content="";
                break;
        }
        
        
        return content;
    }
    
    
    // we can add messages here in the form of using htmlformat that would be
    //inside of the body tag 
    //must return a string containing this info 
    public static String getValidateMessage(JSONObject json)
    {
        String msg ="<h3>Good Day @"+json.getString("UserName")+"</h3>";
        msg+="<p>We at "+companyName+" would like to welcome you to our community.<br>";
        msg+="We are almost done with, @"+json.getString("UserName")+". We Just need to verify your email <br>";
        msg+=json.getString("UserEmail")+"</p>";
        
        msg+="<a href='"+VERIFY_EMAIL_URL+"'>click here</a>";
        msg+="<p>Once verified you will be able to let your emagination fuel your creativity with boundless fun.</p>";
        return msg;
    }
    public static String getPasswordChangeMessage(JSONObject json)
    {
     String msg ="<h3>Good Day @"+json.getString("UserName")+"</h3>";
        msg+="<p>We at "+companyName+" will help you to change your password.<br>";
        msg+="Pleas click on the link provided at the bottom to change the Login password for the following email address: ";
        msg+=json.getString("UserEmail")+"</p>";
        
        msg+="<a href='"+PASSWORD_CHANGE_URL+"'>click here</a>";  
        
        msg+="<p>We hope that this helped you.</p>";
        return msg;
    }
    public static String getUpdateMessage(JSONObject json)
    {
        String msg ="<h3>Good Day Comunity</h3>";
        msg+="<p>We at "+companyName+" would like to inform you of the following update.<br>";
        msg+=json.getString("Update");
        
        msg+="<br>Please visit our update page for the full scoop by clicking on the link bellow</p>";
        msg+="<a href='"+UPDATES_PAGE_URL+"'>click here</a>";
        return msg;
    }
    
    
}
