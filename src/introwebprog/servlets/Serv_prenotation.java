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
 * Created by matteo on 11/01/16.
 */
public class Serv_prenotation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");

        if (param != null) {
            Integer tmp = Integer.parseInt(param);
            int idfilm = tmp.intValue();

            MultisalaDAO dao = new MultisalaDAO();
            Film film = dao.getFilmById(idfilm);


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
                    "<body>";


            request.getRequestDispatcher("/includes/header.jsp").include(request, response);

            res += "\n" +
                    "<footer>\n" +
                    "</footer>\n" +
                    "CIAO</br>" +
                    "</body>\n" +
                    "</html>";


            PrintWriter out = response.getWriter();
            out.println(res);
        }
    }
}
