package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import model.Note;
import model.Tree;
import model.User;
import service.Service;
import utils.FormatLog;
import web.model.FrontLog;

import java.util.Collection;
public class ShowUserDetails extends ActionSupport
{
	private User user;
	private int id;
	private Service service;
	private Collection<Note> notes;
	private Collection<FrontLog> logs;
	private StringBuilder frontEndTree;
	
	public ShowUserDetails()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		user = service.getUserById(id);
		if (user != null)
		{
			notes = service.getNotesByUser(id);
			if (notes != null)
			{
				Collection<Log> logs1 = service.getLogsByUser(id);
				if (logs1 != null)
				{
					logs = FormatLog.formatLogs(logs1);
					Collection<Tree> trees = service.getUserTreeList(id);
					//papers = service.getLabelPapers(uid, "null");
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
						if(frontEndTree.length()==0)
							frontEndTree.append("<ul>\n<li>root</li>\n</ul>");
						return SUCCESS;
					}
					else
						return ERROR;
				}
				return ERROR;
			}
			//else
			return ERROR;
		}
		return ERROR;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Collection<Note> getNotes()
	{
		return notes;
	}
	public void setNotes(Collection<Note> notes)
	{
		this.notes = notes;
	}
	public Collection<FrontLog> getLogs()
	{
		return logs;
	}
	public void setLogs(Collection<FrontLog> logs)
	{
		this.logs = logs;
	}
	public StringBuilder getFrontEndTree()
	{
		return frontEndTree;
	}
	public void setFrontEndTree(StringBuilder frontEndTree)
	{
		this.frontEndTree = frontEndTree;
	}
}
