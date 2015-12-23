package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by matteo on 23/12/15.
 */
public class Serv_logoff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        PrintWriter p = response.getWriter();
        p.println(request.getRequestURL().toString());

        String retPath = request.getRequestURL().toString();
        retPath = retPath.replace("/logoff", "");
        response.sendRedirect(retPath);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
