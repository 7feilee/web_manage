package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Download extends ActionSupport
{
	private int label_id;
	private Service service;
	private String tmpFileName;
	private String errMsg;
	private InputStream dfile;
	private String fileName;
	
	public String getTmpFileName()
	{
		return tmpFileName;
	}
	public void setTmpFileName(String tmpFileName)
	{
		this.tmpFileName = tmpFileName;
	}
	public String getFileName() throws UnsupportedEncodingException
	{
		return new String(tmpFileName.getBytes(), "ISO8859-1");
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public int getLabel_id()
	{
		return label_id;
	}
	public void setLabel_id(int label_id)
	{
		this.label_id = label_id;
	}
	public InputStream getDfile() throws UnsupportedEncodingException, FileNotFoundException
	{
		return ServletActionContext.getServletContext().getResourceAsStream("/zip/".concat(tmpFileName));
	}
	public String execute()
	{
		service = new Service();
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (obj == null)
			return null;
		int uid = ((User) obj).getId();
		String labelname = service.getTreeByid(label_id).getLabelname();
		//生成的ZIP文件名
		tmpFileName = labelname.concat(".zip");
		byte[] buffer = new byte[1024];
		//String strZipPath = FilePath + tmpFileName;
		String realPath = ServletActionContext.getServletContext().getRealPath("/zip/");
		String strZipPath = realPath + tmpFileName;
		try
		{
			FileOutputStream fos = new FileOutputStream(strZipPath);
			ZipOutputStream out = new ZipOutputStream(fos);
			// 需要同时下载的文件
			LinkedList<Paper> papers = service.getLabelAndChildrenPapers(uid, labelname);
			int slen = papers.size();
			for (int i = 0; i < slen; i++)
			{
				InputStream fis = service.getPaperFSbyid(papers.get(i).getId());
				out.putNextEntry(new ZipEntry(papers.get(i).getTitle().concat(".pdf")));
				//设置压缩文件内的字符编码，不然会变成乱码
				//out.setEncoding("GBK");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				if (fis != null)
				{
					while ((len = fis.read(buffer)) > 0)
					{
						out.write(buffer, 0, len);
					}
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getErrMsg()
	{
		return errMsg;
	}
	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
}