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
		User user=userDao.getUserById(id);
		user.setToReadPapers(getPaperByState(user.getId(),0));
		user.setReadPapers(getPaperByState(user.getId(),2));
		user.setStudiedPapers(getPaperByState(user.getId(),1));
	    return user;
    }

    public int updatePaperState(int user_id, int paper_id ,int state){
        return userDao.updatePaperState(user_id,paper_id,state);
    }

    public Collection<Paper> getPaperByState(int user_id, int state) {
		Collection<Paper> papers=new LinkedList<>();
		Collection<Integer> paperids=userDao.getPaperidByState(user_id,state);
		for (Iterator iter = paperids.iterator(); iter.hasNext();) {
			int i=(int)iter.next();
			papers.add(paperDao.getPaperById(i));
		}
        return papers;
    }
    
    public int addPaper(String title, Collection<String> authors, String fileURI, Collection<String> keywords,
                        String abstct, Date publishDate, int operater){
	    // TODO:@ayh
	    return 0;
    }
}
