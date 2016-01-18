package introwebprog.utility;

/**
 * Created by matteo on 16/01/16.
 */

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;

import java.io.IOException;
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

    public MailSender()
    {}

    public void sendPassword(String mail) throws NamingException, MessagingException {
        String to= mail;
        String subject = "RECUPERO PASSWORD MULTISALA";
        MultisalaDAO dao = new MultisalaDAO();
        String messaggio = "La tua password è : "+ dao.getPasswordByMail(mail);

        sendMessage(to, subject, messaggio, null);
    }

    public void sendPrenotationMessage(String u, int s) throws NamingException, MessagingException {
        String to = u;
        //TODO aggiungere spettacolo e creazione pdf e qr code

        System.out.println(" ================================= "+to);

        String subject = "PRENOTAZIONE MULTISALA";
        String messaggio = "Prenotazione effettuata con successo";

        //gen qrcode
        GenQrCode genQr = new GenQrCode();

        //gen pdf
        String ticketname = to+s;
        GenPdf gen = new GenPdf();
        String allegato = null;

        Image qr = null;
        try {
            qr = Image.getInstance(genQr.genQR(messaggio).toByteArray());
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            allegato = gen.createPdf(ticketname, messaggio, qr);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        sendMessage(to, subject, messaggio, allegato);
    }

    public void sendMessage(String to, String subject, String messagetext, String allegato) throws NamingException, MessagingException
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
        //msg.setText(messagetext);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        // Now set the actual message
        messageBodyPart.setText(messagetext);
        // Create a multipar message
        Multipart multipart = new MimeMultipart();
        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        if(allegato != null) {
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(allegato);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(allegato);
            multipart.addBodyPart(messageBodyPart);
        }

        // Send the complete message parts
        msg.setContent(multipart);

        Transport.send(msg);
    }

    public void sendCancellationMessage(String mailUser, Posto posto) throws MessagingException, NamingException {
        String to= mailUser;
        String subject = "CANCELLAZIONE PRENOTAZIONE";
        MultisalaDAO dao = new MultisalaDAO();
        String messaggio = "La tua prenotazione è stata cancellata, in particolare il posto collocato in "+
                "riga : " +posto.getRiga() +" e colonna "+posto.getColonna();

        sendMessage(to, subject, messaggio, null);
    }
}