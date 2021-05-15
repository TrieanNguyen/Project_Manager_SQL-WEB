package Controller.Utils;

import Model.Class.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    public static void storeLoginedUser(HttpSession session, User loginedUser) {

        session.setAttribute("loginedUser", loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }
    public static void  deleteLoginUser(HttpSession session){
        session.removeAttribute("loginedUser");
    }

    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, Integer.toString(user.getEmployeeId()));

        cookieUserName.setMaxAge(10);
        response.addCookie(cookieUserName);
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUser = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUser.setMaxAge(0);
        response.addCookie(cookieUser);
    }

}
