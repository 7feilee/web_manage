package model;
import java.io.Serializable;
import java.util.Collection;

public class Tree implements Serializable
{
	private Collection<Tree> childTree;
	private String labelname;
	private Collection<Paper> papers;
	
	private int depth;
	
	public Collection<Tree> getChildTree()
	{
		return childTree;
	}
	
	public void setChildTree(Collection<Tree> childTree)
	{
		this.childTree = childTree;
	}
	
	public String getLabelname()
	{
		return labelname;
	}
	
	public void setLabelname(String labelname)
	{
		this.labelname = labelname;
	}
	
	public Collection<Paper> getPapers()
	{
		return papers;
	}
	
	public void setPapers(Collection<Paper> papers)
	{
		this.papers = papers;
	}
	
	public int getDepth()
	{
		return depth;
	}
	
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
}
