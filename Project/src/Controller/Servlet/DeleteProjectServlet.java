package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Project;
import Model.Class.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DeleteProjectServlet", urlPatterns = "/DeleteProject")
public class DeleteProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        String i = request.getParameter("projectId");
        HttpSession session = request.getSession();
        User user = MyUtils.getLoginedUser(session);
        String error = null;
        Boolean hasError = false;
        int id = Integer.valueOf(i);
        Project project = null;
        try {
            project = DBUtils.findProject(connection,id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(project.getLeader()!=user.getEmployeeId() && user.getType()!=1)
        {
            response.sendRedirect(request.getContextPath() + "/ProjectList");
            return;
        }
            try {
                DBUtils.deleteProject(connection, id, user.getEmployeeId());
            }catch (Exception e){
                e.printStackTrace();
                hasError = true;
            }

            if(hasError){
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/Project.jsp");
                dispatcher.forward(request, response);
            }
            else{
                response.sendRedirect(request.getContextPath() + "/ProjectList?hasChange=true");
            }
        }

}
