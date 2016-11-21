package service;
import dao.NoteDao;
import dao.PaperDao;
import dao.UserDao;
import model.Note;
import model.Paper;
import model.User;
import web.action.AddNote;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
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
	
	public Integer addNewUser(String username, String password)
	{
		User user = userDao.getUserByUsername(username);
		if (user == null)
		{
			userDao.insertNewUser(username, password);
			return 1;
		}
		else
			return 0;
	}
	
	public Collection<Paper> getPapers()
	{
		return paperDao.getAllPapers();
	}
	
	public Paper getPaperById(int id)
	{
		return paperDao.getPaperById(id);
	}
	
	public User getUserById(int id)
	{
		User user = userDao.getUserById(id);
		user.setToReadPapers(getPaperByState(user.getId(), 1));
		user.setReadPapers(getPaperByState(user.getId(), 2));
		user.setStudiedPapers(getPaperByState(user.getId(), 3));
		return user;
	}
	
	public int updatePaperState(int user_id, int paper_id, int state)
	{
		return userDao.updatePaperState(user_id, paper_id, state);
	}
	
	private Collection<Paper> getPaperByState(int user_id, int state)
	{
		Collection<Paper> papers = new LinkedList<>();
		Collection<Integer> paperids = userDao.getPaperidByState(user_id, state);
		for (Integer paperid : paperids)
		{
			int i = (int) paperid;
			papers.add(paperDao.getPaperById(i));
		}
		return papers;
	}
	
	public int addPaper(String title, Collection<String> authors, String fileURI, Collection<String> keywords,
                        String abstct, Date publishDate, int operater){
		java.sql.Date publishDate2=new java.sql.Date(publishDate.getTime());
	    return paperDao.insertNewPaper(title,fileURI,publishDate2,authors,abstct,keywords);
    }

	public int getPaperState(int user_id, int paper_id){
		return userDao.getPaperState(user_id,paper_id);
	}
	
	public Note getNoteById(int nid)
	{
		return noteDao.getNoteById(nid);
	}
	public Collection<Note> getNotes()
	{
		return noteDao.getAllNotes();
	}
	public int addNote(String title, String content, int authorId, int paperId)
	{
		java.sql.Date publishTime = new java.sql.Date(new Date().getTime());
		return noteDao.insertNote(authorId,paperId,title,content,publishTime);
	}
}
