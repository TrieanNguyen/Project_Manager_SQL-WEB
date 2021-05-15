package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.*;

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
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProjectListServlet", urlPatterns = "/ProjectList")
public class ProjectListServlet extends HttpServlet {
    public static List<Project> listProject = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        String hC = request.getParameter("hasChange");
        String error = null;
        String styleButtonInsert = "";
        String styleButtonDelete = "";
        String styleButtonEdit = "";
        Boolean limitHome = false;
        Boolean hasError = false;
        User user = MyUtils.getLoginedUser(request.getSession());
        int type = user.getType() ;
        try {
            if(type == 1){
                listProject = DBUtils.getAllListProjectAndTask(connection);
            }
            else if(type == 2){
                listProject = DBUtils.getAllListProjectAndTaskOfDepartment(connection, user);
            }
            else if(type == 3){
                Employee employee = DBUtils.findEmployee(connection, user.getEmployeeId());
                Department department = DBUtils.findDepartment(connection, employee.getDepartmentId());
                listProject = DBUtils.getAllListProjectAndTaskOfDepartment(connection, department.getLeader(), user.getEmployeeId());
                limitHome = true;
                styleButtonInsert = "display: none";
                styleButtonDelete = "display: none";
            }
            else if(type == 4){
                listProject = DBUtils.getAllListProjectAndTaskOfEmployee(connection, user);
                limitHome = true;
/*                request.setAttribute("limitHome", true);*/
                styleButtonInsert = "display: none";
                styleButtonDelete = "display: none";
                styleButtonEdit = "display: none";
                /*for (int i = 0; i < listProject.size(); i++) {
                    listProject.get(i).setStyle("display: none");
                }*/
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            hasError = true;
            error = "Can not show list Project";
        }
        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("styleButtonInsert", styleButtonInsert);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/views/Project.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            if(hC != null){
                request.setAttribute("success", true);
                request.setAttribute("info", "Đã xóa dự án thành công!");
            }
            request.setAttribute("limitHome", limitHome);
            request.setAttribute("type", type);
            request.setAttribute("styleButtonInsert", styleButtonInsert);
            request.setAttribute("styleButtonDelete", styleButtonDelete);
            request.setAttribute("styleButtonEdit", styleButtonEdit);
            request.setAttribute("listProject", listProject);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/Project.jsp");
            dispatcher.forward(request, response);
        }
    }
}
