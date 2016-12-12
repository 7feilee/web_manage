package web.model;

import model.Tree;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
public class JSONTree implements Serializable
{
	private String key;
	private String title;
	private JSONTree[] children;
	
	public JSONTree() {
		super();
	}
	
	private int depth;
	private List<Tree> treeList;
	public List<Tree> toTreeList()
	{
		depth = 0;
		treeList = new LinkedList<>();
		toTreeList(this);
		return treeList;
	}
	private void toTreeList(JSONTree t)
	{
		//访问当前节点
		Tree tree = new Tree();
		tree.setId(t.key.contains("_")?0:Integer.valueOf(t.key));
		tree.setLabelname(t.title);
		tree.setDepth(depth);
		treeList.add(tree);
		//访问子节点
		if(t.children==null)
			return;
		for (JSONTree c : t.children)
		{
			depth++;
			toTreeList(c);
			depth--;
		}
	}
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public JSONTree[] getChildren()
	{
		return children;
	}
	public void setChildren(JSONTree[] children)
	{
		this.children = children;
	}
}