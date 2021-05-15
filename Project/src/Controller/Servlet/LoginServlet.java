package Controller.Servlet;


import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Utils.DBUtils;
import Controller.Utils.MyUtils;
import Model.Class.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String employeeId = request.getParameter("employeeId");
        String passWord = request.getParameter("passWord");
        String rememberMe  = request.getParameter("rememberMe");
        boolean remember = "on".equals(rememberMe);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            user = DBUtils.findUser(conn, employeeId, getMd5(passWord));
            if (user == null) {
                hasError = true;
                errorString = "Mã nhân viên hoặc mật khẩu không đúng";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            hasError = true;
            errorString = e.getMessage();
        }
        if(hasError){
            request.setAttribute("error", errorString);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            if(remember){
                MyUtils.storeUserCookie(response, user);
            }
            else {
                MyUtils.deleteUserCookie(response);
            }
            response.sendRedirect(request.getContextPath() + "/Home");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/Login.jsp");
        dispatcher.forward(request, response);
    }
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
