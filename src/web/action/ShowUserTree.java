package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.Tree;
import service.Service;

import java.util.Collection;
public class ShowUserTree extends ActionSupport
{
	//private Tree tree;
	private Service service;
	private int uid;
	private Collection<Tree> trees;
	private Collection<Paper> papers;
	//private String
	
	public ShowUserTree()
	{
		super();
		service = new Service();
		trees = null;
	}
	
	@Override
	public String execute() throws Exception
	{
		if (uid > 0)
		{
			Tree tree = service.getUserTree(uid);
			trees = service.getUserTreeList(uid);
			papers = service.getLabelPapers(uid, "null");
			if (trees != null)
				return SUCCESS;
			else
				return ERROR;
		}
		return ERROR;
	}
	/*
	public Tree getTree() {

        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }*/
	
	public int getUid()
	{
		return uid;
	}
	
	public void setUid(int uid)
	{
		this.uid = uid;
	}
	
	public Collection<Tree> getTrees()
	{
		return trees;
	}
	
	public void setTrees(Collection<Tree> trees)
	{
		this.trees = trees;
	}
	
	public Collection<Paper> getPapers()
	{
		return papers;
	}
	
	public void setPapers(Collection<Paper> papers)
	{
		this.papers = papers;
	}
}
