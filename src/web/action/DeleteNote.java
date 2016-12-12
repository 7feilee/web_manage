package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;
public class DeleteNote extends ActionSupport
{
	private int id;
	private Service service;
	
	public DeleteNote()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null)
			return null;
		if(service.deleteNote(id,user.getId())>0)
			return SUCCESS;
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
}
