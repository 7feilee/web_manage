package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Note;
import service.Service;

import java.text.SimpleDateFormat;
public class ShowNoteDetails extends ActionSupport
{
	private String errMsg;
	private Note note;
	private int id;
	private Service service;
	private String dateStr;
	
	public ShowNoteDetails()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		note = service.getNoteById(id);
		if (note != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = sdf.format(note.getPublishTime());
			return SUCCESS;
		}
		return ERROR;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getDateStr()
	{
		return dateStr;
	}
	public void setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
	}
	public Note getNote()
	{
		return note;
	}
	public void setNote(Note note)
	{
		this.note = note;
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
