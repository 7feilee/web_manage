package dao;
import model.Log;
import model.Note;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class NoteDao
{
	private Statement stmt;
	private Connection conn;
	
	@Nullable
	private Statement newDao()
	{
		if (stmt != null)
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			stmt = null;
			conn = null;
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
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Note note = new Note();
			if (rs.next())
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
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
	}
	public Collection<Note> getAllNotes()
	{
		Collection<Note> notes = new LinkedList<>();
		String sql = "SELECT * FROM note;";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Note note;
			UserDao userDao = new UserDao();
			PaperDao paperDao = new PaperDao();
			while (rs.next())
			{
				note = new Note();
				note.setId(rs.getInt("id"));
				note.setAuthor(userDao.getUserById(rs.getInt("author")));
				note.setPaper(paperDao.getPaperById(rs.getInt("paper")));
				note.setTitle(rs.getString("title"));
				note.setPublishTime(rs.getDate("publishtime"));
				note.setContent(rs.getString("content"));
				notes.add(note);
			}
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.PaperDao.getAllPapers");
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
		return notes;
	}
	public Collection<Note> getNotesByUser(int uid)
	{
		String sql = "SELECT * FROM note WHERE author=" + uid + ";";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Note note;
			Collection<Note> notes = new LinkedList<>();
			while (rs.next())
			{
				note = new Note();
				note.setId(rs.getInt("id"));
				UserDao userDao = new UserDao();
				note.setAuthor(userDao.getUserById(rs.getInt("author")));
				PaperDao paperDao = new PaperDao();
				note.setPaper(paperDao.getPaperById(rs.getInt("paper")));
				note.setTitle(rs.getString("title"));
				note.setPublishTime(rs.getDate("publishtime"));
				note.setContent(rs.getString("content"));
				notes.add(note);
			}
			return notes;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.NoteDao.getNoteById");
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
	}
	public Collection<Note> getNotesByPaper(int pid)
	{
		String sql = "SELECT * FROM note WHERE paper=" + pid + ";";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Note note;
			Collection<Note> notes = new LinkedList<>();
			while (rs.next())
			{
				note = new Note();
				note.setId(rs.getInt("id"));
				UserDao userDao = new UserDao();
				note.setAuthor(userDao.getUserById(rs.getInt("author")));
				PaperDao paperDao = new PaperDao();
				note.setPaper(paperDao.getPaperById(rs.getInt("paper")));
				note.setTitle(rs.getString("title"));
				note.setPublishTime(rs.getDate("publishtime"));
				note.setContent(rs.getString("content"));
				notes.add(note);
			}
			return notes;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.NoteDao.getNotesByPaper");
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (rs != null)
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
	}
	public int insertNote(int authorID, int paperID, String title, String content, Timestamp publishTime)
	{
		stmt = newDao();
		String sql = "INSERT INTO note(author, paper, title, content, publishtime)" +
				" VALUES ('" + authorID + "','" + paperID + "','"
				+ title + "','" + content + "','" + publishTime + "');";
		try
		{
			int result = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id;
			if (rs.next())
			{
				id = rs.getInt(1);
				LogDao logDao = new LogDao();
				if (logDao.insertLog(Log.ADD, Log.NOTE, id, authorID) > 0)
					return result;
				else
					return -3;//写入日志失败
			}
			rs.close();
			return -2;//其他未知错误
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.NoteDao.insertNote");
			e.printStackTrace();
			return -1;
		}
		finally
		{
			closeDao();
		}
	}
}
