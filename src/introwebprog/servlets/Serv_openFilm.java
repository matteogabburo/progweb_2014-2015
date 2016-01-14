package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by matteo on 21/12/15.
 */
public class Serv_openFilm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");

        if(param != null)
        {
            Integer tmp = Integer.parseInt(param);
            int idfilm = tmp.intValue();

            MultisalaDAO dao = new MultisalaDAO();
            Film film = dao.getFilmById(idfilm);


            String res = "";
            res +=  "<html>\n"+
                    "<head>\n" +
                    "    <title>Cinema Multisala</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                    "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                    "\n" +
                    "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                    "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                    "</head>\n" +
                    "<body>";



            request.getRequestDispatcher("/includes/header.jsp").include(request, response);


            res += "<img alt=\""+film.getTitolo()+"\" class=\"film-img img-responsive img-thumbnail\" src=\'"+film.getUriLocandina()+"\'/>";


            res += "\n<div class=\"film-title\"><h2>Titolo :</h2> \n"+film.getTitolo() + "</div>\n";
            res += "\n<div class=\"film-trama\"><h2>Trama :</h2> \n"+film.getTrama() + "</div>\n";


            res += "\n" +
                    "<footer>\n" +
                    "\n" +

                    "<div class=\"row\">" +
                    "<a href=\"http://localhost:8080/CinemaMultisala_war_exploded/prenotation?id="+film.getIdFilm()+"\">" +
                    "<button type=\"button\" class=\"btn btn-primary\">Prenota</button>"+
                    "</a>" +
                    "</div>"+

                    "</footer>\n" +
                    "\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";


            PrintWriter out = response.getWriter();
            out.println(res);
        }
    }
}
