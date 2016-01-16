package introwebprog.utility;

/**
 * Created by matteo on 16/01/16.
 */

import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSender
{
    String to= "";
    String from = "matteogabburo@gmail.com";
    String host = "localhost";
    String subject = "MULTISALA";
    String message = "PRENOTAZIONE EFFETTUATA";

    public void sendMessage(Utente u, Spettacolo s)
    {
        this.to = u.getEmail();

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(this.from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(this.subject);

            // Now set the actual message
            message.setText(this.message);

            // Send message
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}