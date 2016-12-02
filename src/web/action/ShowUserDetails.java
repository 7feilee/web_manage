package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import model.Note;
import model.User;
import service.Service;
import utils.FormatLog;
import web.model.FrontLog;

import java.util.Collection;
public class ShowUserDetails extends ActionSupport
{
	private User user;
	private int id;
	private Service service;
	private Collection<Note> notes;
	private Collection<FrontLog> logs;
	
	public ShowUserDetails()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		user = service.getUserById(id);
		if (user != null)
		{
			notes = service.getNotesByUser(id);
			if (notes != null)
			{
				Collection<Log> logs1 = service.getLogsByUser(id);
				if (logs1 != null)
				{
					logs = FormatLog.formatLogs(logs1);
					return SUCCESS;
				}
				return ERROR;
			}
			//else
			return ERROR;
		}
		return ERROR;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Collection<Note> getNotes()
	{
		return notes;
	}
	public void setNotes(Collection<Note> notes)
	{
		this.notes = notes;
	}
	public Collection<FrontLog> getLogs()
	{
		return logs;
	}
	public void setLogs(Collection<FrontLog> logs)
	{
		this.logs = logs;
	}
}
