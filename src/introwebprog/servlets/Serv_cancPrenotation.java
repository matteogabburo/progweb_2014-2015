package introwebprog.servlets;

import com.sun.org.apache.xpath.internal.operations.Mult;
import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
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
 * Created by matteo on 17/01/16.
 */
public class Serv_cancPrenotation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("canc");

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

        MultisalaDAO dao = new MultisalaDAO();

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

            posti.add(dao.getPostoByRigaColonnaIdSpettacolo(posto.getRiga(), posto.getColonna(), idSpett));
        }

        HttpSession s = null;

        s = request.getSession(false);


        res += "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">" +
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
             res += "<div class=\"text-center\"><h2>Attenzione per procedere con l'eliminazione devi effettuare il login Effettua il login</h2>" +
                    "<a href=\"http://localhost:8080/CinemaMultisala_war_exploded\"><button class=\"btn btn-primary\" type=\"button\">Home</button></a></div>";
        }
        else {
            String mailUser;
            for(int i = 0; i < posti.size(); i++)
            {
                mailUser = dao.getMailFromPosto(posti.get(i));

                System.out.println("??????????????????????????????????????????????????" +mailUser);

                if(dao.cancellazionePrenotazione(posti.get(i)))
                {
                    MailSender mail = new MailSender();
                    try {
                        mail.sendCancellationMessage(mailUser, posti.get(i));
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    } catch (NamingException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    res += "<div class=\"text-center\"><h2>Errore cancellazione prenotazione</h2>" +
                            "<a href=\"http://localhost:8080/CinemaMultisala_war_exploded\"><button class=\"btn btn-primary\" type=\"button\">Home</button></a></div>";
                }
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
