package utils;

import model.User;
import org.apache.struts2.ServletActionContext;
import org.jetbrains.annotations.NotNull;
import service.Service;

import javax.servlet.http.Cookie;
public class CheckLogin
{
	@NotNull
	public static Boolean checkLogin()
	{
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (obj == null)
		{
			//session方式失败，转用cookie方式
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			String token = null;
			User user = null;
			if (cookies != null)
			{
				Service service = new Service();
				for (Cookie cookie : cookies)
				{
					if (cookie.getName().equals("uid"))
					{
						user = service.getUserById(Integer.valueOf(cookie.getValue()));
						if (user != null)
						{
							if (token == null)
								token = Security.MD5(user.getUsername() + user.getPassword());
							else
							{
								if (token.equals(Security.MD5(user.getUsername() + user.getPassword())))
								{
									ServletActionContext.getRequest().getSession().setAttribute("user", user);
									return true;
								}
								else
									return false;
							}
						}
						else
						{
							for (Cookie cookie1 : cookies)
							{
								cookie1.setMaxAge(0);
								ServletActionContext.getResponse().addCookie(cookie1);
							}
							ServletActionContext.getRequest().getSession().removeAttribute("user");
							return false;
						}
					}
					else if (cookie.getName().equals("utoken"))
					{
						if (token == null)
							token = cookie.getValue();
						else
						{
							if (token.equals(cookie.getValue()))
							{
								ServletActionContext.getRequest().getSession().setAttribute("user", user);
								return true;
							}
							else
								return false;
						}
					}
				}
			}
			return false;
		}
		else
		{
			return true;
		}
	}
}
