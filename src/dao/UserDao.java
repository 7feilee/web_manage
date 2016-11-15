package dao;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao
{
	private Statement stmt;
	private Dao dao;
	/**
	 * 构造方法，进行数据库的连接
	 */
	public UserDao()
	{

	}
	
	protected void finalize()
	{

	}
	
	public User getUserByUsername(String username)
	{
		String sql = "select * from user WHERE username='" + username + "';";
		ResultSet rss=null;
		try
		{
			stmt = dao.newDao();
			rss = stmt.executeQuery(sql);
			if (rss.next())
			{
				dao = new Dao();
				stmt = dao.newDao();
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
			dao.closeDao();
		}
	}
	
	public User getUserById(int id)
	{
		String sql = "select * from user WHERE id='" + id + "';";
		try
		{
			dao = new Dao();
			stmt = dao.newDao();
			ResultSet rs = stmt.executeQuery(sql);
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
			dao.closeDao();
		}
	}
	
	public int insertNewUser(String username, String password)
	{
		dao = new Dao();
		stmt = dao.newDao();
		String sql = "INSERT INTO user(username, password) VALUES ('" + username + "','" + password + "');";
		try
		{
			int m = stmt.executeUpdate(sql);
			if (m != 0)
			{
				sql = "select id from user where username='" + username + "';";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next())
				{
					int id = rs.getInt(1);
                    String sql1 = "CREATE TABLE user_" + id + " (paper_id int(10) NOT NULL,state int(10) NOT NULL DEFAULT '0',treehead int(10),UNIQUE KEY `user_1_paper_id_uindex` (`paper_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                    stmt.executeUpdate(sql1);
				}
			}
			return m;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.insertNewUser");
			e.printStackTrace();
			return -1;
		}
		finally {
			dao.closeDao();
		}
	}

    //state=0是未收藏，1是未读，2是粗读，3是精读
	public int updatePaperState(int user_id, int paper_id, int state)
	{
		dao = new Dao();
		stmt = dao.newDao();
		String sql0 = "SELECT table_name FROM information_schema.TABLES WHERE table_name ='user_" + user_id + "';";
		try
		{
			ResultSet rs = stmt.executeQuery(sql0);
			if (!rs.next())
			{
				String sql1 = "CREATE TABLE user_" + user_id + " (paper_id int(10) NOT NULL,state int(10) NOT NULL DEFAULT '0',treehead int(10),UNIQUE KEY `user_1_paper_id_uindex` (`paper_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
 				stmt.executeUpdate(sql1);
			}
			ResultSet rs2=stmt.executeQuery("SELECT * FROM user_" + user_id + " WHERE paper_id="+paper_id+";");
			String sql2;
			if (rs2.next()){
				sql2="UPDATE user_" + user_id + "SET state="+state+"WHERE paper_id="+paper_id+";";
			}
			else {
				sql2 = "INSERT INTO user_" + user_id + "(paper_id, state) VALUES ('" + paper_id + "','" + state + "');";
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
			dao.closeDao();
		}
	}
	
	public Collection<Integer> getPaperidByState(int user_id, int state)
	{
		dao = new Dao();
		stmt = dao.newDao();
		String sql2 = "select paper_id from user_" + user_id + " WHERE state=" + state + ";";
		try
		{
			ResultSet rs4 = stmt.executeQuery(sql2);
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
			dao.closeDao();
		}
	}

    public int getPaperState(int user_id, int paper_id){
        String sql = "select state from user_" + user_id + " WHERE paper_id=" + paper_id + ";";
        try
        {
			dao = new Dao();
			stmt = dao.newDao();
            ResultSet rs = stmt.executeQuery(sql);
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
			dao.closeDao();
		}
	}
}
