package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class AddEditPaper extends ActionSupport
{
	private String title;
	private String author;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String keyword;
	private String abstct;
	private String dateStr;
	private int id;
	
	private Collection<String> authors;
	private Collection<String> keywords;
	private Date publishDate;
	
	private Service service;
	
	public AddEditPaper()
	{
		super();
		service = new Service();
	}
	@Override
	public String execute() throws Exception
	{
		if (title != null && !title.matches("\\s*"))
		{
			if (author != null && !title.matches("\\s*"))
				authors = Arrays.asList(author.split(";"));
			if (keyword != null && !title.matches("\\s*"))
				keywords = Arrays.asList(keyword.split(";"));
			if (dateStr != null && dateStr.matches("\\d{4}-\\d{2}-\\d{2}"))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				publishDate = sdf.parse(dateStr);
			}
			Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
			if (obj == null)
				return ERROR;
			int uid = ((User) obj).getId();
			String fileURI = file==null?null:"true";
			int result;
			if (id == 0)
				result = service.addPaper(title, authors, fileURI, keywords, abstct, publishDate,file ,uid);
			else
				result = service.editPaper(id, title, authors, fileURI, keywords, abstct, publishDate,file, uid);
			if (result > 0)
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
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public File getFile()
	{
		return file;
	}
	public void setFile(File file)
	{
		this.file = file;
	}
	public String getFileFileName()
	{
		return fileFileName;
	}
	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}
	public String getFileContentType()
	{
		return fileContentType;
	}
	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}
}
