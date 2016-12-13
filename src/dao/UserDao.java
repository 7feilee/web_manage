package dao;
import model.Log;
import model.Tree;
import model.User;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao
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
	public Tree getPaperNode(int uid, int pid)
	{
		String sql = "select labelname from user_paper_tree where user_id='" + uid + "' and paper_id='" + pid + "';";
		ResultSet rs = null;
		try
		{
			stmt = newDao();
			rs = stmt.executeQuery(sql);
			Tree tree = new Tree();
			if (rs.next())
				tree.setLabelname(rs.getString("labelname"));
			sql = "SELECT id FROM user_tree WHERE labelname='"+tree.getLabelname()+"' AND user_id='"+uid+"';";
			rs.close();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				tree.setId(rs.getInt("id"));
			return tree;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.getPaperState");
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
	public User getUserByUsername(String username)
	{
		String sql = "select * from user WHERE username='" + username + "';";
		ResultSet rss = null;
		try
		{
			stmt = newDao();
			if (stmt != null) {
				rss = stmt.executeQuery(sql);
			}
			if (rss != null) {
				User user =null;
				if (rss.next())
                {
                    stmt = newDao();
                    user=new User();
                    user.setUsername(rss.getString("username"));
                    user.setPassword(rss.getString("password"));
                    user.setId(rss.getInt("id"));
                    user.setBio(rss.getString("bio"));
                    user.setBlogURL(rss.getString("blogURL"));
                    user.setImgURI(rss.getString("imgURI"));
                    user.setEmail(rss.getString("email"));
                    user.setName(rss.getString("name"));
                }
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
		finally
		{
			if (rss != null)
				try
				{
					rss.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
	}
	
	public User getUserById(int id)
	{
		String sql = "select * from user WHERE id='" + id + "';";
		ResultSet rs = null;
		try
		{
			stmt = newDao();
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
			}
			if (rs != null) {
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
			return null;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.getUserById");
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
	
	public int insertNewUser(String username, String password)
	{
		stmt = newDao();
		String sql = "INSERT INTO user(username, password) VALUES ('" + username + "','" + password + "');";
		try
		{
			int result = 0;
			if (stmt != null) {
				result = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ResultSet rs = null;
			if (stmt != null) {
				rs = stmt.getGeneratedKeys();
			}
			int id;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
				LogDao logDao = new LogDao();
				if (logDao.insertLog(Log.ADD, Log.USER, id, id) > 0)
					return result;
				else
					return -3;//写入日志失败
			}
			if (rs != null) {
				rs.close();
			}
			return -2;//其他未知错误
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.insertNewUser");
			e.printStackTrace();
			return -1;
		}
		finally
		{
			closeDao();
		}
	}
	
	//state=0是未收藏，1是未读，2是粗读，3是精读！这个定义不能轻易改变！
	public int updatePaperState(int user_id, int paper_id, int state)
	{
		stmt = newDao();
		ResultSet rs = null;
		try
		{
			String sql0 = "Select id from user_paper_tree where user_id=" + user_id + " and paper_id=" + paper_id + " ;";
			rs = stmt.executeQuery(sql0);
			int id;
			String sql2;
			if (rs.next())
			{
				id = rs.getInt(1);
				sql2 = "UPDATE user_paper_tree SET state=" + state + " WHERE id=" + id + ";";
			}
			else
			{
				sql2 = "INSERT INTO user_paper_tree (user_id, paper_id, state) VALUES ('" + user_id + "','" + paper_id + "','" + state + "');";
			}
			int result = stmt.executeUpdate(sql2, Statement.RETURN_GENERATED_KEYS);
			if (result > 0)
			{
				LogDao logDao = new LogDao();
				if (logDao.insertLog(state + 4, Log.PAPER, paper_id, user_id) > 0)
					return result;
				else
					return -3;//写入日志失败
			}
			return result;//其他未知错误
		}
		catch (SQLException e)
		{
			System.err.println("MySQL错误@dao.UserDao.updatePaperState");
			e.printStackTrace();
			return 0;
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
	
	public Collection<Integer> getPaperidByState(int user_id, int state)
	{
		stmt = newDao();
		String sql2 = "select paper_id from user_paper_tree where user_id=" + user_id + " and state=" + state + ";";
		ResultSet rs4 = null;
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
		finally
		{
			if (rs4 != null)
				try
				{
					rs4.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			closeDao();
		}
	}
	
	public int getPaperState(int user_id, int paper_id)
	{
		String sql = "select state from user_paper_tree where user_id=" + user_id + " and paper_id=" + paper_id + " ;";
		ResultSet rs = null;
		try
		{
			stmt = newDao();
			rs = stmt.executeQuery(sql);
			int state = 0;
			if (rs.next())
			{
				state = rs.getInt(1);
			}
			return state;
		}
		catch (SQLException e)
		{
			System.err.println("MySQL查询错误@dao.UserDao.getPaperState");
			e.printStackTrace();
			return -1;
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
	
	public int addTreeLabel(String labelname, String label_father, int user_id)
	{
		String sql = "insert into user_tree(user_id,labelname,label_father) values('" + user_id + "','" + labelname + "','" + label_father + "');";
		try
		{
			stmt = newDao();
			return stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			closeDao();
		}
	}
	
	public Collection<Tree> getChildTree(int user_id, String label_father)
	{
		//Tree tree=new Tree();
		@Language("MySQL")
		String sql = "select * from user_tree where user_id=" + user_id + " and label_father='" + label_father + "' ;";
		Collection<Tree> trees = new LinkedList<>();
		stmt = newDao();
		ResultSet rs=null;
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				Tree tree1 = new Tree();
				tree1.setLabelname(rs.getString("labelname"));
				tree1.setId(rs.getInt("id"));
				tree1.setLabel_father(rs.getString("label_father"));
				trees.add(tree1);
			}
			return trees;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			closeDao();
		}
	}

	public Tree getTreeById(int id){
		String sql="select * from user_tree where id="+id+";";
		stmt=newDao();
		Tree tree=null;
		ResultSet rs=null;
		try {
			rs=stmt.executeQuery(sql);
			if (rs.next()){
				tree=new Tree();
				tree.setId(rs.getInt("id"));
				tree.setLabelname(rs.getString("labelname"));
				tree.setLabel_father(rs.getString("label_father"));
				return tree;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
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

	public Collection<Integer> getTreePapers(String labelname, int user_id)
	{
		String sql = "select paper_id from user_paper_tree where user_id=" + user_id + " and labelname='" + labelname + "' ;";
		Collection<Integer> papers = new LinkedList<>();
		stmt = newDao();
		ResultSet rs =null;
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				papers.add(rs.getInt(1));
			}
			return papers;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closeDao();

		}
	}

	public int deleteTreeLabel(String labelname, int user_id)
	{
		String sql = "SELECT * FROM user_tree where labelname='" + labelname + "' and user_id=" + user_id + ";";
		stmt = newDao();
		ResultSet rs=null;
		try
		{
			rs = stmt.executeQuery(sql);
			String father = "null";
			int id=0;
			if (rs.next())
			{
				father = rs.getString("label_father");
				id=rs.getInt("id");
			}
			String sql2 = "Update user_tree set label_father='" + father + "' where label_father='" + labelname + "' and user_id=" + user_id + ";";
			stmt.executeUpdate(sql2);
			String sql22 = "Update user_paper_tree set labelname='" + "null" + "' where labelname='" + labelname + "' and user_id=" + user_id + ";";
			stmt.executeUpdate(sql22);
			String sql3 = "delete from user_tree where id=" + id + ";";
			return stmt.executeUpdate(sql3);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			closeDao();
		}
	}
	
	public int updatePaperLabel(String labelname, int user_id, int paper_id)
	{
		String sql = "update user_paper_tree set labelname='" + labelname + "' where user_id=" + user_id + " and paper_id=" + paper_id + ";";
		stmt = newDao();
		try
		{
			int result = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (result > 0)
			{
				LogDao logDao = new LogDao();
				if (logDao.insertLog(Log.UPDATETREELABLE, Log.PAPER, paper_id, user_id) > 0)
					return result;
				else
					return -3;//写入日志失败
			}
			return result;//其他未知错误
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally {
			closeDao();
		}
	}
	
	public int updateTreeLabel(int id, String newlabelname)
	{
		String sql = "update user_tree set labelname='" + newlabelname + "' where id=" + id +";";
		try
		{
			return stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally {
			closeDao();
		}
	}

	public int updateLabelFather(int id, String newlabel_father)
	{
		String sql = "update user_tree set label_father='" + newlabel_father + "' where id=" + id +";";
		try
		{
			return stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally {
			closeDao();
		}
	}
	
}
