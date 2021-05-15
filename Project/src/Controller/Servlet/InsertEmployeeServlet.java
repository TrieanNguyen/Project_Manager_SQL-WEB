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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InsertEmployeeServlet", urlPatterns = "/InsertEmployee")
public class InsertEmployeeServlet extends HttpServlet {
    private static int newEmployeeID ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());
        List<Department> departmentList = new ArrayList<>();
        if(user.getType() != 1 && user.getType()!= 2){
            response.sendRedirect(request.getContextPath() + "/EmployeeList");
            return;
        }
            try{
                if(user.getType() == 1){
                    departmentList = DBUtils.getAllIDDepartment(conn);
                }
                else{
                    Employee employee = DBUtils.findEmployee(conn, user.getEmployeeId());
                    departmentList.add(DBUtils.findDepartment(conn, employee.getDepartmentId()));
                }
                newEmployeeID = DBUtils.countEmployee(conn) + 1;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            request.setAttribute("newEmployeeId",newEmployeeID);
            request.setAttribute("departmentList", departmentList);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertEmployee.jsp");
            dispatcher.forward(request, response);
    }
}
