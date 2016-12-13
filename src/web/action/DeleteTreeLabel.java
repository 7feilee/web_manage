package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.Service;

public class DeleteTreeLabel extends ActionSupport{
    private String labelname;
    private Service service;
    //private int user_id;
    //private int paper_id;

    public DeleteTreeLabel()
    {
        super();
        service = new Service();
    }

    @Override
    public String execute() throws Exception
    {
        User user = ((User) ServletActionContext.getRequest().getSession().getAttribute("user"));
        if(user == null)
            return ERROR;
        int res = service.deleteTreeLabel(user.getId(),labelname);
        if(res > 0)
            return SUCCESS;
        else
            return ERROR;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }
}
