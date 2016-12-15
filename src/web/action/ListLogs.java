package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import service.Service;
import utils.FormatLog;
import web.model.FrontLog;

import java.util.Collection;
public class ListLogs extends ActionSupport
{
	private String errMsg;
	private Collection<FrontLog> logs;
	private Service service;
	public ListLogs()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		
		Collection<Log> logs1 = service.getAllLogs();
		if (logs1 != null)
		{
			logs = FormatLog.formatLogs(logs1);
			return SUCCESS;
		}
		errMsg = "数据库没电了>_<";
		return ERROR;
	}
	public Collection<FrontLog> getLogs()
	{
		return logs;
	}
	public void setLogs(Collection<FrontLog> logs)
	{
		this.logs = logs;
	}
	public String getErrMsg()
	{
		return errMsg;
	}
	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
}
