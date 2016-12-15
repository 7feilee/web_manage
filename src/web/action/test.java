package web.action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Created by ZZY on 2016/12/15.
 */
public class test extends ActionSupport
{
	private String test;
	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}
	public String getTest()
	{
		return test;
	}
	public void setTest(String test)
	{
		this.test = test;
	}
}
