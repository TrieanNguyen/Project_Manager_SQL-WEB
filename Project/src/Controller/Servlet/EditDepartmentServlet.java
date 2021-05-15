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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditDepartmentServlet", urlPatterns = "/EditDepartment")
public class EditDepartmentServlet extends HttpServlet {
    private static int idDepartment;
    private static Department department = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("departmentName");
        String roomNumber = request.getParameter("roomNumber");
        String bg = request.getParameter("budget");
        String ld = request.getParameter("leader");
        Connection conn = MyUtils.getStoredConnection(request);
        String error = null;
        Boolean hasError = false;
        try{
            double budget = Double.valueOf(bg);
            int leader = Integer.valueOf(ld);
            department = new Department(idDepartment, leader, 0, budget, name, roomNumber, null, false);
            DBUtils.editDepartment(conn, department);
        }
        /*int id, int leader, int deletedBy, double budget
    , String name, String roomNumber, Time deletedDate, boolean deleted)*/
        catch (Exception e)
        {
            hasError = true;
            error = "Detected error when edit The department, please try again.";
        }
        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("department", department);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/EditDepartment.jsp");
            dispatcher.forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/DepartmentList?hasEdit=true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = new ArrayList<>();
        String id = request.getParameter("idClick");
        Connection connection = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());

       /* else {*/
            try{
                idDepartment = Integer.valueOf(id);
                department = DBUtils.findDepartment(connection, idDepartment);
                if(user.getEmployeeId() != 1 && department.getLeader() != user.getEmployeeId()){
                    response.sendRedirect(request.getContextPath() + "/DepartmentList");
                    return;
                }
                if(user.getType()==1){
                    employeeList = DBUtils.getAllIDEmployeeFromDepartment(connection, idDepartment);
                    ListUtils.changeLocationEmployee(employeeList, department.getLeader());
                }
                else if(user.getType()==2){
                    employeeList = DBUtils.getAllIDEmployeeFromDepartment(connection, idDepartment);
                    ListUtils.changeLocationEmployee(employeeList, department.getLeader());
                    request.setAttribute("disabled", "disabled");
                    request.setAttribute("display", "display: none");
                }
                else {
                    Employee employee = DBUtils.findEmployee(connection, user.getEmployeeId());
                    employeeList.add(employee);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            request.setAttribute("employeeList", employeeList);
            request.setAttribute("department", department);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/EditDepartment.jsp");
            dispatcher.forward(request, response);

        }


    /*}*/
}
