package model;
import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable
{
	private int id;
	private User author;
	private Paper paper;
	private Date publishTime;
	private String title;
	private String content;
	private Boolean isPrivate;
	
	
	public Date getPublishTime()
	{
		return publishTime;
	}
	public void setPublishTime(Date pubiishTime)
	{
		this.publishTime = pubiishTime;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Boolean getPrivate()
	{
		return isPrivate;
	}
	public void setPrivate(Boolean aPrivate)
	{
		isPrivate = aPrivate;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public User getAuthor()
	{
		return author;
	}
	public void setAuthor(User author)
	{
		this.author = author;
	}
	public Paper getPaper()
	{
		return paper;
	}
	public void setPaper(Paper paper)
	{
		this.paper = paper;
	}
}
