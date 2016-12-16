package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import model.Note;
import model.Paper;
import service.Service;
import utils.FormatLog;
import web.model.FrontLog;

import java.text.SimpleDateFormat;
import java.util.Collection;
public class ShowPaperDetails extends ActionSupport
{
	private String errMsg;
	private Paper paper;
	private int id;
	private Service service;
	private StringBuilder authors;
	private StringBuilder keywords;
	private String dateStr;
	private Collection<Note> notes;
	private Collection<FrontLog> logs;
	
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
			if(paper.getPublishDate()!=null)
				dateStr = sdf.format(paper.getPublishDate());
			authors = new StringBuilder();
			keywords = new StringBuilder();
			for (String author : paper.getAuthors())
				authors.append(author).append(";");
			for (String keyword : paper.getKeywords())
				keywords.append(keyword).append(";");
			notes = service.getNotesByPaper(id);
			if (notes != null)
			{
				Collection<Log> logs1 = service.getLogsByPaper(id);
				if (logs1 != null)
				{
					logs = FormatLog.formatLogs(logs1);
					return SUCCESS;
				}
				errMsg = "获取日志失败";
				return ERROR;
			}
			errMsg = "获取笔记失败";
			return ERROR;
		}
		errMsg = "获取论文失败";
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
	public String getAuthors()
	{
		return authors.toString();
	}
	public void setAuthors(String authors)
	{
		this.authors = new StringBuilder(authors);
	}
	public String getKeywords()
	{
		return keywords.toString();
	}
	public void setKeywords(String keywords)
	{
		this.keywords = new StringBuilder(keywords);
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
