package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.Department;
import Model.Class.SalaryStatistical;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "SalaryServlet", urlPatterns = "/SalaryList")
public class SalaryServlet extends HttpServlet {
    private List<SalaryStatistical> salaryStatistical = null;
    private int year;
    private int month;
    private int quarter;
    private int departmentId;
    User user = null;
    public static List<SalaryStatistical> salaryStatisticals = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        salaryStatisticals = new ArrayList<>();
        request.setCharacterEncoding("UTF-8");
        String group = request.getParameter("group");
        String time = request.getParameter("time");
        String groupValue = request.getParameter("groupValue");
        String timeValue = request.getParameter("timeValue");
        String y = request.getParameter("year");
        Connection connection = MyUtils.getStoredConnection(request);
        year = Integer.valueOf(y);
        Boolean hasError = false;
        try{
            if(group.equals("công ty"))
            {
                if(timeValue.isEmpty()){
                    salaryStatistical = DBUtils.salaryStatisticalCompanyAll(connection, year);
                    System.out.println("ct: year");
                }
                else if(time.equals("tháng")){
                    month = Integer.valueOf(timeValue);
                    salaryStatistical = DBUtils.salaryStatisticalCompany(connection, year, month, true);
                    System.out.println("ct: month");
                }
                else {
                    quarter = Integer.valueOf(timeValue);
                    salaryStatistical = DBUtils.salaryStatisticalCompany(connection, year, quarter, false);
                    System.out.println("ct: quarter");
                }
            }
            else{
                if(groupValue.isEmpty())
                    departmentId = 0;
                else
                    departmentId = Integer.valueOf(groupValue);
                if(timeValue.isEmpty()){
                    salaryStatistical = DBUtils.salaryStatisticalDepartmentAll(connection, departmentId, year);
                    System.out.println("pb: year");
                }
                else if(time.equals("tháng")){
                    month = Integer.valueOf(timeValue);
                    salaryStatistical = DBUtils.salaryStatisticalDepartment(connection, departmentId, month, year, true);
                    System.out.println("pb: month");
                }
                else {
                    quarter = Integer.valueOf(timeValue);
                    salaryStatistical = DBUtils.salaryStatisticalDepartment(connection, departmentId, quarter, year, false);
                    System.out.println("pb: quarter");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            hasError = true;
        }
        String text = null;
        if(group.equals("công ty"))
            if(timeValue.isEmpty())
                text = "lương của toàn công ty năm " + year;
            else if(time.equals("tháng"))
                text = "lương của công ty tháng " + month + " năm " + year;
            else
                text = "lương của công ty quý " + quarter + " năm " + year;
        else
            try {
                Department department = null;
                department = DBUtils.findDepartment(connection, departmentId);
               
				/*
				 * if(timeValue.isEmpty()) text = "lương của phòng ban " + department.getName()
				 * + " năm " + year; else if(time.equals("tháng")) text = "lương của phòng ban "
				 * + department.getName() + " tháng " + month + " năm " + year; else text =
				 * "lương của phòng ban " + department.getName() + " quý " + quarter + " năm " +
				 * year;
				 */
				 
            }catch ( Exception e){
                    e.printStackTrace();
                    hasError = true;
            }

        request.setAttribute("text", text);
        if(!hasError){
            request.setAttribute("salary",salaryStatistical);
            salaryStatisticals = salaryStatistical;
        }else{
            request.setAttribute("error","Mã phòng ban không hợp lệ hoặc phòng ban đã bị xóa");
            salaryStatisticals = new ArrayList<>();
        }
        RequestDispatcher dispatcher =
          		 this.getServletContext().getRequestDispatcher("/views/Salary.jsp");
          		 dispatcher.forward(request, response);
		 
		/* response.sendRedirect(request.getContextPath() + "/SalaryList"); */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
salaryStatisticals = new ArrayList<>();
        request.setAttribute("salary", salaryStatistical);*/
        salaryStatisticals = new ArrayList<>();
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/Salary.jsp");
        dispatcher.forward(request, response);
    }
}