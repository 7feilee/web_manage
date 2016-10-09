package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import service.Service;
public class ShowUserDetails extends ActionSupport
{
	private User user;
	private int id;
	private Service service;
	
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
			return SUCCESS;
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
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
}
