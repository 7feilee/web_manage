package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;
import utils.Security;

import javax.servlet.http.Cookie;
public class Register extends ActionSupport
{
	private Service service;
	private String username;
	private String password;
	private String token;
	private int id;
	public Register()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		Integer state;
		// TODO: 输入验证
		state = service.addNewUser(username, password);
		if (state == 1)
		{
			//设置session
			Integer uid = service.login(username, password);
			if (uid <= 0)
				return ERROR;
			User user = service.getUserById(uid);
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			//设置cookie
			String token = Security.MD5(username + password);
			Cookie uidCookie = new Cookie("uid", uid.toString());
			uidCookie.setMaxAge(60 * 60 * 24 * 30);
			ServletActionContext.getResponse().addCookie(uidCookie);
			Cookie utokenCookie = new Cookie("utoken", token);
			utokenCookie.setMaxAge(60 * 60 * 24 * 30);
			ServletActionContext.getResponse().addCookie(utokenCookie);
			//请求重定向
			ServletActionContext.getResponse().sendRedirect("showUserDetails?id=" + uid.toString());
			return SUCCESS;
		}
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
		return username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getToken()
	{
		return username;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public int getId()
	{
		return id;
	}
}
