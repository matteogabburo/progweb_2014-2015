package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
import introwebprog.models.Prenotazione;
import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;
import introwebprog.utility.MailSender;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matteo on 16/01/16.
 */
public class Serv_endPrenotation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("prenotation");

        String res = "";

        //************
        //res += param;
        //***********

        //Parsing param

        //da S a P c'è id Spettacolo
        int idSpett = Integer.parseInt(param.substring(1, param.indexOf("P")));

        //da P ci sono i posti con coordinate x e y
        int x;
        int y;
        int i1 = param.indexOf("imx");
        int i2 = param.indexOf("imy");

        List<Posto> posti = new ArrayList<>();
        List<Integer> prezzi = new ArrayList<>();
        Posto posto;
        Integer pre;

        while(param.contains("im"))
        {
            i1 = param.indexOf("imx")+3;
            i2 = param.indexOf("y");
            x = Integer.parseInt(param.substring(i1, i2));
            param = param.substring(i2);
            i1 = param.indexOf("y")+1;
            i2 = param.indexOf("A");
            y = Integer.parseInt(param.substring(i1, i2));
            param = param.substring(i2);

            i1 = param.indexOf("PRE")+3;
            i2 = param.indexOf("B");
            pre = Integer.parseInt(param.substring(i1, i2));
            param = param.substring(i2);


            posto = new Posto();
            posto.setRiga(x);
            posto.setColonna(y);
            posti.add(posto);
            prezzi.add(pre);
        }

        HttpSession s = null;

        s = request.getSession(false);


        res += "<html>\n" +
                "<head>\n" +
                "    <title>Cinema Multisala</title>\n" +
                "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                "</head>" +
                "<body>";

        if(s.getAttribute("USER_MAIL") == null)
        {
            res += "<h2>Attenzione per procedere con l'acquisto devi effettuare il login Effettua il login</h2>";
        }
        else {

            MultisalaDAO dao = new MultisalaDAO();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            //controllo se sono ancora disponibile i posti
            List<Posto> postiCeck = dao.getPostiByIdSpett(idSpett);
            boolean ceck = true;
            for(int i = 0; i < postiCeck.size(); i++)
            {
                if(ceck == true)
                    for(int j = 0; j < posti.size(); j++)
                    {
                        if(posti.get(j).getRiga() == postiCeck.get(i).getRiga() && posti.get(j).getColonna() == postiCeck.get(i).getColonna())
                        {
                            ceck = false;
                        }
                    }
            }

            if(ceck == true) {
                String mailuser = String.valueOf(s.getAttribute("USER_MAIL"));
                if (dao.newPrenotation(mailuser, posti, idSpett, prezzi, prenotazioni) == true) {
                    MailSender mail = new MailSender();
                    try {
                        mail.sendPrenotationMessage(mailuser, prenotazioni);
                    } catch (NamingException e) {
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    res += s.getAttribute("USER_MAIL");
                    res += "<h2>Prenotazione completata, controlla la tua mail!</h2>";
                } else {
                    res += "<h2>Errore registrazione prenotazione</h2>";
                }
            }
            else
            {
                res += "<h2>Attenzione, i posti scelti non sono più disponibili</h2>";
            }
        }
        res +=  "</body>\n" +
                "</html>";

        PrintWriter out = response.getWriter();
        out.println(res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

