package web.action;
import model.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.util.Collection;
import java.util.zip.*;
import java.util.LinkedList;
import service.Service;

public class Download extends ActionSupport {
    private int label_id;
    private int user_id;
    private Service service;
    private String tmpFileName;
    private InputStream dfile;
    private String fileName;

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getTmpFileName() {
        return tmpFileName;
    }
    public void setTmpFileName(String tmpFileName) {
        this.tmpFileName = tmpFileName;
    }
    public String getFileName() throws UnsupportedEncodingException {
        return new String(tmpFileName.getBytes(), "ISO8859-1");
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
    public InputStream getDfile() throws UnsupportedEncodingException, FileNotFoundException {
        return ServletActionContext.getServletContext().getResourceAsStream("/zip/".concat(tmpFileName));
    }
    public String execute(){
        service=new Service();
        String labelname=service.getTreeByid(label_id).getLabelname();
        //生成的ZIP文件名
        tmpFileName = labelname.concat(".zip");
        byte[] buffer = new byte[1024];
        //String strZipPath = FilePath + tmpFileName;
        String realPath=ServletActionContext.getServletContext().getRealPath("/zip/");
        String strZipPath = realPath+tmpFileName;
        try {
            FileOutputStream fos=new FileOutputStream(strZipPath);
            ZipOutputStream out = new ZipOutputStream(fos);
            // 整理要下载的文件

            LinkedList<Tree> treelist=service.getUserLabelTreeList(user_id,labelname);
            Collection<Paper> papers=service.getLabelPapers(user_id,labelname);
            for (Paper paper : papers) {
                InputStream fis = service.getPaperFSbyid(paper.getId());
                out.putNextEntry(new ZipEntry(labelname.concat("/").concat(paper.getTitle()).concat(".pdf")));
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

            int length=treelist.size();
            String labels[]=new String[length];
            for (int i=0;i<length;i++){
                labels[i]="/"+treelist.get(i).getLabelname();
            }
            for (int i=0;i<length;i++){
                for (int j=i;j<length;j++){
                    if (treelist.get(j).getLabel_father().equals(treelist.get(i).getLabelname()))
                        labels[j]=labels[i]+labels[j];
                }
            }
            for (int i=0;i<length;i++){
                labels[i]+="/";
            }

            for (int i=0;i<length;i++){
                Collection<Paper> papers2=service.getLabelPapers(user_id,treelist.get(i).getLabelname());
                for (Paper paper : papers2) {
                    InputStream fis = service.getPaperFSbyid(paper.getId());
                    out.putNextEntry(new ZipEntry(labelname.concat(labels[i]).concat(paper.getTitle()).concat(".pdf")));
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
            }
            out.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}