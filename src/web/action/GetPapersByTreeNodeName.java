package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import service.Service;

import java.util.Collection;
public class GetPapersByTreeNodeName extends ActionSupport
{
	private Collection<Paper> papers;
	private String nodeName;
	private Service service;
	public GetPapersByTreeNodeName()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}
	/*public String getNodeName()
	{
		return nodeName;
	}*/
	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}
	public Collection<Paper> getPapers()
	{
		return papers;
	}
	public void setPapers(Collection<Paper> papers)
	{
		this.papers = papers;
	}
	public String getNodeName()
	{
		return nodeName;
	}
}
