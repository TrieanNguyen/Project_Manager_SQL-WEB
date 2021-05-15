package Controller.Utils;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

public class MailUtils {
    public static int sendMail(String email, String description, String content){
        try{
            String accountName="datnhieu18@gmail.com";
            String accountPassword="Datnhieu1819";
            Properties p=new Properties();
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "465");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.setProperty("mail.smtp.socketFactory.fallback", "false");
            Authenticator authen=new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){

                    return new PasswordAuthentication(accountName,accountPassword);
                }
            };
            Session s=Session.getInstance(p,authen);
            Message msg=new MimeMessage(s);
            msg.setFrom(new InternetAddress(accountName));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(MimeUtility.encodeText(description,  "utf-8", "Q"));
            msg.setContent(content,"text/html; charset=UTF-8");
            Transport.send(msg);
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
