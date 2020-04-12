/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author flavoured.squid
 */

import java.util.ArrayList;
import org.json.*;
public class EmailUnitTesting {
    
    ArrayList<JSONObject> units;
    
    public EmailUnitTesting()
    {
        units = new ArrayList();
       
        
    }
    
    public void createUnits()
    {
        //create a bunch of units to be tested
        JSONObject json = Email.createJSON("someEmail1@gmial.com", "MadMan", "confirmation", "hello ");
        units.add(json);
        JSONObject json1 = Email.createJSON("AnotherEmail@emails.com", "lala", "changePassword", "cry baby");
        units.add(json1);
    }
    //test the inside of he body
    
    public void testJsonObjects()
    {
        
    }
    
    public void bodyTest()
    {
        //test body of email 1 , verification email
       String bodyRetrieved = Message.getMessageContent(units.get(0));
       String correctBody = "<h3>Good Day @"+units.get(0).getString("UserName")+"</h3>";
       correctBody+= "<p>We at COS301 OMEGA Group would like to welcome you to our community.<br>";
       correctBody+="We are almost done with, @"+units.get(0).getString("UserName")+". We Just need to verify your email <br>";
       correctBody+= units.get(0).getString("UserEmail")+"</p>";
       correctBody+="<a href='verify.html'>click here</a>";
       correctBody+= "<p>Once verified you will be able to let your emagination fuel your creativity with boundless fun.</p>";
       
       if (bodyRetrieved.compareTo(correctBody)==0)
       {
           System.out.println("Body testing 1 with type:"+units.get(0).getString("MsgType")+" successful");
           
       }
       else{
           System.out.println("Error in body testing 1 \n retrieved body : "+bodyRetrieved+"\nNot equal to: "+correctBody);
       }
       
        
    }
        
}
