package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
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
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InsertDepartmentServlet", urlPatterns = "/InsertDepartment")
public class InsertDepartmentServlet extends HttpServlet {
    public static int newDepartmentID;
    private List<Employee> employeeList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String departmentName = request.getParameter("departmentName");
        String roomNumber = request.getParameter("roomNumber");
        String bg = request.getParameter("budget");
        String ld = request.getParameter("leader");

        String error = null;
        boolean hasError = false;
        Department department = null;

        /*(int id, int leader, int deletedBy, double budget
    , String name, String roomNumber, Time deletedDate, boolean deleted)*/
        try{
            Connection conn = MyUtils.getStoredConnection(request);
            int leader = Integer.valueOf(ld);
            double budget = Double.valueOf(bg);
            department = new Department(newDepartmentID, leader, 0, budget, departmentName, roomNumber, null, false);
            DBUtils.insertDepartment(conn, department);
        }
        catch (Exception e){
            hasError = true;
            error = "Detected error when insert a department, please try again";
        }
        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("newDepartmentId", newDepartmentID);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/InsertDepartment.jsp");
            dispatcher.forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/DepartmentList?hasInsert=true");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());
        if(user.getEmployeeId() != 1)
        {
            response.sendRedirect(request.getContextPath() + "/DepartmentList");
        }
        else {
            try{
                newDepartmentID = DBUtils.countDepartment(conn) + 1;
                employeeList = DBUtils.getAllIDAndNameEmployee(conn);
                ListUtils.changeLocationEmployee(employeeList, 0);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            request.setAttribute("newDepartmentId",newDepartmentID);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertDepartment.jsp");
            dispatcher.forward(request, response);
        }
    }
}
