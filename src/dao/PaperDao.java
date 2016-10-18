package dao;
import model.*;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class PaperDao
{
    private Statement stmt;
    Dao dao;
    /**构造方法，进行数据库的连接*/
    public PaperDao()
    {
        Dao dao=new Dao();
        stmt=dao.newDao();
    }

    protected void finalize(){
        dao.closeDao();
    }

    public Collection<Paper> getAllPapers(){
        Collection<Paper> papers=new LinkedList<Paper>();
        String sql="select * from paper";
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            Paper paper=new Paper();
            while(rs.next())
            {
                paper.setId(rs.getInt("id"));
                paper.setTitle(rs.getString("title"));
                paper.setPublishDate(rs.getDate("publishDate"));
                Collection<String> author=new LinkedList<>();
                author.add(rs.getString("author1"));
                if (rs.getString("author2")!=null)
                    author.add(rs.getString("author2"));
                else if (rs.getString("author3")!=null)
                    author.add(rs.getString("author3"));
                paper.setAuthors(author);
            }
            papers.add(paper);
        }
        catch(SQLException e){
            System.err.println("MySQL查询错误@dao.PaperDao.getAllPapers");
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
                Collection<String> author=new LinkedList<>();r
                author.add(rs.getString("author1"));
                if (rs.getString("author2")!=null)
                    author.add(rs.getString("author2"));
                else if (rs.getString("author3")!=null)
                    author.add(rs.getString("author3"));
                paper.setAuthors(author);
                paper.setAbsrtct(rs.getString("abstct"));
                paper.setFileURI(rs.getString("fileURL"));
                Collection<String> keyword=new LinkedList<>();
                keyword.add(rs.getString("keyword1"));
                if (rs.getString("keyword2")!=null)
                    keyword.add(rs.getString("keyword2"));
                else if (rs.getString("keyword3")!=null)
                    keyword.add(rs.getString("keyword3"));
                paper.setKeywords(keyword);
            }
            return paper;
        }
        catch(SQLException e){
            System.err.println("MySQL查询错误@dao.PaperDao.getPaperById");
            e.printStackTrace();
            return null;
        }
    }
}
