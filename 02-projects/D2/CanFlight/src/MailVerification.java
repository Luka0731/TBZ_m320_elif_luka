import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailVerification {
    String receiver;
    public MailVerification() {
    }
    public void sendVerificationMail(String receiver, Flight flight) {

        String pswd = "wvsc mwuo hbsg wmhl";
        String email = "fortnermontag@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pswd);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Thank you for your registration");
            message.setText("Your informations: "+flight.getAirLines()+"\n"+
            "Name: "+flight.getFlightNumber()+" Destination: "+flight.getDestination());
            Transport.send(message);

            System.out.println("Email send with Sucess!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }
}
