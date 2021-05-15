package Controller.RestServlet;


import Controller.Servlet.EmployeeListServlet;
import Controller.Servlet.ProjectListServlet;
import Controller.Servlet.TaskListServlet;
import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.TabableView;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "SearchServlet", urlPatterns = "/Search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        /*System.out.println(request.getParameter("searchValue"));
        String dI = request.getParameter("searchValue");*/
        String typeSearch = request.getParameter("type");
        if(typeSearch.equals("project")){
            searchProject(request, response);
        }
        else if(typeSearch.equals("task")){
            searchTask(request, response);
        }
        else if(typeSearch.equals("employee")){
            searchEmployee(request, response);
        }

        /*try{
            List<Employee> leaderIds = DBUtils.getAllIDEmployeeFromDepartment(connection, departmentId);
            int leader = DBUtils.getLeaderFromDepartmentId(connection, departmentId);
            ListUtils.changeLocationEmployee(leaderIds, leader);
            List<Department> departmentList = DBUtils.getAllIDDepartment(connection);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String empJson = null;
            if(departmentId == 0){
                empJson = gson.toJson(departmentList);
            }
            else {
                empJson = gson.toJson(leaderIds);
            }
            System.out.println(empJson);
            PrintWriter out = response.getWriter();
            out.write(empJson);
            out.flush();
        }catch (SQLException e){
            e.printStackTrace();
        }*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException{

    }
    protected void searchTask(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String searchValue = request.getParameter("searchValue");
        try{
            List<Task> result = new ArrayList<>();
            List<Task> taskList = TaskListServlet.taskList;
            String taskName = null;
            searchValue = searchValue.toLowerCase(Locale.ROOT);
            for (int i = 0; i < taskList.size(); i++) {
                taskName = taskList.get(i).getName().toLowerCase(Locale.ROOT);
                if(taskName.contains(searchValue)){
                    result.add(taskList.get(i));
                }
            }
            System.out.println("so luong phan tu "+ result.size());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String empJson = null;
            empJson = gson.toJson(result);
            PrintWriter out = response.getWriter();
            out.write(empJson);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void searchProject(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String searchValue = request.getParameter("searchValue");

        try {
            List<Project> result = new ArrayList<>();
            List<Project> projectList = ProjectListServlet.listProject;
            String projectName = null;
            searchValue = searchValue.toLowerCase(Locale.ROOT);
            for (int i = 0; i < projectList.size(); i++) {
                projectName = projectList.get(i).getName().toLowerCase(Locale.ROOT);
                if(projectName.contains(searchValue)){
                    result.add(projectList.get(i));
                }
            }
            System.out.println("so luong phan tu "+ result.size());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String empJson = null;
            empJson = gson.toJson(result);
            PrintWriter out = response.getWriter();
            out.write(empJson);
            out.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*try{
            List<Employee> leaderIds = DBUtils.getAllIDEmployeeFromDepartment(connection, departmentId);
            int leader = DBUtils.getLeaderFromDepartmentId(connection, departmentId);
            ListUtils.changeLocationEmployee(leaderIds, leader);
            List<Department> departmentList = DBUtils.getAllIDDepartment(connection);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String empJson = null;
            if(departmentId == 0){
                empJson = gson.toJson(departmentList);
            }
            else {
                empJson = gson.toJson(leaderIds);
            }
            System.out.println(empJson);
            PrintWriter out = response.getWriter();
            out.write(empJson);
            out.flush();
        }catch (SQLException e){
            e.printStackTrace();
        }*/
    }

}
