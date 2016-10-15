package dao;
import model.*;
import java.sql.*;

public class UserDao
{
	private Statement stmt;
	
	/**构造方法，进行数据库的连接*/
	public UserDao()
	{
		Connection conn;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://123.207.154.130:3306/papermanage", "root", "coding");
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
	public User getUserByUsername(String username)
	{
		String sql = "select * from user WHERE username='" + username + "';";
		try
		{
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
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
	}

	public User getUserById(int id)
	{
		String sql = "select * from user WHERE id='" + id + "';";
		try
		{
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt(1));
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
	}

    public int insertNewUser(String username, String password) {
		String sql = "INSERT INTO user('username', 'password') VALUES ('"+username+"','"+password+"')";
		try {
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
    }
}
