package model;
import java.io.Serializable;
import java.util.Collection;

public class Tree implements Serializable
{
	private Collection<Tree> childTree;
	private String label_father;
	private String labelname;
	private Collection<Paper> papers;
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel_father() {
		return label_father;
	}

	public void setLabel_father(String label_father) {
		this.label_father = label_father;
	}


}
