package dao;
import model.Note;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.Collection;

public class NoteDao
{
	private Statement stmt;
	private Connection conn;
	
	@Nullable
	private Statement newDao()
	{
		if (stmt!=null)
			return stmt;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://123.207.154.130:3306/papermanage", "root", "coding");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage", "root", "coding");
			stmt = conn.createStatement();
			return stmt;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL连接错误@dao.Dao");
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			System.err.println("MySQL驱动程序错误@dao.Dao");
			e.printStackTrace();
			return null;
		}
	}
	
	private int closeDao()
	{
		try
		{
			if (stmt!=null)
				stmt.close();
			if (conn!=null)
				conn.close();
			stmt=null;
			conn=null;
			return 1;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL连接错误@dao.Dao.closeDao");
			e.printStackTrace();
			return -1;
		}
		catch (Exception e)
		{
			System.err.println("MySQL驱动程序错误@dao.Dao.closeDao");
			e.printStackTrace();
			return -2;
		}
	}
	
	public Note getNoteById(int nid)
	{
		String sql = "SELECT * FROM note WHERE id=" + nid + ";";
		stmt = newDao();
		ResultSet rs=null;
		try
		{
			rs = stmt.executeQuery(sql);
			Note note = new Note();
			if(rs.next())
			{
				note.setId(rs.getInt("id"));
				UserDao userDao = new UserDao();
				note.setAuthor(userDao.getUserById(rs.getInt("author")));
				PaperDao paperDao = new PaperDao();
				note.setPaper(paperDao.getPaperById(rs.getInt("paper")));
				note.setTitle(rs.getString("title"));
				note.setPublishTime(rs.getDate("publishtime"));
				note.setContent(rs.getString("content"));
			}
			return note;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.NoteDao.getNoteById");
			e.printStackTrace();
			return null;
		}
		finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closeDao();
		}
	}
	public Collection<Note> getAllNotes()
	{
		return null;
	}
	public Collection<Note> getNotesByUser(int uid)
	{
		return null;
	}
	public Collection<Note> getNotesByPaper(int pid)
	{
		return null;
	}
	public int insertNote(int paper_id, int user_id)
	{
		return 0;
	}
}
