package Controller.Servlet;

import Controller.Utils.DBUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.Employee;
import Model.Class.User;
/*import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;*/

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = "/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    int id;
    String style = "";
    String styleForm = "";
    String status;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String i = request.getParameter("id");
        try{
            Connection connection = MyUtils.getStoredConnection(request);
            id = Integer.valueOf(i);
            Employee employee = DBUtils.findUser(connection, id);
            if(employee == null){
                request.setAttribute("status", "Mã nhân viên không đúng hoặc tài khoản của bạn đã bị xóa");
                request.getRequestDispatcher("/views/ForgotPassword.jsp").forward(request, response);
                return;
            }
            String email = employee.getEmail();
            String description = "Khôi phục mật khẩu";
            String newPassword = MailUtils.getSaltString();
            String context = "Mật khẩu mới của bạn là: "+newPassword;
            if(MailUtils.sendMail(email, description,context)==1){
                User user = new User();
                user.setPassWord(MailUtils.getMd5(newPassword));
                user.setEmployeeId(id);
                user.setModifiedBy(id);
                DBUtils.changePassWord(connection, user);
                style ="";
                status = "Đã gửi mail";
                styleForm = "display: none";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("style",style);
        request.setAttribute("styleForm", styleForm);
        request.setAttribute("status", status);
        request.setAttribute("id", id);
        request.getRequestDispatcher("/views/ForgotPassword.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        style = "display: none";
        request.setAttribute("style",style);
        request.setAttribute("styleForm",styleForm);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp");
        dispatcher.forward(request, response);
    }
}