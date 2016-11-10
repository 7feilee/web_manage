package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.*;
import utils.Security;

import javax.servlet.http.Cookie;
public class Login extends ActionSupport
{
	private String username;
	private String password;
	private Service service;
	private String autoLogin;
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
		if (username.length() < 50 || username.length() > 0) ;
		else
			return ERROR;
		uid = service.login(username, password);
		if (uid > 0)
		{
			//设置session
			User user = service.getUserById(uid);
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			//设置cookie
			if(autoLogin!=null && autoLogin.equals("on"))
			{
				String token = Security.MD5(username + password);
				Cookie uidCookie = new Cookie("uid", uid.toString());
				uidCookie.setMaxAge(60 * 60 * 24 * 30);
				ServletActionContext.getResponse().addCookie(uidCookie);
				Cookie utokenCookie = new Cookie("utoken", token);
				utokenCookie.setMaxAge(60 * 60 * 24 * 30);
				ServletActionContext.getResponse().addCookie(utokenCookie);
			}
			//请求重定向
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
	public String isAutoLogin()
	{
		return autoLogin;
	}
	public void setAutoLogin(String autoLogin)
	{
		this.autoLogin = autoLogin;
	}
}
