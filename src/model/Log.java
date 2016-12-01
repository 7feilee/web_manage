package model;

import java.io.Serializable;
import java.util.Date;
public class Log implements Serializable
{
	//操作对象
	public static final int PAPER = 1;
	public static final int NOTE = 2;
	public static final int USER = 3;
	public static final int TOREAD = 4;
	public static final int READ = 5;
	public static final int STUDIED = 6;
	//操作类型
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int EDIT = 3;
	
	private Date time;
	private Boolean isPrivate;
	private int target;
	private int type;
	private int operator;
	
	
	public Date getTime()
	{
		return time;
	}
	public void setTime(Date time)
	{
		this.time = time;
	}
	public Boolean getPrivate()
	{
		return isPrivate;
	}
	public void setPrivate(Boolean aPrivate)
	{
		isPrivate = aPrivate;
	}
	public int getTarget()
	{
		return target;
	}
	public void setTarget(int target)
	{
		this.target = target;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getOperator()
	{
		return operator;
	}
	public void setOperator(int operator)
	{
		this.operator = operator;
	}
}
