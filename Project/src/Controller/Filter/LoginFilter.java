package Controller.Filter;

import Controller.Utils.MyUtils;
import Model.Class.User;
/*import com.sun.deploy.net.HttpResponse;*/

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName = "LoginFilter" ,urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (this.needJDBC(req)){
            HttpSession session = req.getSession();
            User user = MyUtils.getLoginedUser(session);
            String path = req.getServletPath();
            if(path.equals("/Login") || path.equals("/Logout") || path.equals("/ForgotPassword"))
            {
                chain.doFilter(request,response);
            }
            else
            if(user == null)
            {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect(req.getContextPath() + "/");
            }
            else{
                chain.doFilter(request, response);
            }
        }
        else{
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }
    private boolean needJDBC(HttpServletRequest request) {

        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null) {

            urlPattern = servletPath + "/*";
        }

        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();

        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
