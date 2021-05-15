package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Task;
import Model.Class.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "TaskListServlet", urlPatterns = "/TaskList")
public class TaskListServlet extends HttpServlet {
    public static List<Task> taskList = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        String idProject = request.getParameter("projectId");
        String idDepartment = request.getParameter("departmentId");
        User user = MyUtils.getLoginedUser(request.getSession());
        String projectName = null;
        int type = user.getType();
        try {
            projectName = DBUtils.findProject(connection, Integer.valueOf(idProject)).getName();
            taskList = DBUtils.getAllTaskByProjectId(connection, Integer.valueOf(idProject), user.getEmployeeId());
        } catch (Exception e){
            e.printStackTrace();
        }

        String hE = request.getParameter("hasEdit");
        String hD = request.getParameter("hasDelete");
        String hI = request.getParameter("hasInsert");
        if(hE != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Sửa công việc thành công");
        }
        if(hD != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Xóa công việc thành công");
        }
        if(hI != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Thêm công việc thành công");
        }
        if(type==4){
            request.setAttribute("styleDeleteButton", "display: none");
        }

        request.setAttribute("type", type);
        request.setAttribute("projectName", projectName);
        request.setAttribute("taskList", taskList);
        request.setAttribute("projectId", idProject);
        request.setAttribute("departmentId", idDepartment);
/*        System.out.println(" ma du an " + idProject + " mapb " + idDepartment);*/
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/Task.jsp");
        dispatcher.forward(request, response);

    }
}
