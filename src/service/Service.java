package service;
import dao.*;
import model.*;

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
		if (user != null)
			if (password.equals(user.getPassword()))
				return 1;
		return 0;
	}
}
