package model;
import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable
{
	private int id;
	private User author;
	private Date pubiishTime;
	private String title;
	private String content;
	private Boolean isPrivate;
	
	public User getAuthor()
	{
		return author;
	}
	public void setAuthor(User author)
	{
		this.author = author;
	}
	public Date getPubiishTime()
	{
		return pubiishTime;
	}
	public void setPubiishTime(Date pubiishTime)
	{
		this.pubiishTime = pubiishTime;
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
}
