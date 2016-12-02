package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

public class AddTreeLabel extends ActionSupport
{
	private String labelname;
	//private int user_id;
	private String label_father;
	
	private Service service;
	
	public AddTreeLabel()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		if (labelname != null)
		{
			Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
			if (obj == null)
				return ERROR;
			//else
			int uid = ((User) obj).getId();
			int stat = service.addTreeLabel(labelname, label_father, uid);
			if (stat > 0)
				return SUCCESS;
			else
				return ERROR;
		}
		else
			return ERROR;
	}
	
	public String getLabelname()
	{
		return labelname;
	}
	
	public void setLabelname(String labelname)
	{
		this.labelname = labelname;
	}

    /*public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }*/
	
	public String getLabel_father()
	{
		return label_father;
	}
	
	public void setLabel_father(String label_father)
	{
		this.label_father = label_father;
	}
}


