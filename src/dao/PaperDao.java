package dao;
import model.*;
import java.sql.*;
import java.util.List;

public class PaperDao
{
    private Statement stmt;

    /**构造方法，进行数据库的连接*/
    public PaperDao()
    {
        Connection conn;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage", "root", "zzyadmin");
            stmt = conn.createStatement();
        }
        catch (SQLException e)
        {
            System.err.println("MySQL连接错误@dao.UserDao");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println("MySQL驱动程序错误@dao.UserDao");
            e.printStackTrace();
        }
    }

    public List<Paper> getAllPapers(){
        List<Paper> papers=null;
        String sql="";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Paper paper=new Paper();





                papers.add(paper);
            }


        }
        catch(SQLException e){
            return null;
        }
        return papers;
    }

    public Paper getPaperById(int id){
        return null;
    }
}
