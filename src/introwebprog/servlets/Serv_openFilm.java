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

        System.out.println("BUONGIORNO *********************************************************************************");

        if(param != null)
        {
            Integer tmp = Integer.parseInt(param);
            int idfilm = tmp.intValue();

            MultisalaDAO dao = new MultisalaDAO();
            Film film = dao.getFilmById(idfilm);

            String res = "";
            res += film.getTitolo();
            res += film.getTrama();
            res += film.getUriLocandina();

            PrintWriter out = response.getWriter();
            out.println(res);
        }
    }
}
