package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

public class AddEditNote extends ActionSupport
{
	private int paperid;
	private int id;
	private String title;
	private String content;
	private Service service;
	
	public AddEditNote()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		if(id == 0)
		{//新增笔记
			if (title != null && !title.matches("\\s*"))
			{
				User author = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				if (author == null)
					return ERROR;
				Paper paper = service.getPaperById(paperid);
				if (paper == null)
					return ERROR;
				if (service.addNote(title, content, author.getId(), paperid) > 0)
					return SUCCESS;
				return ERROR;
			}
			return ERROR;
		}
		else
		{//编辑笔记
			if (title != null && !title.matches("\\s*"))
			{
				User operator = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				if (operator == null)
					return ERROR;
				if (service.editNote(id, title, content, operator.getId()) > 0)
					return SUCCESS;
				return ERROR;
			}
			return ERROR;
		}
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getPaperid()
	{
		return paperid;
	}
	public void setPaperid(int paperid)
	{
		this.paperid = paperid;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
