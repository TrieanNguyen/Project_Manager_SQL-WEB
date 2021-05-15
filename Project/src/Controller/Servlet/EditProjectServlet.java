package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Employee;
import Model.Class.Project;
import Model.Class.User;

/*import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;*/

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

@WebServlet(name = "EditProjectServlet", urlPatterns = "/EditProject")
public class EditProjectServlet extends HttpServlet {
    private static Project project;
    private static List<Employee> listIdEmployee;
    private static List<Department> listIdDepartment;
    private static int idProject;
    List<String> attitude = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String l = request.getParameter("leader");
        String tS = request.getParameter("timeStart");
        String eT = request.getParameter("estimatedTimeEnd");
        String dI = request.getParameter("departmentId");
        String tR = request.getParameter("totalRevenue");
        String aL = request.getParameter("attitudeLeader");
        String note = request.getParameter("note");
        Connection connection = MyUtils.getStoredConnection(request);
        boolean hasError = false;
        try {
            int leader = Integer.valueOf(l);
            Date timeStart = Date.valueOf(tS);
            Date estimatedTimeEnd = Date.valueOf(eT);
            int departmentId = Integer.valueOf(dI);
            Double totalRevenue = Double.valueOf(tR);
            int attitudeLeader = Integer.valueOf(aL);
            project.setLeader(leader);
            project.setName(name);
            project.setTimeStart(timeStart);
            project.setEstimatedTimeEnd(estimatedTimeEnd);
            project.setDepartmentId(departmentId);
            project.setTotalRevenue(totalRevenue);
            project.setAttitudeLeader(attitudeLeader);
            project.setNote(note);
            User user = MyUtils.getLoginedUser(request.getSession());
            DBUtils.editProject(connection, project, user);
        } catch (Exception e){
            e.printStackTrace();
            hasError = true;
        }
        response.sendRedirect(request.getContextPath() + "/EditProject?hasError="+hasError+"&projectId="+idProject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iP = request.getParameter("projectId");
        String hE = request.getParameter("hasError");
        Connection connection = MyUtils.getStoredConnection(request);
        try{
            idProject = Integer.valueOf(iP);
            project = DBUtils.findProject(connection, idProject);
            int idDepartment = project.getDepartmentId();
            listIdEmployee = DBUtils.getAllIDEmployeeFromDepartment(connection, idDepartment);
            ListUtils.changeLocationEmployee(listIdEmployee, project.getLeader());
            listIdDepartment  = DBUtils.getAllIDDepartment(connection);
            ListUtils.changeLocationDepartment(listIdDepartment, idDepartment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        User user = MyUtils.getLoginedUser(request.getSession());
        if(user.getType() != 1 && project.getLeader() != user.getEmployeeId()){
                response.sendRedirect(request.getContextPath() + "/ProjectList");
                return;
        }

        for (int i = 0; i < 5; i++) {
            String a = null;
            attitude.add(a);
        }
        attitude.set(project.getAttitudeLeader() - 1, "checked");
        if(hE != null) {
            boolean hasError = Boolean.valueOf(hE);
            if (hasError) {
                request.setAttribute("info", "Đã xảy ra lỗi");
            } else {
                request.setAttribute("info", "Sửa dự án thành công");
            }
            request.setAttribute("hasChange", true);
        }
        else{
            request.setAttribute("hasChange", false);
        }
        if(user.getType() == 3){
            request.setAttribute("limitHome", true);
        }
        request.setAttribute("attitude", attitude);
        request.setAttribute("idEmployee", listIdEmployee);
        request.setAttribute("idDepartment", listIdDepartment);
        request.setAttribute("project", project);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/EditProject.jsp");
        dispatcher.forward(request, response);

    }

}
