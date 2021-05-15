package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MailUtils;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


@WebServlet(name = "InsertUserServlet", urlPatterns = "/InsertUser")
public class InsertUserServlet extends HttpServlet {
    private static Employee employee;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String i = request.getParameter("id");
        String passWord = request.getParameter("passWord");
        String rePassWord = request.getParameter("rePassWord");
        String error = null;
        if(passWord.equals(rePassWord))
        {
            Integer type = 4;
            User user = null;
            Boolean hasError = false;

            try{
                HttpSession session = request.getSession();
                Connection connection = MyUtils.getStoredConnection(request);
                Integer modifiedBy = MyUtils.getLoginedUser(session).getEmployeeId();
                Integer id = Integer.valueOf(i);
                user = new User(id, MailUtils.getMd5(passWord), modifiedBy, type);
                DBUtils.insertEmployee(connection,employee);
                DBUtils.insertUser(connection, user);
            }catch (SQLException e){
                e.printStackTrace();
                hasError = true;
                error = "error";
            }
            if(hasError)
            {
                request.setAttribute("error", error);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/InsertUser.jsp");
                dispatcher.forward(request, response);
            }
            else {
                request.setAttribute("success", true);
                request.setAttribute("employee",employee);
                /*response.sendRedirect(request.getContextPath() + "/EmployeeList");*/
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/InsertUser.jsp");
                dispatcher.forward(request, response);
            }
        }else {
            error = "Vui lòng nhập mật khẩu trùng khớp";
            request.setAttribute("error", error);
            request.setAttribute("employee",employee);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/InsertUser.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String i = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String bD = request.getParameter("birthDate");
        String tS = request.getParameter("timeStart");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String degree = request.getParameter("degree");
        String d = request.getParameter("departmentId");
        /**/
        String portrait = request.getParameter("portrait");
        String s = request.getParameter("salary");
        String advantage = request.getParameter("advantage");
        String disadvantage = request.getParameter("disadvantage");
        String foreignLanguage = request.getParameter("foreignLanguage");
        String note = request.getParameter("note");

        String error = null;
        boolean hasError = false;
        User user = MyUtils.getLoginedUser(request.getSession());
        Department department = null;
        Connection connection = MyUtils.getStoredConnection(request);
        Integer departmentId = Integer.valueOf(d);
        try {
            department = DBUtils.findDepartment(connection, departmentId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(user.getEmployeeId() != 1 && department.getLeader() != user.getEmployeeId())
        {
            response.sendRedirect(request.getContextPath() + "/DepartmentList");
        }
        else {
            try{
                Integer id = Integer.valueOf(i);

                Date birthDate = Date.valueOf(bD);
                Date timeStart = Date.valueOf(tS);
                Double salary = Double.valueOf(s);

/*                User user = MyUtils.getLoginedUser(request.getSession());*/
                employee = new Employee(id, departmentId, 0, salary, name, sex, phone, email, address, position
                        , degree, portrait, birthDate, false, advantage, disadvantage, foreignLanguage, 1, 0, note, timeStart, user.getEmployeeId(),null);
            /*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
                /*DBUtils.insertEmployee(connection, employee);*/
            }
            catch (Exception e){
                e.printStackTrace();
                hasError = true;
                error = "Detected error when insert a employee, please try again";
            }

            if(hasError){
                request.setAttribute("error", error);
                request.setAttribute("newEmployeeId", i);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/InsertEmployee.jsp");
                dispatcher.forward(request, response);
            }
            else{
            /*request.setAttribute("employee", employee);
            response.sendRedirect(request.getContextPath() + "/InsertUser");*/
                request.setAttribute("employee",employee);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertUser.jsp");
                dispatcher.forward(request, response);
            }
        }

        /*RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/InsertUser.jsp");
        dispatcher.forward(request, response);*/
    }

}
