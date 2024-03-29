package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by matteo on 17/01/16.
 */
public class Serv_user extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //responseWrapper importante per includere header ********************************************
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter sw = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(sw);
            }

            @Override
            public String toString() {
                return sw.toString();
            }
        };
        request.getRequestDispatcher("/includes/header.jsp").include(request, responseWrapper);
        //**************************************************************************************


        MultisalaDAO dao = new MultisalaDAO();


        String res = "";

        res += "<html>\n" +
                "<head>" +
                "<meta charset=\"utf-8\">\n" +
                "    <title>Cinema Multisala</title>\n" +
                "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                "</head>";

        res +="<body>";
        res += responseWrapper.toString();

        res +="<div class=\"col-md-1 sidebar\"></div>" ;
        res +="<div class=\"col-md-10\">" ;


        //ceck ruolo utente, se admin fai roba admin altrimenti prenotazioni effettuate da utente normale

        //cose per admin :
        //  - vedere lista posti venduti per ogni spettacolo
        //  - lista incassi per film
        //  - lista dei clienti top
        //  - disdire prenotazione


        HttpSession s = null;
        s = request.getSession(false);

        Utente u = dao.getUtenteByMail((String)s.getAttribute("USER_MAIL"));

        System.out.println("= = = = = = = = = > "+ u.getEmail() + u.getIdRuolo());


        String body= "";
        if(dao.isAdmin(u))
        {
            //tabella spettacoli

            List<Film> films = dao.allFilms();

            body += "<div class=\"row adminrow\">";
            body += "<div class = \'col-md-4\'>";
            body += "<h3>Film</h3>";
            body += "</div>";
            body += "<div class = \'col-md-4\'>";
            body += "<h3>Spettacoli</h3>";
            body += "</div>";
            body += "<div class = \'col-md-4\'>";
            body += "<h3>Guadagno</h3>";
            body += "</div>";
            body += "</div>";

            for(int i = 0; i < films.size(); i++) {
                body += "<div class=\"row adminrow\">";
                body += "<div class = \'col-md-4\'>";
                body += films.get(i).getTitolo();
                body += "</div>";

                //spettacoli per film
                body += "<div class = \'col-md-4\'>";
                List<Spettacolo> spettacoli = dao.getSpettacoloByFilmId(films.get(i).getIdFilm());
                for(int j = 0; j < spettacoli.size(); j++ )
                {
                    body += "<a href =\"http://localhost:8080/CinemaMultisala_war_exploded/user/spettacolo?id="+spettacoli.get(j).getIdSpettacolo()+"\">";
                    body += spettacoli.get(j).getDataOraToString();
                    body += "</a></br>";
                }

                body += "</div>";

                //guadagno totale per film
                body += "<div class = \'col-md-4\'>";
                body += dao.getGuadagnoPerFilm(dao.getSpettacoloByFilmId(films.get(i).getIdFilm())) +" €";
                body += "</div>";

                body += "</div>";
            }
        }
        else
        {

            res += "USER MODE";// TODO: 17/01/16
        }

        res += body;
        res += "\n" +
                "<footer>\n" +

                "</footer>\n" ;

        res +="</div>" ;
        res +="<div class=\"col-md-1 sidebar\"></div>" ;


        res +="</body>\n" +
                "</html>";


        PrintWriter out = response.getWriter();
        out.println(res);
    }
}
