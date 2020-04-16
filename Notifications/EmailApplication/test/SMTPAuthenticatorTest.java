import javax.mail.PasswordAuthentication;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Henco
 */
public class SMTPAuthenticatorTest {
    
    public SMTPAuthenticatorTest() {
    }
    
    

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
