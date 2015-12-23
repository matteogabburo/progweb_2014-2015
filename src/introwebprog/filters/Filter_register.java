package introwebprog.filters;

import introwebprog.dao.MultisalaDAO;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by matteo on 23/12/15.
 */
public class Filter_register implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String mail = req.getParameter("mail");
        String pwd = req.getParameter("pwd");

        boolean cond = true;
        //ceck pwd
        if(pwd.length() < 7)
            cond = false;

        //ceck mail
        MultisalaDAO dao = new MultisalaDAO();
        if(dao.existThisMail(mail))
            cond = false;

        if(cond == true) {
            chain.doFilter(req, resp);
        }
        else
        {
            PrintWriter p = resp.getWriter();
            p.print("Error\n");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
