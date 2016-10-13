package model;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
/**
 * 文献的model，保存文献的成员变量和方法
 */
public class Paper implements Serializable
{
	private int id;
	
	private String title;
	private Collection<String> authors;
	private String fileURI;
	private Collection<String> keywords;
	private String absrtct;
	private Date publishDate;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public Collection<String> getAuthors()
	{
		return authors;
	}
	public void setAuthors(Collection<String> authors)
	{
		this.authors = authors;
	}
	public String getFileURI()
	{
		return fileURI;
	}
	public void setFileURI(String fileURI)
	{
		this.fileURI = fileURI;
	}
	public Collection<String> getKeywords()
	{
		return keywords;
	}
	public void setKeywords(Collection<String> keywords)
	{
		this.keywords = keywords;
	}
	public String getAbsrtct()
	{
		return absrtct;
	}
	public void setAbsrtct(String absrtct)
	{
		this.absrtct = absrtct;
	}
	public Date getPublishDate()
	{
		return publishDate;
	}
	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}
}
