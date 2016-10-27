package service;
import dao.*;
import model.*;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
public class Service
{
	private UserDao userDao;
	private PaperDao paperDao;
	private NoteDao noteDao;
	
	public Service()
	{
		super();
		userDao = new UserDao();
		paperDao = new PaperDao();
		noteDao = new NoteDao();
	}
	
	public int login(String username, String password)
	{
		User user = userDao.getUserByUsername(username);
		if (user != null)
			if (password.equals(user.getPassword()))
				return user.getId();
		return 0;
	}

    public Integer addNewUser(String username, String password) {
		User user =userDao.getUserByUsername(username);
		if (user == null){
            userDao.insertNewUser(username,password);
            return 1;
		}
		else
			return 0;
    }

	public Collection<Paper> getPapers(){
		return paperDao.getAllPapers();
	}

	public Paper getPaperById(int id){
		return paperDao.getPaperById(id);
	}

    public User getUserById(int id){
	    return userDao.getUserById(id);
    }

    public int updatePaperState(int user_id, int paper_id ,int state){
        return userDao.updatePaperState(user_id,paper_id,state);
    }

    public Collection<Paper> getPaperByState(int user_id, int state) {
        return userDao.getPaperByState(user_id,state);
    }
    
    public int addPaper(String title, Collection<String> authors, String fileURI, Collection<String> keywords,
                        String abstct, Date publishDate, int operater){
	    // TODO:@ayh
	    return 0;
    }
}
