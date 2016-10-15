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
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("uid");
		if(obj == null)
		{
			//session方式失败，转用cookie方式
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			String token = null;
			if(cookies != null)
			{
				Service service = new Service();
				for (Cookie cookie : cookies)
				{
					if (cookie.getName().equals("uid"))
					{
						User user = service.getUserById(Integer.valueOf(cookie.getValue()));
						if (user != null)
						{
							if(token == null)
								token = Security.MD5(user.getUsername() + user.getPassword());
							else
							{
								if(token.equals(Security.MD5(user.getUsername() + user.getPassword())))
									return actionInvocation.invoke();
								else
									return "input";
							}
						}
						else
							return "input";
					}
					else if(cookie.getName().equals("utoken"))
					{
						if(token == null)
							token = cookie.getValue();
						else
						{
							if(token.equals(cookie.getValue()))
								return actionInvocation.invoke();
							else
								return "input";
						}
					}
				}
			}
			return "input";
		}
		//4.用户登录了，放行
		return actionInvocation.invoke();
	}
}
