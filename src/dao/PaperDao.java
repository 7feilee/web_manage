package dao;
import model.Paper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class PaperDao
{
	private Statement stmt;
	private Dao dao;
	/**
	 * 构造方法，进行数据库的连接
	 */
	public PaperDao()
	{
		Dao dao = new Dao();
		stmt = dao.newDao();
	}
	
	@Override
	protected void finalize()
	{
		dao.closeDao();
	}
	
	public Collection<Paper> getAllPapers()
	{
		Collection<Paper> papers = new LinkedList<>();
		String sql = "select * from paper";
		try
		{
			ResultSet rs = stmt.executeQuery(sql);
			Paper paper;
			while (rs.next())
			{
				paper = new Paper();
				paper.setId(rs.getInt("id"));
				paper.setTitle(rs.getString("title"));
				paper.setPublishDate(rs.getDate("publishDate"));
				Collection<String> author = new LinkedList<>();
				author.add(rs.getString("author1"));
				if (rs.getString("author2") != null)
					author.add(rs.getString("author2"));
				else if (rs.getString("author3") != null)
					author.add(rs.getString("author3"));
				paper.setAuthors(author);
				papers.add(paper);
			}
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.PaperDao.getAllPapers");
			e.printStackTrace();
			return null;
		}
		return papers;
	}
	
	public Paper getPaperById(int id)
	{
		String sql = "select * from paper where id='" + id + "';";
		try
		{
			ResultSet rs = stmt.executeQuery(sql);
			Paper paper = new Paper();
			if (rs.next())
			{
				paper.setId(rs.getInt("id"));
				paper.setTitle(rs.getString("title"));
				paper.setPublishDate(rs.getDate("publishDate"));
				Collection<String> author = new LinkedList<>();
				author.add(rs.getString("author1"));
				if (rs.getString("author2") != null)
					author.add(rs.getString("author2"));
				else if (rs.getString("author3") != null)
					author.add(rs.getString("author3"));
				paper.setAuthors(author);
				paper.setAbstct(rs.getString("abstct"));
				paper.setFileURI(rs.getString("fileURI"));
				Collection<String> keyword = new LinkedList<>();
				keyword.add(rs.getString("keyword1"));
				if (rs.getString("keyword2") != null)
					keyword.add(rs.getString("keyword2"));
				else if (rs.getString("keyword3") != null)
					keyword.add(rs.getString("keyword3"));
				paper.setKeywords(keyword);
			}
			return paper;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.PaperDao.getPaperById");
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertNewPaper(String title, String fileURI, Date publishDate,
	                          Collection<String> authors, String abstct,
	                          Collection<String> keywords)
	{
		int i = 0;
		String author1 = "", author2 = "", author3 = "", keyword1 = "", keyword2 = "", keyword3 = "";
		for (Iterator<String> it = authors.iterator(); it.hasNext(); )
		{
			i++;
			if (i == 1)
				author1 = (String) it.next();
			else if (i == 2)
				author2 = (String) it.next();
			else if (i == 3)
				author3 = (String) it.next();
		}
		i = 0;
		for (Iterator<String> its = keywords.iterator(); its.hasNext(); )
		{
			i++;
			if (i == 1)
				keyword1 = (String) its.next();
			else if (i == 2)
				keyword2 = (String) its.next();
			else if (i == 3)
				keyword3 = (String) its.next();
		}
		String sql = "insert into paper(title,fileURI,publishDate,author1,author2,author3," +
				"abstct,keyword1,keyword2,keyword3) values" +
				"('" + title + "','" + fileURI + "','" + publishDate + "','" + author1 + "','" + author2 + "','" + author3 + "','" +
				abstct + "','" + keyword1 + "','" + keyword2 + "','" + keyword3 + "');";
		try
		{
			int m = stmt.executeUpdate(sql);
			if (m > 0)
				return m;
			else
				return 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
