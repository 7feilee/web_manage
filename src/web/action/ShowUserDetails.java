package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Note;
import model.User;
import service.Service;

import java.util.Collection;
public class ShowUserDetails extends ActionSupport
{
	private User user;
	private int id;
	private Service service;
	private Collection<Note> notes;
	
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
				return SUCCESS;
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
}
