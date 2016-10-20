package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;
import java.sql.*;

public class Dao {
    private Statement stmt;
    private Connection conn;
    /**
     * 构造方法，进行数据库的连接
     */
    public Dao() {

    }

    public Statement newDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage", "root", "coding");
            stmt = conn.createStatement();
            return stmt;
        } catch (SQLException e) {
            System.err.println("MySQL连接错误@dao.Dao");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("MySQL驱动程序错误@dao.Dao");
            e.printStackTrace();
            return null;
        }
    }

    public int closeDao(){
        try {
            stmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.err.println("MySQL连接错误@dao.Dao.closeDao");
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            System.err.println("MySQL驱动程序错误@dao.Dao.closeDao");
            e.printStackTrace();
            return -2;
        }
    }
}
