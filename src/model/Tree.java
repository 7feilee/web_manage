package model;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Tree {
    private Collection<Tree> childTree;
    private String labelname;
    private Collection<Paper> papers;

    public Collection<Tree> getChildTree() {
        return childTree;
    }

    public void setChildTree(Collection<Tree> childTree) {
        this.childTree = childTree;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }
}
