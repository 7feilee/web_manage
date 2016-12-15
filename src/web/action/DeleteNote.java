package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;
public class DeleteNote extends ActionSupport
{
	private int id;
	private Service service;
	private String errMsg;
	
	public DeleteNote()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null)
		{
			errMsg = "登录状态无效，请重新登录！";
			return ERROR;
		}
		if (service.deleteNote(id, user.getId()) > 0)
			return SUCCESS;
		errMsg = "数据库没电了>_<";
		return ERROR;
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
