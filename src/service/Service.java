package service;
import dao.*;
import model.*;
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
		Collection<Paper> papers=paperDao.getAllPapers();
		return papers;
	}

	public Paper getPaperById(int id){
		Paper paper=paperDao.getPaperById(id);
		return paper;
	}

    public User getUserById(int id){
	    return userDao.getUserById(id);
    }
}
