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
	private String errMsg;
	
	public AddEditNote()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		if (id == 0)
		{//新增笔记
			if (title != null && !title.matches("\\s*"))
			{
				User author = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				if (author == null)
				{
					errMsg = "登录状态无效，请重新登录！";
					return ERROR;
				}
				Paper paper = service.getPaperById(paperid);
				if (paper == null)
				{
					errMsg = "无此论文！";
					return ERROR;
				}
				if (service.addNote(title, content, author.getId(), paperid) > 0)
					return SUCCESS;
				return ERROR;
			}
			errMsg = "标题不能为空！";
			return ERROR;
		}
		else
		{//编辑笔记
			if (title != null && !title.matches("\\s*"))
			{
				User operator = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				if (operator == null)
				{
					errMsg = "登录状态无效！";
					return ERROR;
				}
				if (service.editNote(id, title, content, operator.getId()) > 0)
					return SUCCESS;
				errMsg = "数据库没电了>_<";
				return ERROR;
			}
			errMsg = "标题不能为空！";
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
	public String getErrMsg()
	{
		return errMsg;
	}
	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
}
