package Controller.RestServlet;

import Controller.Utils.DBUtils;
import Controller.Utils.ListUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Employee;
import Model.Class.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DepartmentSelectionServlet", urlPatterns = "/DepartmentSelection")
public class DepartmentSelectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println(request.getParameter("departmentId"));
        String dI = request.getParameter("departmentId");
        int departmentId = Integer.valueOf(dI);
        Connection connection = MyUtils.getStoredConnection(request);
        try{
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
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
