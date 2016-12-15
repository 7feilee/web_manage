package web.action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import service.Service;

import java.util.Collection;
public class GetPapersByTreeNodeName extends ActionSupport
{
	private String errMsg;
	private Collection<Paper> papers;
	private String nodeName;
	private Service service;
	private int uid;
	public GetPapersByTreeNodeName()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		papers = service.getLabelPapers(uid, nodeName);
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
	public String getNodeName()
	{
		return nodeName;
	}
	/*public String getNodeName()
	{
		return nodeName;
	}*/
	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}
	public int getUid()
	{
		return uid;
	}
	public void setUid(int uid)
	{
		this.uid = uid;
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
