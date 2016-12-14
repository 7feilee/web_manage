package dao;

import model.Log;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
public class LogDao
{
	private Statement stmt;
	private Connection conn;
	
	private Statement newDao()
	{
		if (stmt != null)
			return stmt;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papermanage?useSSL=false", "root", "coding");
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
	public Collection<Log> getAllLogs()
	{
		Collection<Log> logs = new LinkedList<>();
		String sql = "SELECT * FROM log;";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Log log;
			while (rs.next())
			{
				log = new Log();
				log.setId(rs.getInt("id"));
				log.setOperatorid(rs.getInt("operatorid"));
				log.setTarget(rs.getInt("target"));
				log.setTargetid(rs.getInt("targetid"));
				log.setTime(rs.getTimestamp("time"));
				log.setType(rs.getInt("type"));
				logs.add(log);
			}
			return logs;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.LogDao.getAllLogs");
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
	public Collection<Log> getLogsByUser(int uid)
	{
		Collection<Log> logs = new LinkedList<>();
		String sql = "select * from log WHERE operatorid='" + uid + "';";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Log log;
			while (rs.next())
			{
				log = new Log();
				log.setId(rs.getInt("id"));
				log.setOperatorid(rs.getInt("operatorid"));
				log.setTarget(rs.getInt("target"));
				log.setTargetid(rs.getInt("targetid"));
				log.setTime(rs.getTimestamp("time"));
				log.setType(rs.getInt("type"));
				logs.add(log);
			}
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.LogDao.getLogsByUser");
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
		return logs;
	}
	public Collection<Log> getLogsByPaper(int pid)
	{
		Collection<Log> logs = new LinkedList<>();
		String sql = "select * from log WHERE targetid='" + pid + "' AND target='"+Log.PAPER+"';";
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			Log log;
			while (rs.next())
			{
				log = new Log();
				log.setId(rs.getInt("id"));
				log.setOperatorid(rs.getInt("operatorid"));
				log.setTarget(rs.getInt("target"));
				log.setTargetid(rs.getInt("targetid"));
				log.setTime(rs.getTimestamp("time"));
				log.setType(rs.getInt("type"));
				logs.add(log);
			}
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.LogDao.getLogsByUser");
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
		return logs;
	}
	public int insertLog(int type, int target, int targetid, int operatorid)
	{
		stmt = newDao();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO log(time, type, target, targetid, operatorid) " +
				"VALUES ('" + time + "','" + type + "','" + target + "','" + targetid + "','" + operatorid + "');";
		try
		{
			return stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.LogDao.insertLog");
			e.printStackTrace();
			return -1;
		}
		finally
		{
			closeDao();
		}
	}
}
