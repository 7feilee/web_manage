package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

public class AddNote extends ActionSupport
{
	private int paperid;
	
	private String title;
	private String content;
	private Service service;
	
	public AddNote()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		if (title != null)
		{
			User author = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			if (author == null)
				return ERROR;
			//else
			Paper paper = service.getPaperById(paperid);
			if (paper == null)
				return ERROR;
			//else
			if (service.addNote(title, content, author.getId(), paperid) > 0)
				return SUCCESS;
			//else
			return ERROR;
		}
		//else
		return ERROR;
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
}
