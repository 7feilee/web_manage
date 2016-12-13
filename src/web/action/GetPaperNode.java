package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Tree;
import service.Service;
public class GetPaperNode extends ActionSupport
{
	private int uid;
	private int pid;
	private Tree tree;
	private Service service;
	public GetPaperNode()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		tree = service.getPaperNode(uid,pid);
		if(tree!=null)
			return SUCCESS;
		return ERROR;
	}
	public Tree getTree()
	{
		return tree;
	}
	public void setTree(Tree tree)
	{
		this.tree = tree;
	}
//	public int getPid()
//	{
//		return pid;
//	}
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public void setUid(int uid)
	{
		this.uid = uid;
	}
}
