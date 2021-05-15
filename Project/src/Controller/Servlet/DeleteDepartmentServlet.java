package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
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

@WebServlet(name = "DeleteDepartmentServlet", urlPatterns = "/DeleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    private static int idDepartment;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idClick");
        idDepartment = Integer.valueOf(id);
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        String error = null;
        Boolean hasError = false;
        Department department = null;
        try {
            department = DBUtils.findDepartment(conn, idDepartment);
        } catch (Exception e){
            e.printStackTrace();
        }
        User user = MyUtils.getLoginedUser(request.getSession());
        if(user.getEmployeeId() != 1 && department.getLeader() != user.getEmployeeId()){
            response.sendRedirect(request.getContextPath() + "/DepartmentList");
        }
        else {
            try{
                DBUtils.deleteDepartment(conn, idDepartment, session);
            }
            catch (Exception e){
                hasError = true;
                error = "Detected error when delete the department, please try again.";
            }
            if(hasError){
                request.setAttribute("error", error);
            }
            response.sendRedirect(request.getContextPath() + "/DepartmentList?hasDeleted=true");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
