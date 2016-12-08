package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Note;
import model.Paper;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
public class ShowPaperDetails extends ActionSupport
{
	private Paper paper;
	private int id;
	private Service service;
	private String dateStr;
	private Collection<Note> notes;
	
	public ShowPaperDetails()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		paper = service.getPaperById(id);
		if (paper != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = sdf.format(paper.getPublishDate());
			notes = service.getNotesByPaper(id);
			if (notes != null)
				return SUCCESS;
			//else
			return ERROR;
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
	public Paper getPaper()
	{
		return paper;
	}
	public void setPaper(Paper paper)
	{
		this.paper = paper;
	}
	public String getDateStr()
	{
		return dateStr;
	}
	public void setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
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
