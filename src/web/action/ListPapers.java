package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import service.Service;

import java.util.Collection;
public class ListPapers extends ActionSupport
{
	private Collection<Paper> papers;
	private Service service;
	public ListPapers()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		papers = service.getPapers();
		if (papers != null)
			return SUCCESS;
		return ERROR;
	}
	public Collection<Paper> getPapers()
	{
		return papers;
	}
	public void setPapers(Collection<Paper> papers)
	{
		this.papers = papers;
	}
}
