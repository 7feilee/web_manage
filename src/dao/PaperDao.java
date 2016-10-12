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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage", "root", "19951224");
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
        String sql="select * from paper";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            Paper paper=new Paper();
            while(rs.next()){
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setPublishDate(rs.getDate("publishDate"));
                List<String> author=null;
                author.add(rs.getString("author1"));
                if (rs.getString("author2")!="")
                    author.add(rs.getString("author2"));
                else if (rs.getString("author3")!="")
                    author.add(rs.getString("author3"));
                paper.setAuthors(author);
            }
            papers.add(paper);

        }
        catch(SQLException e){
            System.err.println("MySQL查询错误@dao.UserDao.getUserById");
            e.printStackTrace();
            return null;
        }
        return papers;
    }

    public Paper getPaperById(int id){
        String sql="select * from paper where id='"+id+"'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            Paper paper=new Paper();
            if(rs.next()){
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setPublishDate(rs.getDate("publishDate"));
                List<String> author=null;
                author.add(rs.getString("author1"));
                if (rs.getString("author2")!="")
                    author.add(rs.getString("author2"));
                else if (rs.getString("author3")!="")
                    author.add(rs.getString("author3"));
                paper.setAuthors(author);
                paper.setAbstct(rs.getString("abstct"));
                paper.setFileURI(rs.getString("fileURL"));
                List<String> keyword=null;
                author.add(rs.getString("keyword1"));
                if (rs.getString("keyword2")!="")
                    author.add(rs.getString("keyword2"));
                else if (rs.getString("keyword3")!="")
                    author.add(rs.getString("keyword3"));
                paper.setKeywords(keyword);
            }
            return paper;
        }
        catch(SQLException e){
            System.err.println("MySQL查询错误@dao.UserDao.getUserById");
            e.printStackTrace();
            return null;
        }
    }
}
