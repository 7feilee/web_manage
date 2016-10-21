package web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
public class Logout extends ActionSupport
{
	@Override
	public String execute() throws Exception
	{
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for (Cookie cookie : cookies)
		{
			cookie.setMaxAge(0);
			ServletActionContext.getResponse().addCookie(cookie);
		}
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return SUCCESS;
	}
}
