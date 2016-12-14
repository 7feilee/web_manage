package web.action;
import model.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.util.zip.*;
import java.util.LinkedList;
import service.Service;
public class DownloadFile extends ActionSupport {

    private int paper_id;
    private Service service;
    private InputStream dfile;
    private String fileName;
    public String getFileName() throws UnsupportedEncodingException {
        Paper paper=service.getPaperById(paper_id);
        return new String((paper.getTitle()).concat(".zip").getBytes(), "ISO8859-1");
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }
    public String execute() {
        service = new Service();
        return SUCCESS;
    }

    public InputStream getDfile() throws UnsupportedEncodingException, FileNotFoundException {
        return service.getPaperFSbyid(paper_id);
    }
}
