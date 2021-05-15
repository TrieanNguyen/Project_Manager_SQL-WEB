package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Project;
import Model.Class.Task;
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
import java.sql.SQLException;

@WebServlet(name = "DeleteTaskServlet", urlPatterns = "/DeleteTask")
public class DeleteTaskServlet extends HttpServlet {
    int idTask = 0;
    int idProject = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);
        String iT = request.getParameter("taskId");
        String iP = request.getParameter("projectId");
        HttpSession session = request.getSession();
        User user = MyUtils.getLoginedUser(session);
        String error = null;
        Boolean hasError = false;
        idTask = Integer.valueOf(iT);
        idProject = Integer.valueOf(iP);
        Task task = null;
        try {
            task = DBUtils.findTask(connection, idTask);
        }catch (SQLException e){
            e.printStackTrace();
        }
        Project project = null;
        try {
            project = DBUtils.findProject(connection, idProject);
        }catch (SQLException e){
            e.printStackTrace();
        }
        Department department = null;
        try {
            department = DBUtils.findDepartment(connection, project.getDepartmentId());
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(user.getType() != 1 && user.getEmployeeId() != task.getEmployeeId() && user.getEmployeeId() != project.getLeader()
        && user.getEmployeeId() != department.getLeader()){
            response.sendRedirect(request.getContextPath() + "/TaskList?idProject="+idProject);
        }
        else {
            try {
                DBUtils.deleteTask(connection, idTask, user.getEmployeeId());
            }catch (Exception e){
                e.printStackTrace();
                hasError = true;
                error = "Deleted error when delete task, please try again!";
            }

            if(hasError){
                request.setAttribute("error", error);
            /*RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/Task.jsp");
            dispatcher.forward(request, response);*/
            }
            /*        else{*/
            response.sendRedirect(request.getContextPath() + "/TaskList?projectId=" + idProject+"&hasDelete=true");
        }

/*        }*/
    }
}
