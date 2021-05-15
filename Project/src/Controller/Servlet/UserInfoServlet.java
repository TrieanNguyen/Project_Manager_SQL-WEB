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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserInfoServlet", urlPatterns = "/UserInfo")
public class UserInfoServlet extends HttpServlet {
    Employee employee = new Employee();
    int employeeId = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String error = null;
        boolean hasError = false;
       /* Integer departmentId = Integer.valueOf(d);*/
        /*try {
            department = DBUtils.findDepartment(connection, departmentId);
        }catch (SQLException e){
            e.printStackTrace();
        }*/
        /*if(user.getEmployeeId() != 1 && department.getLeader() != user.getEmployeeId())
        {
            response.sendRedirect(request.getContextPath() + "/DepartmentList");
        }
        else {*/
            try{
                /*Integer attitude = Integer.valueOf(a);
                Double salary = Double.valueOf(s);*/

                /*                User user = MyUtils.getLoginedUser(request.getSession());*/

                /*employee = new Employee(employeeId, departmentId, 0, salary, name, sex, phone, email, address, position
                        , degree, null, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, 0, note,null, user.getEmployeeId(),null);*/

                /*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
                String name = request.getParameter("name");
                employee.setName(name);
                String sex = request.getParameter("sex");
                employee.setSex(sex);
                String bD = request.getParameter("birthDate");
                Date birthDate = Date.valueOf(bD);
                employee.setBirthDate(birthDate);
                String phone = request.getParameter("phone");
                employee.setPhone(phone);
                String email = request.getParameter("email");
                employee.setEmail(email);
                String address = request.getParameter("address");
                employee.setAddress(address);
                String position = request.getParameter("position");
        /*String degree = request.getParameter("degree");
        String d = request.getParameter("departmentId");
        String a = request.getParameter("attitude");
        String s = request.getParameter("salary");*/
                String advantage = request.getParameter("advantage");
                String disadvantage = request.getParameter("disadvantage");
                String foreignLanguage = request.getParameter("foreignLanguage");
                /*String note = request.getParameter("note");*/


                User user = MyUtils.getLoginedUser(request.getSession());
                /*Department department = null;*/
                Connection connection = MyUtils.getStoredConnection(request);
                employee.setModifiedBy(employeeId);
                DBUtils.editEmployee(connection, employee);
            }
            catch (Exception e){
                e.printStackTrace();
                hasError = true;
                error = "Detected error when edit a employee, please try again";
            }

            if(hasError){
                request.setAttribute("error", error);
                request.setAttribute("employee",employee);
               /* RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/Employee.jsp");
                dispatcher.forward(request, response);*/
                response.sendRedirect(request.getContextPath() + "/EmployeeList");
            }
            else{
            /*request.setAttribute("employee", employee);
            response.sendRedirect(request.getContextPath() + "/InsertUser");*/
                /*                request.setAttribute("employee",employee);*/
                response.sendRedirect(request.getContextPath() + "/UserInfo?hasEdit=true&idEmployee="+employeeId);
            }
       /* }*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = request.getSession();
        int employeeId = MyUtils.getLoginedUser(session).getEmployeeId();
        Connection connection = MyUtils.getStoredConnection(request);
        Employee employee = null;
        List<String> sex = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String temp = "";
            sex.add(temp);
        }
        ArrayList<String> attitude = new ArrayList<>();
        try {
            employee = DBUtils.findUser(connection, employeeId);
            if(employee.getSex().equals("nam") || employee.getSex().equals("Nam")){
                sex.set(0, "checked");
            }else
                sex.set(1, "checked");
            for (int i = 0; i < 5; i++) {
                String a = null;
                attitude.add(a);
            }
            attitude.set(employee.getAttitude() - 1, "checked");
        }catch (SQLException e){}
        request.setAttribute("attitude", attitude);
        request.setAttribute("sex", sex);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/Contact.jsp");
        dispatcher.forward(request, response);*/
        String temp = null;

        List<Department> departmentList = new ArrayList<>();
        User user = MyUtils.getLoginedUser(request.getSession());
/*        String eI = request.getParameter("idEmployee");*/
        employeeId = user.getEmployeeId();
        Connection connection = MyUtils.getStoredConnection(request);
        Department department = null;
        try {
            employee = DBUtils.findEmployee(connection, employeeId);
            department = DBUtils.findDepartment(connection, employee.getDepartmentId());
/*            if(user.getType()!=1){*/
            departmentList.add(department);
/*            }
            else{
                departmentList = DBUtils.getAllIDDepartment(connection);
                ListUtils.changeLocationDepartment(departmentList, department.getId());
            }*/
        }catch (SQLException e){
            e.printStackTrace();
        }
        List<String> attitude = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            attitude.add(temp);
        }
        attitude.set(employee.getAttitude() - 1, "checked");
        List<String> position = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            position.add(temp);
        }
        String positionValue = employee.getPosition();
        if(positionValue.equals("Nhân viên"))
            position.set(0, "selected");
        else if(positionValue.equals("Thư ký"))
            position.set(1, "selected");
        else
            position.set(2, "selected");
        List<String> degree = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            degree.add(temp);
        }
        String degreeValue = employee.getDegree();
        if(degreeValue.equals("Đại học"))
            degree.set(0, "selected");
        else if(degreeValue.equals("Thạc sĩ"))
            degree.set(1, "selected");
        else if(degreeValue.equals("Tiến sĩ"))
            degree.set(2, "selected");
        else if(degreeValue.equals("Cấp 3"))
            degree.set(3, "selected");
        else
            degree.set(4, "selected");
        String hE = request.getParameter("hasEdit");
        if(hE != null){
            request.setAttribute("success", true);
            request.setAttribute("info", "Sửa thông tin của bạn thành công");
        }
        if(user.getType() != 1 && user.getType() != 2){
            request.setAttribute("limitHome", true);
        }
        List<String> sex = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            sex.add(temp);
        }
        if(employee.getSex().equals("nam") || employee.getSex().equals("Nam")){
            sex.set(0, "checked");
        }else
            sex.set(1, "checked");
        request.setAttribute("sex", sex);
        request.setAttribute("limitEdit", "disabled");
        request.setAttribute("degree", degree);
        request.setAttribute("position", position);
        request.setAttribute("attitude", attitude);
        request.setAttribute("employee", employee);
        request.setAttribute("departmentList", departmentList);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/EditEmployee.jsp");
        dispatcher.forward(request, response);
    }
}
