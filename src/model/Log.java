package model;

import java.io.Serializable;
import java.util.Date;
public class Log implements Serializable
{
	//操作对象
	public static final int PAPER = 1;
	public static final int NOTE = 2;
	public static final int USER = 3;
	//操作类型
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int EDIT = 3;
	//对收藏的操作,这种操作定义为type=4|5|6|7，target=1
	public static final int NOTREAD = 4;
	public static final int TOREAD = 5;
	public static final int READ = 6;
	public static final int STUDIED = 7;
	public static final int UPDATETREELABLE = 8;
	
	private int id;
	private Date time;
	private Boolean isPrivate;
	private int target;
	private int targetid;
	private int type;
	private int operatorid;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
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
	public int getOperatorid()
	{
		return operatorid;
	}
	public void setOperatorid(int operatorid)
	{
		this.operatorid = operatorid;
	}
	public int getTargetid()
	{
		return targetid;
	}
	public void setTargetid(int targetid)
	{
		this.targetid = targetid;
	}
}
