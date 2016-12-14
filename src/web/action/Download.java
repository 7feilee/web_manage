package web.action;
import model.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.util.zip.*;
import java.util.LinkedList;
import service.Service;

public class Download extends ActionSupport {
    private int label_id;
    private Service service;
    private String tmpFileName;
    private String tpath;
    private static final String FilePath = "D:\\";
    private InputStream dfile;
    private String fileName;
    //private InputStream in;

    public InputStream getIn() {
        try {
            ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;fileName="
                    + java.net.URLEncoder.encode(tmpFileName, "UTF-8"));
            InputStream in = ServletActionContext.getServletContext().getResourceAsStream(tmpFileName);
            return in;
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }

    /*public void setIn(FileInputStream in) {
        this.in = in;
    }*/

    public String getTpath() {
        return tpath;
    }

    public void setTpath(String tpath) {
        this.tpath = tpath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLabel_id() {
        return label_id;
    }
    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }


    public InputStream getDfile(){
        try {
            ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName="
                + java.net.URLEncoder.encode(tmpFileName, "UTF-8"));

            File file = new File(FilePath + tmpFileName);//ServletActionContext.getServletContext().getResourceAsStream(file);
            FileInputStream in = null;
            if (file.exists()) {
                //ServletActionContext.getServletContext().getResourceAsStream(file);
                in = new FileInputStream(file);
            }
            return in;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }

    public String execute() throws Exception{
        service=new Service();
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(obj == null)
            return null;
        int uid = ((User) obj).getId();
        String labelname=service.getTreeByid(label_id).getLabelname();
        //生成的ZIP文件名
        tmpFileName = labelname.concat(".zip");
        byte[] buffer = new byte[1024];
        //String strZipPath = FilePath + tmpFileName;
        String strZipPath = FilePath+tmpFileName;
        try {
            FileOutputStream fos=new FileOutputStream(strZipPath);
            ZipOutputStream out = new ZipOutputStream(fos);
            // 需要同时下载的文件
            LinkedList<Paper> papers=service.getLabelAndChildrenPapers(uid,labelname);
            int slen=papers.size();
            for (int i = 0; i < slen; i++) {
                InputStream fis = service.getPaperFSbyid(papers.get(i).getId());
                out.putNextEntry(new ZipEntry(papers.get(i).getTitle().concat(".pdf")));
                //设置压缩文件内的字符编码，不然会变成乱码
                //out.setEncoding("GBK");
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                if (fis!=null){
                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}