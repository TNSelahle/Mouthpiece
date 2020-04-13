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
    int count=0;
    ArrayList<String[]> test;
    public EmailUnitTesting() {
        units = new ArrayList();
        test = new ArrayList();
    }

    public void createUnits() {
        //create a bunch of units to be tested
        
        //test cases
        //to add case
        //add
        //
        //a =new String[]{"<email>", "<name>", "<tipe>", "<heding>"};
        //test.add(a);
        String[] a =new String[]{"u15285830@tuks.co.za", "MadMan", "confirmation", "hello "};
        test.add(a);
        a =new String[]{"GreenDragon150@gmail.com", "lala", "changePassword", "cry baby"};
        test.add(a);
        
        
        test.forEach((n)->units.add(Email.createJSON(n[0],n[1],n[2],n[3])));
        
        testJsonObjects();
        bodyTest();

    }

    //test the Json
    public void adaptifTestJsonObjects(JSONObject n) {
        int num =count ++;
        // exspeted
        String[] a =test.get(num);
        String exspeted="{\"UserName\":\""+a[1]+"\",\"MsgType\":\""+a[2]+"\",\"UserEmail\":\""+a[0]+"\",\"Subject\":\""+a[3]+"\"}";
        if (n.toString().compareTo(exspeted) == 0) {
            System.out.println("Json testing "+count+" successful");
        } else {
            System.out.println("Json testing "+count+" unsuccessful");
            System.out.println("exspeted"+exspeted);
            System.out.println("resewed " + n.toString());

        }
        
    }

    public void testJsonObjects() {

        System.out.println("Json absaloot testing");
        // absaloot testing
        JSONObject testJson = units.get(0);
        if (testJson.toString().compareTo("{\"UserName\":\"MadMan\",\"MsgType\":\"confirmation\",\"UserEmail\":\"u15285830@tuks.co.za\",\"Subject\":\"hello \"}") == 0) {
            System.out.println("Json testing 1  successful");
        } else {
            System.out.println("Json testing 1  unsuccessful");
            System.out.println("exspeted " + "{\"UserName\":\"MadMan\",\"MsgType\":\"confirmation\",\"UserEmail\":\"u15285830@tuks.co.za\",\"Subject\":\"hello \"}");
            System.out.println("resewed " + testJson.toString());

        }
        testJson = units.get(1);
        if (testJson.toString().compareTo("{\"UserName\":\"lala\",\"MsgType\":\"changePassword\",\"UserEmail\":\"GreenDragon150@gmail.com\",\"Subject\":\"cry baby\"}") == 0) {
            System.out.println("Json testing 2  successful");
        } else {
            System.out.println("Json testing 2  unsuccessful");
            System.out.println("exspeted " + "{\"UserName\":\"lala\",\"MsgType\":\"changePassword\",\"UserEmail\":\"GreenDragon150@gmail.com\",\"Subject\":\"cry baby\"}");
            System.out.println("resewed " + testJson.toString());

        }

        System.out.println("");
        System.out.println("Json adaptif testing");
        count=0;
        units.forEach((n)->adaptifTestJsonObjects(n));
        System.out.println ("");
    }
    
    

    

    public void bodyTest() {
        //test body of email 1 , verification email
        String bodyRetrieved = Message.getMessageContent(units.get(0));
        String correctBody = "<h3>Good Day @" + units.get(0).getString("UserName") + "</h3>";
        correctBody += "<p>We at COS301 OMEGA Group would like to welcome you to our community.<br>";
        correctBody += "We are almost done with, @" + units.get(0).getString("UserName") + ". We Just need to verify your email <br>";
        correctBody += units.get(0).getString("UserEmail") + "</p>";
        correctBody += "<a href='verify.html'>click here</a>";
        correctBody += "<p>Once verified you will be able to let your emagination fuel your creativity with boundless fun.</p>";

        if (bodyRetrieved.compareTo(correctBody) == 0) {
            System.out.println("Body testing 1 with type:" + units.get(0).getString("MsgType") + " successful");

        } else {
            System.out.println("Error in body testing 1 \n retrieved body : " + bodyRetrieved + "\nNot equal to: " + correctBody);
        }
        //test body of email 2 , verification email
        bodyRetrieved = Message.getMessageContent(units.get(1));
        correctBody = "<h3>Good Day @" + units.get(1).getString("UserName") + "</h3>";
        correctBody += "<p>We at COS301 OMEGA Group would like to welcome you to our community.<br>";
        correctBody += "We are almost done with, @" + units.get(1).getString("UserName") + ". We Just need to verify your email <br>";
        correctBody += units.get(1).getString("UserEmail") + "</p>";
        correctBody += "<a href='verify.html'>click here</a>";
        correctBody += "<p>Once verified you will be able to let your emagination fuel your creativity with boundless fun.</p>";

        if (bodyRetrieved.compareTo(correctBody) == 0) {
            System.out.println("Body testing 2 with type:" + units.get(1).getString("MsgType") + " successful");

        } else {
            System.out.println("Error in body testing 2 \n retrieved body : " + bodyRetrieved + "\nNot equal to: " + correctBody);
        }

    }

}
