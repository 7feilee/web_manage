package web.model;

import java.io.Serializable;
public class FrontLog implements Serializable
{
	private String time;
	private String event;
	
	public String getEvent()
	{
		return event;
	}
	public void setEvent(String event)
	{
		this.event = event;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
}
