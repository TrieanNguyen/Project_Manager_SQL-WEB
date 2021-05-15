package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.Employee;
import Model.Class.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ChangePassWordServlet", urlPatterns = "/ChangePassWord")
public class ChangePassWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");
        Connection connection = MyUtils.getStoredConnection(request);
        User user = MyUtils.getLoginedUser(request.getSession());
        User user1 = null;
        String error = null;
        Boolean hasError = false;
        try{
            user1 = DBUtils.findUser(connection, String.valueOf(user.getEmployeeId()), MailUtils.getMd5(password));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if(user1 == null){
            hasError = true;
            error = "Password is invalid";
        }
        else if(!newPassword.equals(reNewPassword)){
            hasError = true;
            error = "New password and retype new password is not same";
        }
        if(hasError){
            request.setAttribute("error", error);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/views/ChangePassWord.jsp");
            dispatcher.forward(request, response);
        }else {
            user1.setPassWord(MailUtils.getMd5(newPassword));
            user1.setModifiedBy(user.getEmployeeId());

            try {
                DBUtils.changePassWord(connection, user1);
                Employee employee = DBUtils.findEmployee(connection, user1.getEmployeeId());
                MailUtils.sendMail(employee.getEmail(), "Cập nhật mật khẩu", "Mật khẩu của bạn vừa được thay đổi");
            } catch (SQLException e){
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = MyUtils.getLoginedUser(request.getSession());
        int type = user.getType();
        if( type == 3 || type == 4)
            request.setAttribute("limitHome", true);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/ChangePassWord.jsp");
        dispatcher.forward(request, response);
    }

}
