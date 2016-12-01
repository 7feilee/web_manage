package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import service.Service;

import java.util.Collection;
public class ListLogs extends ActionSupport
{
	private Collection<Log> logs;
	private Service service;
	public ListLogs()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		/*
		logs = service.getAllLogs();
		if(logs!=null)
			return SUCCESS;
		return ERROR;*/
		ActionContext.getContext().put("123","456");
		return SUCCESS;
	}
	public Collection<Log> getLogs()
	{
		return logs;
	}
	public void setLogs(Collection<Log> logs)
	{
		this.logs = logs;
	}
}
