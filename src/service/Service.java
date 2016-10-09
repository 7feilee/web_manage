package service;
import dao.*;
import model.*;

import java.util.Collection;

public class Service
{
	private UserDao userDao;
	private PaperDao paperDao;
	private NoteDao noteDao;
	private User user;
	private Paper paper;
	private Note note;
	private Log log;
	
	public Service()
	{
		super();
		userDao = new UserDao();
		paperDao = new PaperDao();
		noteDao = new NoteDao();
		user = new User();
		paper = new Paper();
		note = new Note();
		log = new Log();
	}

	public int login(String username, String password)
	{
		user = userDao.getUserByUsername(username);
		if(user != null)
			if (password.equals(user.getPassword()))
				return 1;
		return 0;
	}

    public Integer addNewUser(String username, String password, String token) {
		user =userDao.getUserByUsername(username);
		if (user == null)
			return 0;
		else
			userDao.insertNewUser(username,password,token);
		return 1;
    }

	public Collection<Paper> getPapers(){
		return null;
	}

}
