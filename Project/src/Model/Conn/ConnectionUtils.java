package Model.Conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection getConnection(int type)
        throws ClassNotFoundException, SQLException{
        return ConnectDB.getConnect(type);

    }
    public static void closeQuietly(Connection conn){
        try {
            conn.close();
        }catch (Exception e) {

        }
    }
    public static void rollbackQuietly(Connection conn){
        try {
            conn.rollback();
        }catch (Exception e){

        }
    }
}
