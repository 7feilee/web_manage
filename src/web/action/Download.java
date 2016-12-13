package web.action;


import model.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.zip.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletResponse;
//import org.apache.tools.zip.ZipOutputStream;
//import org.apache.tools.zip.ZipEntry;
import service.Service;

/**
 * 批量下载文件：
 *   使用ant.jar包中的org.apache.tools.zip.*完成压缩，
 * java原生也有java.util.zip.*但是测试了下无法搞定压缩
 * 文件内文件名的中文问题
 * @author yangcong
 *
 */
public class Download extends ActionSupport {
    private String labelname;
    private int label_id;
    private Service service=new Service();
    private static final String FilePath = "D:\\";
    private static final long serialVersionUID = -8694640030455344419L;
    private String tmpFileName;
    public String getLabelname() {
        return labelname;
    }
    public int getLabel_id() {
        return label_id;
    }
    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }
    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }


    public String execute() {
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        if(obj == null)
            return ERROR;
        //else
        int uid = ((User) obj).getId();
        labelname=service.getTreeByid(label_id).getLabelname();
        //生成的ZIP文件名
        tmpFileName = labelname.concat(".zip");
        byte[] buffer = new byte[1024];
        String strZipPath = FilePath + tmpFileName;
//        File path=new File(FilePath);
//        File dir=new File(path,tmpFileName);
//        if(!dir.exists()){
//            try {
//                dir.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
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
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
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

    public InputStream getDownloadFile() throws FileNotFoundException, UnsupportedEncodingException {
        // 如果下载文件名为中文，进行字符编码转换
        ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName="
                                + java.net.URLEncoder.encode(tmpFileName, "UTF-8"));
        return ServletActionContext.getServletContext().getResourceAsStream(FilePath.concat(tmpFileName)) ;
    }
}