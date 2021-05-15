package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Employee;
import Model.Class.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DepartmentListServlet", urlPatterns = "/DepartmentList")
public class DepartmentListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String hasInsert = request.getParameter("hasInsert");
        String hasDeleted = request.getParameter("hasDeleted");
        String hasEdit = request.getParameter("hasEdit");
        if(hasInsert != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Thêm phòng ban thành công");
        }
        if(hasDeleted != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Xóa phòng ban thành công");
        }
        if(hasEdit != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Sửa phòng ban thành công");
        }
        String errorString = null;
        List<Department> departmentList = null;
        Map<Department, String > listNameLeaderOfDepartment = new HashMap<Department, String>();
        User user = MyUtils.getLoginedUser(request.getSession());
        int type = user.getType();
        try{
            if(type == 1)
            {
                listNameLeaderOfDepartment = DBUtils.getListNameLeaderOfDepartment(conn);
                request.setAttribute("isAdmin", true);
            }
            else if(type == 2)
            {
                Employee employee = DBUtils.findEmployee(conn, user.getEmployeeId());
                int departmentId = DBUtils.findDepartment(conn, employee.getDepartmentId()).getId();
                listNameLeaderOfDepartment = DBUtils.getListNameLeaderOfDepartment(conn, departmentId);
            }
            /*else if(type == 3 || type == 4){
                listNameLeaderOfDepartment = DBUtils.getListNameLeaderOfDepartmentofEmployee(conn,user);
                request.setAttribute("styleDepartment", "display: none");
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("listNameLeaderOfDepartment", listNameLeaderOfDepartment);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/Department.jsp");
        dispatcher.forward(request, response);
    }
}
