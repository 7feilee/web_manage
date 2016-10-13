package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import service.Service;

import java.text.SimpleDateFormat;
public class ShowPaperDetails extends ActionSupport
{
	private Paper paper;
	private int id;
	private Service service;
	private String dateStr;
	
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
}
