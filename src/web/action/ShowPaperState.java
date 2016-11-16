package web.action;

import com.opensymphony.xwork2.ActionSupport;
import service.Service;
public class ShowPaperState extends ActionSupport
{
	private int uid;
	private int pid;
	private int state;
	private Service service;
	
	public ShowPaperState()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		state = service.getPaperState(uid,pid);
		if (state >= 0)
			return SUCCESS;
		//else
		return ERROR;
	}
	public int getUid()
	{
		return uid;
	}
	public void setUid(int uid)
	{
		this.uid = uid;
	}
	public int getPid()
	{
		return pid;
	}
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public int getState()
	{
		return state;
	}
}
