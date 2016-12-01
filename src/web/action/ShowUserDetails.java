package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import model.Note;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.util.Collection;
public class ShowUserDetails extends ActionSupport
{
	private User user;
	private int id;
	private Service service;
	private Collection<Note> notes;
	private Collection<Log> logs;
	
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
			if(notes != null)
			{
				logs = service.getLogsByUser(id);
				if(logs!=null)
				{
					ServletActionContext.getRequest().setAttribute("logs",logs);
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
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public Collection<Note> getNotes()
	{
		return notes;
	}
	public void setNotes(Collection<Note> notes)
	{
		this.notes = notes;
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
