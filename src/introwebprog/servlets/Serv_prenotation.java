package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Spettacolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by matteo on 11/01/16.
 */
public class Serv_prenotation extends HttpServlet {

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


        String res = "";
        res += "<html>\n" +
                "<head>\n" +
                "    <title>Cinema Multisala</title>\n" +
                "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>" ;

        res +="<div class=\"col-md-1 sidebar\"></div>" ;
        res +="<div class=\"col-md-10\">" ;

        res += responseWrapper.toString();


            if (param != null) {
                Integer tmp = Integer.parseInt(param);
                int idfilm = tmp.intValue();

                MultisalaDAO dao = new MultisalaDAO();
                List<Spettacolo> spettacoli = dao.getSpettacoloByFilmId(idfilm);


                for (int i = 0; i < spettacoli.size(); i++) {

                    res += "<div class=\"row text-center\">";
                    res += spettacoli.get(i).getDataOra();
                    res += "<a href=\"http://localhost:8080/CinemaMultisala_war_exploded/prenotation/spett?id=" + spettacoli.get(i).getIdSpettacolo() + "\">" +
                            "<button type=\"button\" class=\"btn btn-primary left\">Prenota</button>" +
                            "</a>" +
                            "</div>" +
                            "</br>";
                }
            }

        res += "\n" +
                "<footer>\n" +
                "</footer>\n";

        res +="</div>";
        res +="<div class=\"col-md-1 sidebar\"></div>" ;
        res +="</body>\n" +
                "</html>";

        PrintWriter out = response.getWriter();
        out.println(res);
    }
}
