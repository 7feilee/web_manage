package model;
import java.io.Serializable;
import java.util.Collection;

/**
 * 用户的model，保存用户的相关信息
 */
public class User implements Serializable
{
	//个人信息
	private String name;
	private String email;
	private String bio;
	private String imgURI;
	private String blogURL;
	//账户信息
	private String username;
	private String password;
	private String token;
	private int id;
	//论文信息
	private Collection<Integer> toReadPapers;
	private Collection<Integer> readPapers;
	private Collection<Integer> studiedPapers;
	//阅读笔记
	private Collection<Integer> notes;
	//研究分类树
	private TagTree tagTree;
	private class TagTree
	{
		private class TagTreeNode
		{
			Collection<Paper> papers;
			Collection<TagTreeNode> chlids;
		}
		TagTreeNode root;
		//TODO: tag tree 
	}
	
	public TagTree getTagTree()
	{
		return tagTree;
	}
	public void setTagTree(TagTree tagTree)
	{
		this.tagTree = tagTree;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Collection<Integer> getToReadPapers()
	{
		return toReadPapers;
	}
	public void setToReadPapers(Collection<Integer> toReadPapers)
	{
		this.toReadPapers = toReadPapers;
	}
	public Collection<Integer> getReadPapers()
	{
		return readPapers;
	}
	public void setReadPapers(Collection<Integer> readPapers)
	{
		this.readPapers = readPapers;
	}
	public Collection<Integer> getStudiedPapers()
	{
		return studiedPapers;
	}
	public void setStudiedPapers(Collection<Integer> studiedPapers)
	{
		this.studiedPapers = studiedPapers;
	}
	public String getBio()
	{
		return bio;
	}
	public void setBio(String bio)
	{
		this.bio = bio;
	}
	public String getImgURI()
	{
		return imgURI;
	}
	public void setImgURI(String imgURI)
	{
		this.imgURI = imgURI;
	}
	public String getBlogURL()
	{
		return blogURL;
	}
	public void setBlogURL(String blogURL)
	{
		this.blogURL = blogURL;
	}
	public Collection<Integer> getNotes()
	{
		return notes;
	}
	public void setNotes(Collection<Integer> notes)
	{
		this.notes = notes;
	}
}
