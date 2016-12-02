package service;
import dao.*;
import model.*;

import java.util.*;
import java.sql.Timestamp;
public class Service
{
	private UserDao userDao;
	private PaperDao paperDao;
	private NoteDao noteDao;
	private LogDao logDao;
	
	public Service()
	{
		super();
		userDao = new UserDao();
		paperDao = new PaperDao();
		noteDao = new NoteDao();
		logDao = new LogDao();
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
		if(user!=null)
		{
			user.setToReadPapers(getPaperByState(user.getId(), 1));
			user.setReadPapers(getPaperByState(user.getId(), 2));
			user.setStudiedPapers(getPaperByState(user.getId(), 3));
		}
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
		Timestamp publishTime = new Timestamp(System.currentTimeMillis());
		return noteDao.insertNote(authorId,paperId,title,content,publishTime);
	}
	public Collection<Note> getNotesByUser(int uid)
	{
		return noteDao.getNotesByUser(uid);
	}
	public Collection<Note> getNotesByPaper(int pid)
	{
		return noteDao.getNotesByPaper(pid);
	}
	public Collection<Log> getAllLogs()
	{
		return logDao.getAllLogs();
	}
	public Collection<Log> getLogsByUser(int uid)
	{
		return logDao.getLogsByUser(uid);
	}
	public int addTreeLabel(String labelname,String label_father,int user_id){
        return userDao.addTreeLabel(labelname,label_father,user_id);
    }

    public Tree getUserTree(int user_id){
		Tree tree=new Tree();
		tree.setChildTree(getTree(user_id,"null",0));
		return tree;
	}

	public LinkedList<Tree> getUserTreeList(int user_id){
		LinkedList<Tree> trees=new LinkedList<>();
		LinkedList<Tree> queue=new LinkedList<>();
		Tree ftree=new Tree();
		ftree.setLabelname("null");
		int depth=-1;
		ftree.setDepth(depth);
		queue.add(ftree);
		int size=queue.size();
		while (size!=0){
			for (int i=0;i<size;i++) {
				Collection<Tree> ctree = userDao.getChildTree(user_id, queue.getFirst().getLabelname());
				int pos=trees.indexOf(queue.getFirst());
				int d=queue.getFirst().getDepth();
				for (Tree tree1 : ctree) {
					if (tree1!=null) {
						tree1.setDepth(d+1);
						trees.add(++pos, tree1);
						queue.add(tree1);
					}
				}
				queue.remove();
				size = queue.size();
			}
		}
		return trees;
	}

	public Collection<Tree> getTree(int user_id, String labelname,int depth){
		Collection<Tree> trees = userDao.getChildTree(user_id,labelname);
		int newdepth=depth+1;
		if (trees==null)
			return null;
		else{
			for (Tree tree : trees) {
				tree.setChildTree(getTree(user_id,tree.getLabelname(),newdepth));
				Collection<Integer> paperids=userDao.getTreePapers(tree.getLabelname(),user_id);
				Collection<Paper> papers=new LinkedList<>();
				for (Integer paperid : paperids) {
					Paper paper=getPaperById(paperid);
					papers.add(paper);
				}
				tree.setPapers(papers);
				tree.setDepth(depth);
			}
		}
		return trees;
	}

	public int updatePaperlabel(int user_id ,int paper_id ,String newlabelname){
		return userDao.updatePaperLabel(newlabelname,user_id,paper_id);
	}

	public int deleteTreeLabel(int user_id,String labelname){
		return userDao.deleteTreeLabel(labelname,user_id);
	}

	public Collection<Paper> getLabelPapers(int user_id, String labelname){
		Collection<Paper> papers=new LinkedList<>();
		LinkedList<Tree> queue=new LinkedList<>();
		Tree ftree=new Tree();
		ftree.setLabelname(labelname);
		queue.add(ftree);
		int size=queue.size();
		while (size!=0){
			for (int i=0;i<size;i++) {
				Collection<Tree> ctree = userDao.getChildTree(user_id, queue.getFirst().getLabelname());
				int d=queue.getFirst().getDepth();
				for (Tree tree1 : ctree) {
					if (tree1!=null) {
						Collection<Integer> paperids=userDao.getTreePapers(tree1.getLabelname(),user_id);
						for (Integer paperid : paperids) {
							papers.add(getPaperById(paperid));
						}
						queue.add(tree1);
					}
				}
				queue.remove();
				size = queue.size();
			}
		}
		return papers;
	}
}
