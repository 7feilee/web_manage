package web.action;
import model.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.util.zip.*;
import java.util.LinkedList;
import service.Service;
public class DownloadFile extends ActionSupport {
    private int label_id;
    private Service service;
}
