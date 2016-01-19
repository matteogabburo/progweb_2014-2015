package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by matteo on 21/12/15.
 */
public class Serv_openFilm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");

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


        if(param != null)
        {
            Integer tmp = Integer.parseInt(param);
            int idfilm = tmp.intValue();

            MultisalaDAO dao = new MultisalaDAO();
            Film film = dao.getFilmById(idfilm);


            String res = "";
            res +=  "<html>\n"+
                    "<head>\n" +
                    "<meta charset=\"utf-8\">" +
                    "    <title>Cinema Multisala</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                    "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                    "\n" +
                    "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                    "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                    "</head>\n" +
                    "<body>";

            res += responseWrapper.toString();

            res+=   "<div class=\"row\">" ;

            res +="<div class=\"col-md-1 sidebar\"></div>" ;
            res +="<div class=\"col-md-10\">" ;




            res += "<center><img alt=\""+film.getTitolo()+"\" class=\"film-img img-responsive img-thumbnail\" src=\'"+film.getUriLocandina()+"\'/></center>";


            res += "\n<div class=\"text-center\"><h2 class=\"text-center film-title\">Titolo</h2>\n"+film.getTitolo() + "</div>\n";
            res += "\n<div class=\"text-center\"><h2 class=\"text-center film-trama\">Trama</h2>\n"+film.getTrama() + "</div>\n";

            res += "\n<div class=\"text-center\"><h2 class=\"text-center film-trailer\">Trailer</h2>\n<iframe width=\"420\" height=\"345\" src=\""+film.getUrlTrailer()+"\"></iframe></div>";


            res += "\n" +
                    "<footer class=\"text-center\">\n" +
                    "\n" +

                    "<div class=\"row\">" +
                    "<a href=\"http://localhost:8080/CinemaMultisala_war_exploded/prenotation?id="+film.getIdFilm()+"\">" +
                    "<button type=\"button\" class=\"btn bottombutton btn-primary\">Prenota</button>"+
                    "</a>" +
                    "</div>"+

                    "</footer>\n";

                    res +="</div>";
                    res +="<div class=\"col-md-1 sidebar\"></div>" ;


                    res += "</div>" +
                    "</body>\n" +
                    "</html>";


            PrintWriter out = response.getWriter();
            out.println(res);
        }
    }
}
