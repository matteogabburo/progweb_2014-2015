package introwebprog.utility;

/**
 * Created by matteo on 16/01/16.
 */

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.swing.text.AbstractDocument;

public class MailSender
{
    String from = "matteogabburo@gmail.com";
    //String from = "multisala";
    String host = "smtp.gmail.com";

    public void sendPassword(String mail) throws NamingException, MessagingException {
        String to= mail;
        String subject = "RECUPERO PASSWORD MULTISALA";
        MultisalaDAO dao = new MultisalaDAO();
        String messaggio = "La tua password è : "+ dao.getPasswordByMail(mail);

        sendMessage(to, subject, messaggio);
    }

    public void sendPrenotationMessage(String u, int s) throws NamingException, MessagingException {
        String to = u;
        //TODO aggiungere spettacolo e creazione pdf e qr code

        System.out.println(" ================================= "+to);

        String subject = "PRENOTAZIONE MULTISALA";
        String messaggio = "Prenotazione effettuata con successo";

        sendMessage(to, subject, messaggio);
    }

    public void sendMessage(String to, String subject, String messagetext) throws NamingException, MessagingException
    {
        InitialContext ic = new InitialContext();
        String snName = "mail/MyMailSession";
        Session session = (Session)ic.lookup(snName);

        Properties props = session.getProperties();
        props.put("mail.from", from);

        Message msg = new MimeMessage(session);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setFrom();
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setText(messagetext);

        Transport.send(msg);
    }
}