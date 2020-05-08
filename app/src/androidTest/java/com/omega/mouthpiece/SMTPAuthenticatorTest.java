package com.omega.mouthpiece;

import org.junit.Test;

import javax.mail.PasswordAuthentication;

import static org.junit.Assert.*;

public class SMTPAuthenticatorTest {
    /**
     * Test of getPasswordAuthentication method, of class SMTPAuthenticator.
     */
    @Test
    public void testGetPasswordAuthentication() {
        System.out.println("getPasswordAuthentication");
        SMTPAuthenticator instance = new SMTPAuthenticator();
        PasswordAuthentication result = instance.getPasswordAuthentication();
        assertEquals(result.getPassword(),Email.getSMTPAuthPassword() );
        assertEquals(result.getUserName(),Email.getSMTPAuthUser() );
    }
}