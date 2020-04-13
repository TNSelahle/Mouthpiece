

import org.json.JSONObject;

public class main {
    
    public static void main (String[]args)
    {
//        JSONObject json = new JSONObject();
        
//        json.put("UserEmail","");
//        json.put("UserName","squidBro");
//        json.put("MsgType", "validate");
//        json.put("Subject", "My 301 email");
//        json.put("Message","Hello there, General Grievis");
        
//        
//        Email.email(json);
        
        Email.SendSuccessfullUploadEmail("jaco.fab4@gmail.com", "madBunny", "https://up.ac.co.za", "abcd123");
    }
    
}
