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
import javax.swing.text.StyledEditorKit;
import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InsertTaskServlet", urlPatterns = "/InsertTask")
public class InsertTaskServlet extends HttpServlet {
    int newTaskId = 0;
    int projectId = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String i = request.getParameter("id");
        String name = request.getParameter("name");
        String tS = request.getParameter("timeStart");
        String eT = request.getParameter("estimatedTimeEnd");
        String c = request.getParameter("coefficient");
        String eI = request.getParameter("employeeId");
        String dI = request.getParameter("departmentId");
        String note = request.getParameter("note");
        User user = MyUtils.getLoginedUser(request.getSession());
        Connection connection = MyUtils.getStoredConnection(request);

        Boolean hasError = false;
        String error = null;
        try{
            int id = Integer.valueOf(i);
            Date timeStart = Date.valueOf(tS);
            Date estimatedTimeEnd = Date.valueOf(eT);
            Double coefficient = Double.valueOf(c);
            int employeeId = Integer.valueOf(eI);
            int departmentId = Integer.valueOf(dI);
            int modifiedBy = user.getEmployeeId();
            Task task = new Task(id, projectId, modifiedBy, 0, employeeId, coefficient, name, null, null, false, 0, 0, null, note,
                    timeStart, estimatedTimeEnd, false, departmentId);

            /*Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
    , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
    , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed)*/
            System.out.println("ma nhan vien: "+ task.getEmployeeId());
            DBUtils.insertTask(connection, task);
        }catch (Exception e){
            e.printStackTrace();
            hasError = true;
            error = "Error";
        }
        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("newTaskId", newTaskId);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/InsertTask.jsp");
            dispatcher.forward(request, response);
        }
        else{
            /*request.setAttribute("employee", employee);
            response.sendRedirect(request.getContextPath() + "/InsertUser");*/
            request.setAttribute("newTaskId",newTaskId);
            response.sendRedirect(request.getContextPath() + "/TaskList?projectId="+projectId+"&departmentId="+dI+"&hasInsert=true");
        }

        /*insertTask(@id int, @projectId int, @name nvarchar(50),@timeStart date, @estimatedTimeEnd date, @modifiedBy int, @coefficient real,
 @employeeId int, @departmentId int, @note nvarchar(MAX))*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        projectId = Integer.valueOf(request.getParameter("projectId"));
        int departmentId = Integer.valueOf(request.getParameter("departmentId"));
/*        int departmentId = 0;*/
        List<Employee> employeeList = new ArrayList<>();
        User user = MyUtils.getLoginedUser(request.getSession());
        try{
            departmentId =  DBUtils.findDepartment(connection, departmentId).getId();
            newTaskId = DBUtils.countTask(connection) + 1;
            /*if(user.getType() == 1){
                employeeList = DBUtils.getAllIDAndNameEmployee(connection);
            }else*/ if(user.getType() == 2 || user.getType() == 1){
                employeeList = DBUtils.getAllIDEmployeeFromDepartment(connection, departmentId);
            }else if(user.getType() == 3){
                employeeList = DBUtils.getAllEmployeeInProject(connection, projectId);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("newTaskId",newTaskId);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("projectId", projectId);
        request.setAttribute("departmentId", departmentId);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertTask.jsp");
        dispatcher.forward(request, response);
    }
}
