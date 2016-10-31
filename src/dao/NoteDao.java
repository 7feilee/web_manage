package dao;
import java.sql.Statement;

public class NoteDao
{
	private Statement stmt;
	private Dao dao;
	/**
	 * 构造方法，进行数据库的连接
	 */
	public NoteDao()
	{
		Dao dao = new Dao();
		stmt = dao.newDao();
	}
	
	@Override
	protected void finalize()
	{
		dao.closeDao();
	}
	
	public int insertNewNote(int paper_id, int user_id)
	{
		return 0;
	}
}
