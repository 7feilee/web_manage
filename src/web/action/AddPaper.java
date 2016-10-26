package web.action;
import com.opensymphony.xwork2.ActionSupport;

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
	@Override
	public String execute() throws Exception
	{
		if(title!=null)
		{
			if(author !=null)
				authors = Arrays.asList(author.split("\\|"));
			if(keyword != null)
				keywords = Arrays.asList(keyword.split("\\|"));
			if(dateStr!= null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				publishDate = sdf.parse(dateStr);
			}
			//调用service
			return SUCCESS;
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
