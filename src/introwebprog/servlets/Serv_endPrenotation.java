package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;

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
        Posto posto;

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


            posto = new Posto();
            posto.setRiga(x);
            posto.setColonna(y);
            posti.add(posto);
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
            if(dao.newPrenotation(String.valueOf(s.getAttribute("USER_MAIL")), posti, idSpett) == true)
            {
            /*MailSender mail = new MailSender();
            mail.sendMessage*/
                res += s.getAttribute("USER_MAIL");
                res += "<h2>Prenotazione completata, controlla la tua mail!</h2>";
            }
            else
            {
                res += "<h2>Errore registrazione prenotazione</h2>";
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
