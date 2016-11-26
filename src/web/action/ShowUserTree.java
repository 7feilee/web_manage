package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Tree;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
public class ShowUserTree extends ActionSupport{
    private Tree tree;
    private Service service;
    private int user_id;
    //private String

    public ShowUserTree()
    {
        super();
        service = new Service();
        tree=null;
    }

    @Override
    public String execute() throws Exception
    {
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(obj == null)
            return ERROR;
        int uid = ((User) obj).getId();
        tree = service.getUserTree(uid);
        if(tree != null)
            return SUCCESS;
        else
            return ERROR;
    }
    public Tree getTree() {

        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
