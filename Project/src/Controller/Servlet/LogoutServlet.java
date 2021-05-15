package Controller.Servlet;

import Controller.Utils.MyUtils;
import Model.Class.User;
/*import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;*/

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MyUtils.deleteLoginUser(session);
/*        User user = MyUtils.getLoginedUser(session);
        System.out.println(user.getEmployeeId() + " ma nhan vien");*/
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
        dispatcher.forward(request, response);
    }
}
