package DAO;

import java.sql.*;

/**
 *
 * 数据库连接类
 * @author Oxygen
 */
public class DatabaseConnection {
    private final String DRIVE = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://127.0.0.1:3306/XXXX";
    private final String USER = "XXXXX";
    private final String PASSWORD = "XXXXXX";

    private Connection conn = null;

    //实例化数据库连接
    public DatabaseConnection() {
        try {
            Class.forName(DRIVE);
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //取得数据库连接
    public Connection getConnection() {
        return this.conn;
    }

    //关闭数据库连接
    public void close() {
        if(this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

