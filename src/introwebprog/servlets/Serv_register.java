package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Film;
import introwebprog.models.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by matteo on 23/12/15.
 */
public class Serv_register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");
        String passwd = request.getParameter("pwd");
        System.out.println("mail : " +mail);
        System.out.println("password : " +passwd);

        if(mail != null && passwd != null)
        {
            MultisalaDAO dao = new MultisalaDAO();

            Utente u = new Utente();
            u.setEmail(mail);
            u.setPassword(passwd);
            u.setCredito(0.0);//inizialmente vuoto


            PrintWriter p = response.getWriter();
            if(dao.registerNewUser(u))
            {
                p.println("Registration successfull");
            }
            else
            {
                p.println("Registration not successfull");
            }

            //String retPath = request.getRequestURL().toString();
            //retPath = retPath.replace("/register", "");

            //temporaneo
            String retPath = "http://localhost:8080/CinemaMultisala_war_exploded/";

            response.sendRedirect(retPath);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
