package Controller.Servlet;

import Controller.Utils.MyUtils;
import Model.Class.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "/Home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = MyUtils.getLoginedUser(request.getSession());
        int type = user.getType();
        if(type == 3 || type == 4){
            request.setAttribute("limitHome", true);
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/HomeView.jsp");
        dispatcher.forward(request, response);
    }
}
