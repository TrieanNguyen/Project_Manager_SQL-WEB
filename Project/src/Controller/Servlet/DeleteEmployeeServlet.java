package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.Employee;
import Model.Class.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "DeleteEmployeeServlet", urlPatterns = "/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    int employeeId = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ed = request.getParameter("idEmployee");
        employeeId = Integer.valueOf(ed);
        Connection conn = MyUtils.getStoredConnection(request);
        String error = null;
        Boolean hasError = false;
        Department department = null;
        Employee employee = new Employee();
        try {
            employee = DBUtils.findEmployee(conn, employeeId);
            department = DBUtils.findDepartment(conn, employee.getDepartmentId());
        } catch (Exception e){
            e.printStackTrace();
        }
        User user = MyUtils.getLoginedUser(request.getSession());
        if(user.getEmployeeId() != 1 && department.getLeader() != user.getEmployeeId()){
            response.sendRedirect(request.getContextPath() + "/EmployeeList");
        }
        else {
            try{
                DBUtils.deleteEmployee(conn, employeeId, user.getEmployeeId());
            }
            catch (Exception e){
                hasError = true;
                error = "Detected error when delete the employee, please try again.";
            }
            if(hasError){
                request.setAttribute("error", error);
            }
            response.sendRedirect(request.getContextPath() + "/EmployeeList?hasDelete=true");
            String description = "Th??ng b??o v??? t??i kho???n";
            String content = "?????u ti??n, c??ng ty DHTP xin c???m ??n " + employee.getName()+
                    " ???? c???ng hi???n cho c??ng ty trong su???t th???i gian l??m vi???c" +
                    ". C??ng ty xin th??ng b??o, hi???n t???i t??i kho???n c???a b???n ???? kh??ng c??n s??? d???ng ???????c tr??n website c???a c??ng ty" +
                    ". Xin tr??n tr???ng th??ng b??o";
            MailUtils.sendMail(employee.getEmail(),description, content);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
