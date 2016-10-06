package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.*;
import service.*;

import java.io.Serializable;

public class Login extends ActionSupport
{
	private String username;
	private String password;
	private Service service;
	public Login()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute()
	{
		Integer state;
		// TODO: 输入验证
		state = service.login(username,password);
		if(state == 0)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
