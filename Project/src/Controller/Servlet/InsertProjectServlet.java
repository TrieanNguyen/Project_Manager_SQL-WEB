package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Employee;
import Model.Class.Project;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InsertProjectServlet", urlPatterns = "/InsertProject")
public class InsertProjectServlet extends HttpServlet {
    private static int newProjectID;
    List<Department> departmentList = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String lD = request.getParameter("leader");
        String tS = request.getParameter("timeStart");
        String eTE = request.getParameter("estimatedTimeEnd");
        /*String dI = request.getParameter("departmentId");*/
        String tR = request.getParameter("totalRevenue");
        String note = request.getParameter("Note");

        String error = null;
        Boolean hasError = false;
        Connection connection = MyUtils.getStoredConnection(request);
        Project project = null;
        try{
            int leader = Integer.valueOf(lD);
            Date timeStart = Date.valueOf(tS);
            Date estimatedTimeEnd = Date.valueOf(eTE);
            int departmentId = DBUtils.findUser(connection, leader).getDepartmentId(); /*Integer.valueOf(dI)*/;
            Double totalRevenue = Double.valueOf(tR);
            project = new Project(newProjectID, leader, null, departmentId, 0, 0, totalRevenue,
                    0, name, timeStart, estimatedTimeEnd, null, null, false, 0, 0, note);
/*Project(int id, int leader, String contract, int departmentId, int modifiedBy, int deletedBy, double totalRevenue
    , double progress, String name, Date timeStart, Date estimatedTimeEnd, Date modifiedDate, Date deletedDate, Boolean deleted
    , int attitudeLeader, int levelProjectCompleted)*/
            DBUtils.insertProject(connection, project);
        }catch (Exception e)
        {
            e.printStackTrace();
            hasError = true;
        }
        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("newProjectId", newProjectID);
            request.setAttribute("departmentList", departmentList);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/InsertProject.jsp");
            dispatcher.forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/InsertProject?success=true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        String error = null;
        User user = MyUtils.getLoginedUser(request.getSession());
        int departmentId = 0;
        if(user.getType() != 1 && user.getType() != 2){
            error = "You can not permission insert project!";
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/ProjectList");
        }
        else{
            try{
                newProjectID = DBUtils.countProject(connection) + 1;
                departmentId = DBUtils.getIDDepartmentOfLeader(connection,user.getEmployeeId());
                employeeList = DBUtils.getAllIDEmployeeFromDepartment(connection, departmentId);
                Department department = DBUtils.findDepartment(connection, departmentId);
                if(user.getType() == 2){
                    departmentList.add(department);
                }
                else if(user.getType() == 1){
                    departmentList = DBUtils.getAllIDDepartment(connection);
                }
                ListUtils.changeLocationDepartment(departmentList, 0);
                ListUtils.changeLocationEmployee(employeeList, department.getLeader());

            }
            catch (Exception e){
                e.printStackTrace();
            }
            String success = request.getParameter("success");
            if(success != null){
                request.setAttribute("success", true);
            }
            request.setAttribute("newProjectId",newProjectID);
            request.setAttribute("departmentList", departmentList);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertProject.jsp");
            dispatcher.forward(request, response);
        }

    }
}
