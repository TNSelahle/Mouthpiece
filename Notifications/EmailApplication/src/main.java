

import org.json.JSONObject;

public class main {
    
    public static void main (String[]args)
    {
        JSONObject json = new JSONObject();
        
        json.put("UserEmail","cosomegatest@gmail.com");
        json.put("UserName","squidBro");
        json.put("MsgType", "validate");
        json.put("Subject", "My 301 email");
        json.put("Message","Hello there, General Grievis");
        
        
        Email.email(json);
        
        
    }
    
}
