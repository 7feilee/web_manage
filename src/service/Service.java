package service;
import dao.LogDao;
import dao.NoteDao;
import dao.PaperDao;
import dao.UserDao;
import model.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
public class Service
{
	private UserDao userDao;
	private PaperDao paperDao;
	private NoteDao noteDao;
	private LogDao logDao;

	public Service() {
		super();
		userDao = new UserDao();
		paperDao = new PaperDao();
		noteDao = new NoteDao();
		logDao = new LogDao();
	}

	public int login(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if (user != null)
			if (password.equals(user.getPassword()))
				return user.getId();
		return 0;
	}

	public Integer addNewUser(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			userDao.insertNewUser(username, password);
			return 1;
		} else
			return 0;
	}

	public Collection<Paper> getPapers() {
		return paperDao.getAllPapers();
	}

	public Paper getPaperById(int id) {
		return paperDao.getPaperById(id);
	}

	public User getUserById(int id) {
		User user = userDao.getUserById(id);
		if (user != null) {
			user.setToReadPapers(getPaperByState(user.getId(), 1));
			user.setReadPapers(getPaperByState(user.getId(), 2));
			user.setStudiedPapers(getPaperByState(user.getId(), 3));
		}
		return user;
	}

	public int updatePaperState(int user_id, int paper_id, int state) {
		return userDao.updatePaperState(user_id, paper_id, state);
	}

	private Collection<Paper> getPaperByState(int user_id, int state) {
		Collection<Paper> papers = new LinkedList<>();
		Collection<Integer> paperids = userDao.getPaperidByState(user_id, state);
		for (Integer paperid : paperids)
		{
			int i = paperid;
			papers.add(paperDao.getPaperById(i));
		}
		return papers;
	}

	public int addPaper(String title, Collection<String> authors, String fileURI, Collection<String> keywords,
						String abstct, Date publishDate, String sourceURL, int operater) {
		java.sql.Date publishDate2 = new java.sql.Date(publishDate.getTime());
		return paperDao.insertNewPaper(title, fileURI, publishDate2, authors, abstct, keywords, "C:\\Users\\JevonsAn\\Desktop\\MOOC_特征与学习机制_王永固.pdf", operater);
	}

	public Tree getPaperNode(int uid, int pid)
	{
		return userDao.getPaperNode(uid,pid);
	}
	public int getPaperState(int user_id, int paper_id)
	{
		return userDao.getPaperState(user_id, paper_id);
	}

	public Note getNoteById(int nid) {
		return noteDao.getNoteById(nid);
	}

	public Collection<Note> getNotes() {
		return noteDao.getAllNotes();
	}

	public int addNote(String title, String content, int authorId, int paperId) {
		Timestamp publishTime = new Timestamp(System.currentTimeMillis());
		return noteDao.insertNote(authorId, paperId, title, content, publishTime);
	}

	public int editNote(int id, String title, String content, int operatorId)
	{
		Timestamp editTime = new Timestamp(System.currentTimeMillis());
		return noteDao.updateNote(id, title, content, operatorId, editTime);
	}
	public Collection<Note> getNotesByUser(int uid)
	{
		return noteDao.getNotesByUser(uid);
	}

	public Collection<Note> getNotesByPaper(int pid) {
		return noteDao.getNotesByPaper(pid);
	}

	public Collection<Log> getAllLogs() {
		return logDao.getAllLogs();
	}

	public Collection<Log> getLogsByUser(int uid) {
		return logDao.getLogsByUser(uid);
	}
	public Collection<Log> getLogsByPaper(int pid){return logDao.getLogsByPaper(pid);}
	public int addTreeLabel(String labelname, String label_father, int user_id)
	{
		return userDao.addTreeLabel(labelname, label_father, user_id);
	}

	public Tree getUserTree(int user_id) {
		Tree tree = new Tree();
		tree.setChildTree(getTree(user_id, "null", 0));
		return tree;
	}

	public LinkedList<Tree> getUserTreeList(int user_id) {
		LinkedList<Tree> trees = new LinkedList<>();
		LinkedList<Tree> queue = new LinkedList<>();
		Tree ftree = new Tree();
		ftree.setLabelname("null");
		int depth = -1;
		ftree.setDepth(depth);
		queue.add(ftree);
		int size = queue.size();
		while (size != 0) {
			for (int i = 0; i < size; i++) {
				Collection<Tree> ctree = userDao.getChildTree(user_id, queue.getFirst().getLabelname());
				int pos = trees.indexOf(queue.getFirst());
				int d = queue.getFirst().getDepth();
				for (Tree tree1 : ctree) {
					if (tree1 != null) {
						tree1.setDepth(d + 1);
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

	private Collection<Tree> getTree(int user_id, String labelname, int depth) {
		Collection<Tree> trees = userDao.getChildTree(user_id, labelname);
		int newdepth = depth + 1;
		if (trees == null)
			return null;
		else {
			for (Tree tree : trees) {
				tree.setChildTree(getTree(user_id, tree.getLabelname(), newdepth));
				Collection<Integer> paperids = userDao.getTreePapers(tree.getLabelname(), user_id);
				Collection<Paper> papers = new LinkedList<>();
				for (Integer paperid : paperids) {
					Paper paper = getPaperById(paperid);
					papers.add(paper);
				}
				tree.setPapers(papers);
				tree.setDepth(depth);
			}
		}
		return trees;
	}

	public int updatePaperlabel(int user_id, int paper_id, String newlabelname) {
		return userDao.updatePaperLabel(newlabelname, user_id, paper_id);
	}

	public int deleteTreeLabel(int user_id, String labelname) {
		return userDao.deleteTreeLabel(labelname, user_id);
	}

	public LinkedList<Paper> getLabelAndChildrenPapers(int user_id, String labelname) {
		LinkedList<Paper> papers = new LinkedList<>();
		LinkedList<Tree> queue = new LinkedList<>();
		Tree ftree = new Tree();
		ftree.setLabelname(labelname);
		queue.add(ftree);
		Collection<Integer> paperids = userDao.getTreePapers(ftree.getLabelname(), user_id);
		for (Integer paperid : paperids) {
			papers.add(getPaperById(paperid));
		}
		int size = queue.size();
		while (size != 0) {
			for (int i = 0; i < size; i++) {
				Collection<Tree> ctree = userDao.getChildTree(user_id, queue.getFirst().getLabelname());
				int d = queue.getFirst().getDepth();
				for (Tree tree1 : ctree) {
					if (tree1 != null) {
						Collection<Integer> paperids2 = userDao.getTreePapers(tree1.getLabelname(), user_id);
						for (Integer paperid : paperids2) {
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

	public Collection<Paper> getLabelPapers(int user_id, String labelname)
	{
		Collection<Integer> paperids = userDao.getTreePapers(labelname, user_id);
		Collection<Paper> papers = new LinkedList<>();
		for (Integer paperid : paperids)
		{
			papers.add(paperDao.getPaperById(paperid));
		}
		return papers;
	}

	public int addLog(int type, int target, int targetid, int operatorid) {
		return logDao.insertLog(type, target, targetid, operatorid);
	}

	public int deleteNote(int nid, int uid)
	{
		return noteDao.deleteNote(nid, uid);
	}
	public int editPaper(int id, String title, Collection<String> authors, String fileURI, Collection<String> keywords, String abstct, Date publishDate, int uid)
	{
		java.sql.Date publishDate2 = new java.sql.Date(publishDate.getTime());
		return paperDao.updatePaper(id, title, authors, fileURI, keywords, abstct, publishDate2, uid);
	}
	
	public int resetTree(List<Tree> treelist, int user_id)
	{
		int size = treelist.size();
		int id = 0;
		Tree tree = null;
		Tree ytree = null;
		int result = -1;
		LinkedList<Tree> list2 = getUserTreeList(user_id);
		LinkedList<Integer> ids = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			tree = treelist.get(i);
			id = tree.getId();
			ids.add(id);
		}
		for (Tree tree1 : list2) {
			if (ids.indexOf(tree1.getId()) == -1)
				userDao.deleteTreeLabel(tree1.getLabelname(), user_id);
		}
		for (int i = 0; i < size; i++)
		{
			int depth = treelist.get(i).getDepth();
			if (depth == 0)
				treelist.get(i).setLabel_father("null");
			for (int j = i + 1; j < size; j++)
			{
				int cdepth = treelist.get(j).getDepth();
				if (cdepth > depth)
				{
					if (cdepth == (depth + 1))
					{
						treelist.get(j).setLabel_father(treelist.get(i).getLabelname());
					}
				} else
					break;
			}
		}
		for (int i = 0; i < size; i++) {
			tree = treelist.get(i);
			id = tree.getId();
			ytree = userDao.getTreeById(id);
			if (ytree == null)
				result = userDao.addTreeLabel(tree.getLabelname(), tree.getLabel_father(), user_id);
			else {
				if (!tree.getLabelname().equals(ytree.getLabelname()))
					result = userDao.updateTreeLabel(id, tree.getLabelname());
				if (!tree.getLabel_father().equals(ytree.getLabel_father()))
					result = userDao.updateLabelFather(id, tree.getLabel_father());
			}
		}
		return result;
	}

	public BufferedInputStream getPaperBSbyid(int paper_id){
		return new BufferedInputStream(paperDao.getPaperBSbyid(paper_id));
	}

	public InputStream getPaperFSbyid(int paper_id){
		return paperDao.getPaperBSbyid(paper_id);
	}

	public Tree getTreeByid(int id){
		return userDao.getTreeById(id);
	}
}
