package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
public class ShowUserTree extends ActionSupport{
    //private Tree tree;
    private Service service;
    private int user_id;
    private Collection<Tree> trees;
    private Collection<Paper> papers;
    //private String

    public ShowUserTree()
    {
        super();
        service = new Service();
        trees=null;
    }

    @Override
    public String execute() throws Exception
    {
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(obj == null)
            return ERROR;
        int uid = ((User) obj).getId();
        Tree tree=service.getUserTree(uid);
        trees = service.getUserTreeList(uid);
        papers=service.getLabelPapers(uid,"null");
        if(trees != null)
            return SUCCESS;
        else
            return ERROR;
    }
    /*
    public Tree getTree() {

        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }*/

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Collection<Tree> getTrees() {
        return trees;
    }

    public void setTrees(Collection<Tree> trees) {
        this.trees = trees;
    }

    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }
}
