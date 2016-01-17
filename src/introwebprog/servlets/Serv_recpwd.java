package introwebprog.servlets;

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

/**
 * Created by matteo on 16/01/16.
 */
public class Serv_recpwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mail = request.getParameter("mail");

        if(mail != null) {
            MailSender s = new MailSender();

            try {
                s.sendPassword(mail);
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        String retPath = "http://localhost:8080/CinemaMultisala_war_exploded/";
        response.sendRedirect(retPath);
    }
}
