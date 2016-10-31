package dao;
import model.*;
import java.sql.*;

public class NoteDao
{
    private Statement stmt;
    Dao dao;
    /**构造方法，进行数据库的连接*/
    public NoteDao()
    {
        Dao dao=new Dao();
        stmt=dao.newDao();
    }

    protected void finalize(){
        dao.closeDao();
    }

    public int insertNewNote(int paper_id,int user_id){
        return 0;
    }
}
