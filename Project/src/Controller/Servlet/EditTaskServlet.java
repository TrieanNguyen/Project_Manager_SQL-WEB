package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
import Controller.Utils.MyUtils;
import Model.Class.*;

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

@WebServlet(name = "EditTaskServlet", urlPatterns = "/EditTask")
public class EditTaskServlet extends HttpServlet {
    int idProject = 0;
    int idTask = 0;
    int idDepartment = 0;
    int employeeId = 0;
    Task task = null;
    int type ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if(name == null){
            name = task.getName();
        }
        String tS = request.getParameter("timeStart");
        if(tS == null){
            tS = task.getTimeStart().toString();
        }
        String eT = request.getParameter("estimatedTimeEnd");
        if(eT == null){
            eT = task.getEstimatedTimeEnd().toString();
        }
        String c = request.getParameter("coefficient");
        if(c == null){
            c = String.valueOf(task.getCoefficient());
        }
        String eI = request.getParameter("employeeId1");
        if(eI == null){
            eI = String.valueOf(task.getEmployeeId());
        }
        /*String dI = request.getParameter("departmentId");*/
        String cp = request.getParameter("completed");
        String linkCheckTask = request.getParameter("linkCheckTask");
        String a = request.getParameter("attitude");
        if(a == null){
            a = String.valueOf(task.getAttitude());
        }
        String note = request.getParameter("note");
        Connection connection = MyUtils.getStoredConnection(request);
        String error = null;
        Boolean hasError  = false;
        try {
            User user = MyUtils.getLoginedUser(request.getSession());
            Double coefficient = Double.valueOf(c);
            Date timeStart = Date.valueOf(tS);
            Date estimatedTimeEnd = Date.valueOf(eT);
            employeeId = Integer.valueOf(eI);
/*            int departmentId = Integer.valueOf(dI);*/
            Boolean completed = Boolean.valueOf(cp);
            int attitude = Integer.valueOf(a);
            Task task = new Task(idTask, idProject, user.getEmployeeId(),0,employeeId, coefficient, name, null, null, false, 0, attitude,
                    linkCheckTask, note, timeStart, estimatedTimeEnd, completed, 0);
            DBUtils.editTask(connection, task);
            /*Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
    , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
    , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed)*/
        }catch (Exception e){
            e.printStackTrace();
            hasError = true;
            error= "Error";
        }
        if(hasError){
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/EditTask?idTask="+idTask+"&idProject=" + idProject);
            /*request.setAttribute("task", task);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/EditProject.jsp");
            dispatcher.forward(request, response);*/
        }
        else{
            response.sendRedirect(request.getContextPath() + "/TaskList?projectId="+idProject+"&departmentId="+idDepartment+"&hasEdit=true");
        }
        /*editTask(@id int, @name nvarchar(50), @timeStart date, @estimatedTimeEnd date, @modifiedBy int, @coefficient real, @employeeId int, @completed bit,
@departmentId int, @linkCheckTask nvarchar(MAX), @attitude int, @note nvarchar(max))*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iP = request.getParameter("projectId");
        String iT = request.getParameter("taskId");
        String eI = request.getParameter("employeeId");
        idTask = Integer.valueOf(iT);
        idProject = Integer.valueOf(iP);
        employeeId = Integer.valueOf(eI);
        Connection connection = MyUtils.getStoredConnection(request);
        Project project = null;
        Department department = null;
        List<Employee> employeeList = null;
        List<Department> departmentList = null;
        try{
            task  = DBUtils.findTask(connection, idTask);
            project  = DBUtils.findProject(connection, idProject);
            department  = DBUtils.findDepartment(connection, project.getDepartmentId());
            idDepartment = department.getId();
            employeeList = DBUtils.getAllIDEmployeeFromDepartment(connection, idDepartment);
            ListUtils.changeLocationEmployee(employeeList, employeeId);
            departmentList = DBUtils.getAllIDDepartment(connection);
            ListUtils.changeLocationDepartment(departmentList, department.getLeader());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        User user = MyUtils.getLoginedUser(request.getSession());
        type = user.getType();
        if(user.getType() != 1 && task.getEmployeeId() != user.getEmployeeId() && project.getLeader() != user.getEmployeeId()
        && user.getEmployeeId() != department.getLeader())
        {
            response.sendRedirect(request.getContextPath() + "/TaskList?projectId="+idProject);
        }
        else {
            if(type==4){
                request.setAttribute("disabled","disabled");
                request.setAttribute("style","display: none");
                request.setAttribute("limitHome", true);
            }else if(type==3){
                request.setAttribute("limitHome", true);

            }
            List<String> attitude = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                String a = null;
                attitude.add(a);
            }
            List<String> completed = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                String a = null;
                completed.add(a);
            }
            if(task.getCompleted()){
                completed.set(0, "checked");
            }else
                completed.set(1, "checked");
            attitude.set(task.getAttitude() - 1, "checked");
            request.setAttribute("completed", completed);
            request.setAttribute("bonus", 100);
            request.setAttribute("departmentId", idDepartment);
            request.setAttribute("projectId", iP);
            request.setAttribute("attitude", attitude);
            request.setAttribute("task", task);
            request.setAttribute("employeeList", employeeList);
            request.setAttribute("departmentList", departmentList);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/EditTask.jsp");
            dispatcher.forward(request, response);
        }
    }

}
