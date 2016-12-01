package dao;
import model.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao
{
	private Statement stmt;
	private Connection conn;

	public Statement newDao()
	{
		if (stmt!=null)
			return stmt;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
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

	public int closeDao()
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
	public User getUserByUsername(String username)
	{
		String sql = "select * from user WHERE username='" + username + "';";
		ResultSet rss=null;
		try
		{
			stmt = newDao();
			rss = stmt.executeQuery(sql);
			if (rss.next())
			{
				stmt = newDao();
				User user = new User();
				user.setUsername(rss.getString("username"));
				user.setPassword(rss.getString("password"));
				user.setId(rss.getInt("id"));
				user.setBio(rss.getString("bio"));
				user.setBlogURL(rss.getString("blogURL"));
				user.setImgURI(rss.getString("imgURI"));
				user.setEmail(rss.getString("email"));
				user.setName(rss.getString("name"));
				return user;
			}
			else
				return null;
		}
		catch (SQLException e)
		{
			
			System.err.println("MySQL查询错误@dao.UserDao.getUserByUsername");
			e.printStackTrace();
			return null;
		}
		finally {
			if (rss!=null)
				try {
					rss.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closeDao();
		}
	}
	
	public User getUserById(int id)
	{
		String sql = "select * from user WHERE id='" + id + "';";
		ResultSet rs=null;
		try
		{
			stmt = newDao();
			rs= stmt.executeQuery(sql);
			if (rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt(1));
				user.setBio(rs.getString("bio"));
				user.setBlogURL(rs.getString("blogURL"));
				user.setImgURI(rs.getString("imgURI"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				return user;
			}
			else
				return null;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.getUserById");
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
	
	public int insertNewUser(String username, String password)
	{
		stmt = newDao();
		String sql = "INSERT INTO user(username, password) VALUES ('" + username + "','" + password + "');";
		ResultSet rs=null;
		try
		{
			int m = stmt.executeUpdate(sql);
			return m;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.insertNewUser");
			e.printStackTrace();
			return -1;
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

    //state=0是未收藏，1是未读，2是粗读，3是精读
	public int updatePaperState(int user_id, int paper_id, int state)
	{
		stmt = newDao();
		ResultSet rs=null;
		try
		{
			String sql0="Select id from user_paper_tree where user_id="+user_id+" and paper_id="+paper_id+" ;";
			rs=stmt.executeQuery(sql0);
			int id=0;
			String sql2="";
			if (rs.next())
			{
				id=rs.getInt(1);
				sql2="UPDATE user_paper_tree SET state="+state+" WHERE id="+id+";";
			}
			else {
				sql2 = "INSERT INTO user_paper_tree (user_id, paper_id, state) VALUES ('"+user_id+ "','" + paper_id + "','" + state + "');";
			}
			return stmt.executeUpdate(sql2);
		}
		catch (SQLException e)
		{
			System.err.println("MySQL错误@dao.UserDao.updatePaperState");
			e.printStackTrace();
			return 0;
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
	
	public Collection<Integer> getPaperidByState(int user_id, int state)
	{
		stmt = newDao();
		String sql2 = "select paper_id from user_paper_tree where user_id="+user_id+" and state=" + state + ";";
		ResultSet rs4=null;
		try
		{
			rs4 = stmt.executeQuery(sql2);
			Collection<Integer> paperids = new LinkedList<>();
			while (rs4.next())
			{
				paperids.add(rs4.getInt(1));
			}
			return paperids;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.getPaperByState");
			e.printStackTrace();
			return null;
		}
		finally {
			if (rs4!=null)
				try {
					rs4.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closeDao();
		}
	}

    public int getPaperState(int user_id, int paper_id){
        String sql = "select state from user_paper_tree where user_id="+user_id+" and paper_id="+paper_id+" ;";
		ResultSet rs=null;
		try
        {
			stmt = newDao();
            rs = stmt.executeQuery(sql);
            int state=0;
            if (rs.next()){
                state=rs.getInt(1);
            }
            return state;
        }
        catch (SQLException e)
        {
            System.err.println("MySQL查询错误@dao.UserDao.getPaperState");
            e.printStackTrace();
            return -1;
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
}
