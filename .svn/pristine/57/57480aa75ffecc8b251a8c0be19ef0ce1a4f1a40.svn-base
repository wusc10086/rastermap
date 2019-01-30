package org.rasterdb.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Connect {
    private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static String username = "system";
    private static String pw = "root";
    private static Connection conn = null;

    public static Connection getConnect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                conn = DriverManager.getConnection(url, username, pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void testConnect() {
        Connection con = getConnect();
        if (con == null)
            System.out.print("连接失败");
        else
            System.out.print("连接成功");
    }
}

 

