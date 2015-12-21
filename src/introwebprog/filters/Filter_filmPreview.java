package introwebprog.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by matteo on 21/12/15.
 */
public class Filter_filmPreview implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
