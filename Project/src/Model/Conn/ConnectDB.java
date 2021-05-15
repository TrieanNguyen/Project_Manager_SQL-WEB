package Model.Conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static Connection getConnect(){
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=DB_TaskManager";
       /* String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=simpleWeb";*/
        String user = "adminLogin";
        String passWord = "123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, passWord);
        }catch (Exception er){
            er.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnect( int type){
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=DB_TaskManager";
        String user = null;
        String passWord = "123";
        if(type == 0){
            user = "sa";
        }
        else if(type == 1){
            user = "adminLogin";
        }
        else if(type == 2){
            user = "departmentLogin";
        }
        else if(type == 3){
            user = "projectLogin";
        }
        else if(type == 4){
            user = "employeeLogin";
        }

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, passWord);
        }catch (Exception er){
            er.printStackTrace();
        }
        return conn;
    }
}
