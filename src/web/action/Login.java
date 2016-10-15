package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.*;
import utils.Security;
public class Login extends ActionSupport
{
	private String username;
	private String password;
	private String token;
	private Service service;
	public Login()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		Integer uid;
		// TODO: 输入验证
		token = Security.MD5(username + password);
		uid = service.login(username, password);
		if (uid > 0)
		{
			ServletActionContext.getResponse().sendRedirect("showUserDetails?id="+uid.toString());
			return SUCCESS;
		}
		else
		{
			ServletActionContext.getRequest().setAttribute("err", true);
			return ERROR;
		}
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
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
}
