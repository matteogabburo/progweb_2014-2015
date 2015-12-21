package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Created by matteo on 19/12/15.
 */
public class Serv_filmPreviewTable extends HttpServlet {


    private final int NCOLUMN = 5;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        MultisalaDAO dao = new MultisalaDAO();
        ArrayList<Film> films = dao.allFilms();

        String res = "";

        if(films != null)
        {
            for (int i = 0; i < films.size(); i++)
            {
                if(i == 0)
                {
                    res += "<div class=\"row\">";
                }
                else if(i % this.NCOLUMN == 0)
                {
                    res += "</div>";
                    res += "<div class=\"row\">";
                }
                res += "<a href=\""+request.getRequestURL()+"film?id="+films.get(i).getIdFilm()+"\">";
                res += "<div class = \'col-md-2\'>";
                res += "<img alt=\""+films.get(i).getTitolo()+"\" class=\"img-responsive img-thumbnail\" src=\'"+films.get(i).getUriLocandina()+"\'/>";
                res += "<div class=\'title\'>"+films.get(i).getTitolo()+"</div>";
                res += "<div class=\'duration\'> Durata : "+films.get(i).getDurata()+" min</div>";
                res += "</div>";
                res += "</a>";
            }
            if(films.size() % this.NCOLUMN != 0)
            {
                res += "</div>";
            }
        }
        PrintWriter p = response.getWriter();
        p.println(res);

        request.setAttribute("previewFilms", res);
        /*RequestDispatcher view=request.getRequestDispatcher("/index.jsp");
        view.forward(request,response);*/
    }
}
