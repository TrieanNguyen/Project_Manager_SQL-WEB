package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeListServlet", urlPatterns = "/EmployeeList")
public class EmployeeListServlet extends HttpServlet {
    public static Map<Employee, String>  mapEmployeeAndDepartmentName = new HashMap<Employee, String>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());
        int type = user.getType();
        try{
            if(type == 1){
                mapEmployeeAndDepartmentName = DBUtils.getAllEmployeeAndDepartmentName(connection);
            }
            else if(type == 2){
                mapEmployeeAndDepartmentName = DBUtils.getAllEmployeeAndDepartmentNameOfDepartment(connection, user);
            }
/*            else if(type == 3 || type == 4){
                mapEmployeeAndDepartmentName = DBUtils.getAllEmployeeAndDepartmentNameOfEmployee(connection, user);
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String hE = request.getParameter("hasEdit");
        String hD = request.getParameter("hasDelete");
        String hI = request.getParameter("hasInsert");
        if(hE != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Sửa nhân viên thành công");
        }
        if(hD != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Xóa nhân viên thành công");
        }
        if(hI != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Thêm nhân viên thành công");
        }
        request.setAttribute("mapEmployeeAndDepartmentName", mapEmployeeAndDepartmentName);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/Employee.jsp");
        dispatcher.forward(request, response);
    }
}
