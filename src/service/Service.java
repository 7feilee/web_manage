package service;
import dao.*;
import model.*;

import java.util.Collection;
import java.util.List;

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

    public Integer addNewUser(String username, String password) {
		user =userDao.getUserByUsername(username);
		if (user == null){
            userDao.insertNewUser(username,password);
            return 1;
		}
		else
			return 0;
    }

	public Collection<Paper> getPapers(){
		List<Paper> papers=paperDao.getAllPapers();
		return papers;
	}

	public Paper getPaperById(int id){
		Paper paper=paperDao.getPaperById(id);
		return paper;
	}

    public User getUserById(int id){
        user = userDao.getUserById(id);
        if(user != null)
            return user;
        else
            return null;
    }
}
