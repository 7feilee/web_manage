package dao;
import model.*;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao
{
	private Statement stmt;
	Dao dao;
	/**构造方法，进行数据库的连接*/
	public UserDao()
	{
		Dao dao=new Dao();
		stmt=dao.newDao();
	}

	protected void finalize(){
		dao.closeDao();
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
				user.setBio(rs.getString("bio"));
				user.setBlogURL(rs.getString("blogURL"));
				user.setImgURI(rs.getString("imgURI"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				Collection<Paper> toReadPapers=getPaperByState(user.getId(),0);
				Collection<Paper> readPapers=getPaperByState(user.getId(),2);
				Collection<Paper> studiedPapers=getPaperByState(user.getId(),1);
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
				user.setBio(rs.getString("bio"));
				user.setBlogURL(rs.getString("blogURL"));
				user.setImgURI(rs.getString("imgURI"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				Collection<Paper> toReadPapers=getPaperByState(user.getId(),0);
				Collection<Paper> readPapers=getPaperByState(user.getId(),2);
				Collection<Paper> studiedPapers=getPaperByState(user.getId(),1);
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
		String sql = "INSERT INTO user(username, password) VALUES ('"+username+"','"+password+"')";
		try {
			int m=stmt.executeUpdate(sql);
			if (m!=0){
				sql="select id from user where username='"+username+"';";
				ResultSet rs=stmt.executeQuery(sql);
				if (rs.next()){
					int id=rs.getInt(1);
					String sql1="CREATE TABLE user_"+id+" (paper_id int(10),state int(10),note VARCHAR(2000));";
					stmt.executeUpdate(sql1);
				}
			}
			return m;
		} catch (SQLException e) {
			System.err.println("MySQL查询错误@dao.UserDao.insertNewUser");
			e.printStackTrace();
			return 0;
		}
    }

    public int updatePaperState(int user_id, int paper_id, int state){
		//state=1 精度 2 粗读 0 未读
		String sql0="SELECT table_name FROM information_schema.TABLES WHERE table_name ='user_"+user_id+"';";
		try {
			ResultSet rs=stmt.executeQuery(sql0);
			if (rs.next()) {
			}
			else{
				String sql1="CREATE TABLE user_"+user_id+" (paper_id int(10),state int(10),treehead int(10));";
				stmt.executeUpdate(sql1);
			}
			String sql2="INSERT INTO user_"+user_id+"(paper_id, state) VALUES ('"+paper_id+"','"+state+"')";
			return stmt.executeUpdate(sql2);
		} catch (SQLException e) {
			System.err.println("MySQL错误@dao.UserDao.updatePaperState");
			e.printStackTrace();
			return 0;
		}
	}

	public Collection<Paper> getPaperByState(int user_id, int state) {
		String sql = "select paper_id from user_" + user_id + " WHERE state=" + state + ";";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			Collection<Paper> papers = new LinkedList<Paper>();
			while (rs.next()) {
				int paperid = rs.getInt(1);
				sql = "select * from paper where id='" + paperid + "'";
				ResultSet rs2 = stmt.executeQuery(sql);
				if (rs2.next()) {
					papers.add(getPaperById(rs2.getInt(1)));
				}
			}
			return papers;
		} catch (SQLException e) {
			System.err.println("MySQL查询错误@dao.UserDao.getPaperByState");
			e.printStackTrace();
			return null;
		}
	}

	public Paper getPaperById(int id){
		String sql="select * from paper where id='"+id+"'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			Paper paper=new Paper();
			if(rs.next()){
				paper.setId(rs.getInt("id"));
				paper.setTitle(rs.getString(1));
				paper.setPublishDate(rs.getDate("publishDate"));
				Collection<String> author=new LinkedList<String>();
				author.add(rs.getString("author1"));
				if (rs.getString("author2")!=null)
					author.add(rs.getString("author2"));
				else if (rs.getString("author3")!=null)
					author.add(rs.getString("author3"));
				paper.setAuthors(author);
				paper.setAbsrtct(rs.getString("abstct"));
				paper.setFileURI(rs.getString("fileURL"));
				Collection<String> keyword=new LinkedList<String>();
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
