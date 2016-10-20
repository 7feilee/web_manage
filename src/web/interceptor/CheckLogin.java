package web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;
import utils.Security;

import javax.servlet.http.Cookie;
public class CheckLogin extends MethodFilterInterceptor
{
	
	@Override
	public String doIntercept(ActionInvocation actionInvocation) throws Exception
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
									return actionInvocation.invoke();
								}
								else
									return "input";
							}
						}
						else
							return "input";
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
								return actionInvocation.invoke();
							}
							else
								return "input";
						}
					}
				}
			}
			return "input";
		}
		else
		{
			return actionInvocation.invoke();
		}
	}
}
