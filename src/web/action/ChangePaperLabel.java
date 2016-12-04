package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.Service;

/**
 * Created by Jevons on 2016/11/30.
 */
public class ChangePaperLabel  extends ActionSupport{

    private String newlabelname;
    private int paper_id;
    private Service service;
    //private int user_id;
    //private int paper_id;

    public ChangePaperLabel()
    {
        super();
        service = new Service();
    }

    @Override
    public String execute() throws Exception {
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(obj == null)
            return ERROR;
        int uid = ((User) obj).getId();
        int res=service.updatePaperlabel(uid,paper_id,newlabelname);
        if(res > 0)
            return SUCCESS;
        else
            return ERROR;
    }



    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getNewlabelname() {
        return newlabelname;
    }

    public void setNewlabelname(String newlabelname) {
        this.newlabelname = newlabelname;
    }
}
