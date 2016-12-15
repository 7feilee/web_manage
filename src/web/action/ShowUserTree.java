package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Tree;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.util.Collection;
public class ShowUserTree extends ActionSupport
{
	private String errMsg;
	//private Tree tree;
	private Service service;
	private Collection<Tree> trees;
	//	private Collection<Paper> papers;
	private StringBuilder frontEndTree;
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
		User operator = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (operator == null)
		{
			errMsg = "登录状态失效";
			return ERROR;
		}
		int uid = operator.getId();
		trees = service.getUserTreeList(uid);
//		papers = service.getLabelPapers(uid, "null");
		if (trees != null)
		{
			frontEndTree = new StringBuilder();
			int depth = -1;//当前深度
			for (Tree tree : trees)
			{
				if (tree.getDepth() <= depth)
					frontEndTree.append("</li>\n");
				for (; tree.getDepth() > depth; depth++)
					frontEndTree.append("<ul>\n");
				for (; tree.getDepth() < depth; depth--)
					frontEndTree.append("</ul>\n</li>\n");
				frontEndTree.append("<li id='").append(tree.getId()).append("'><span>").append(tree.getLabelname()).append("</span>\n");
			}
			for (; -1 < depth; depth--)
				frontEndTree.append("</li>\n</ul>\n");
			if (frontEndTree.length() == 0)
				frontEndTree.append("<ul>\n<li>root</li>\n</ul>");
			return SUCCESS;
		}
		errMsg = "无法获取用户分类树";
		return ERROR;
	}
	/*
	public Tree getTree() {

        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }*/
	
	public Collection<Tree> getTrees()
	{
		return trees;
	}
	
	public void setTrees(Collection<Tree> trees)
	{
		this.trees = trees;
	}
	
	//	public Collection<Paper> getPapers()
//	{
//		return papers;
//	}
//
//	public void setPapers(Collection<Paper> papers)
//	{
//		this.papers = papers;
//	}
	public String getFrontEndTree()
	{
		return String.valueOf(frontEndTree);
	}
	public void setFrontEndTree(String frontEndTree)
	{
		this.frontEndTree = new StringBuilder(frontEndTree);
	}
	public String getErrMsg()
	{
		return errMsg;
	}
	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
}
