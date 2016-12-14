package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Tree;
import model.User;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import service.Service;
import web.model.JSONTree;

import java.util.LinkedList;
import java.util.List;

public class EditUserTree extends ActionSupport
{
	private String data;
	private Service service;
	
	public EditUserTree()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null)
			return ERROR;
		List<Tree> treeList = new LinkedList<>();
//		data = data.replace("\"", "");
//		int depth = 0;
		JSONArray jsonArray = JSONArray.fromObject(data);
		JSONTree[] trees = (JSONTree[]) JSONArray.toArray(jsonArray, JSONTree.class);
		for (JSONTree tree : trees)
			treeList.addAll(tree.toTreeList());
		if (service.resetTree(treeList, user.getId()) > 0)
			ServletActionContext.getResponse().sendRedirect("showUserDetails?id="+user.getId());
		return ERROR;
	}
	
//	public String getData()
//	{
//		return data;
//	}
	public void setData(String data)
	{
		this.data = data;
	}
}
