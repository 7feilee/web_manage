package web.action;
import com.opensymphony.xwork2.ActionSupport;
import service.Service;

public class EditUserTree extends ActionSupport
{
	private String data;
	private Service service;
	
	public EditUserTree()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
}
