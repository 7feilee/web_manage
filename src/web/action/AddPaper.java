package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class AddPaper extends ActionSupport
{
	private String title;
	private String author;
	private String fileURI;
	private String keyword;
	private String abstct;
	private String dateStr;
	
	private Collection<String> authors;
	private Collection<String> keywords;
	private Date publishDate;
	
	private Service service;
	
	public AddPaper()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		if(title!=null)
		{
			if(author !=null)
				authors = Arrays.asList(author.split("\\|"));
			if(keyword != null)
				keywords = Arrays.asList(keyword.split("\\|"));
			if(dateStr!= null && dateStr.matches("\\d{4}-\\d{2}-\\d{2}"))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				publishDate = sdf.parse(dateStr);
			}
			Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
			if(obj == null)
				return ERROR;
			//else
			int uid = ((User) obj).getId();
			int stat = service.addPaper(title,authors,fileURI,keywords,abstct,publishDate,uid);
			if(stat > 0)
				return SUCCESS;
			else
				return ERROR;
		}
		else
			return ERROR;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getFileURI()
	{
		return fileURI;
	}
	public void setFileURI(String fileURI)
	{
		this.fileURI = fileURI;
	}
	public String getKeyword()
	{
		return keyword;
	}
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	public String getAbstct()
	{
		return abstct;
	}
	public void setAbstct(String abstct)
	{
		this.abstct = abstct;
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
