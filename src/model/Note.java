package model;
import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable
{
	private int id;
	private int author;
	private int paper;
	private Date pubiishTime;
	private String title;
	private String content;
	private Boolean isPrivate;
	
	
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
	public int getAuthor()
	{
		return author;
	}
	public void setAuthor(int author)
	{
		this.author = author;
	}
	public int getPaper()
	{
		return paper;
	}
	public void setPaper(int paper)
	{
		this.paper = paper;
	}
}
