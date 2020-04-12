
import javax.mail.PasswordAuthentication;


public class SMTPAuthenticator extends javax.mail.Authenticator
{
    
          @Override
          public PasswordAuthentication getPasswordAuthentication() {
           String username = Email.getSMTPAuthUser();
           String password = Email.getSMTPAuthPassword();
           return new PasswordAuthentication(username, password);
        }
    
}
