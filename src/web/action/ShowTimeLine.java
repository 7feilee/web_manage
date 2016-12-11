package web.action;
import com.opensymphony.xwork2.ActionSupport;
import model.Log;
import model.Paper;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.Service;
import web.model.FrontLog;

import java.text.SimpleDateFormat;
import java.util.*;

public class ShowTimeLine extends ActionSupport
{
	private Service service;
	private Collection<FrontLog> logs;
	public ShowTimeLine()
	{
		super();
		service = new Service();
	}
	
	@Override
	public String execute() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null)
			return ERROR;
		Collection<Log> logs1 = service.getLogsByUser(user.getId());
		if (logs1 == null)
			return ERROR;
		logs = toTimeLine(logs1);
		return SUCCESS;
	}
	private Collection<FrontLog> toTimeLine(Collection<Log> logs)
	{
		
		class TimeLineContent{
			public Set<Integer> toRead;
			public Set<Integer> read;
			public Set<Integer> studied;
			TimeLineContent()
			{
				toRead = new HashSet<>();
				read = new HashSet<>();
				studied = new HashSet<>();
			}
		}
		Collection<FrontLog> result = new LinkedList<>();
		Map<String, TimeLineContent> timeline = new LinkedHashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Log log : logs)
		{
			if (log.getId() == 0 || log.getOperatorid() == 0 || log.getTarget() == 0 || log.getTargetid() == 0 || log.getType() == 0 || log.getTime() == null)
				timeline.put(null, null);
			if (log.getTarget() == Log.PAPER && (log.getType()==Log.TOREAD||log.getType()==Log.READ||log.getType()==Log.STUDIED))
			{
				String time = sdf.format(log.getTime());
				TimeLineContent timeLineContent = timeline.containsKey(time) ? timeline.get(time) : new TimeLineContent();
				if (log.getType()==Log.TOREAD)
					timeLineContent.toRead.add(log.getTargetid());
				else if(log.getType()==Log.READ)
					timeLineContent.read.add(log.getTargetid());
				else if (log.getType()==Log.STUDIED)
					timeLineContent.studied.add(log.getTargetid());
				timeline.put(time,timeLineContent);
			}
		}
		ListIterator<Map.Entry<String,TimeLineContent>> i=new ArrayList<Map.Entry<String,TimeLineContent>>(timeline.entrySet()).listIterator(timeline.size());
		while(i.hasPrevious())
		{
			Map.Entry<String, TimeLineContent> entry=i.previous();
			FrontLog frontLog = new FrontLog();
			frontLog.setTime(entry.getKey());
			TimeLineContent timeLineContent = entry.getValue();
			StringBuilder event = new StringBuilder();
			event.append("<tr>\n");
			if(!timeLineContent.toRead.isEmpty())
			{
				event.append("<th class='danger'>【计划读】</th>\n<td>");
				for (int paperid:timeLineContent.toRead)
				{
					Paper paper = service.getPaperById(paperid);
					event.append("<a href='/showPaperDetails.action?id=").append(paperid).append("'>").append(paper.getTitle()).append("</a>, ");
				}
				event.append("</td>\n</tr>");
			}
			if(!timeLineContent.read.isEmpty())
			{
				event.append("<th class='info'>【已粗读】</th>\n<td>");
				for (int paperid:timeLineContent.read)
				{
					Paper paper = service.getPaperById(paperid);
					event.append("<a href='/showPaperDetails.action?id=").append(paperid).append("'>").append(paper.getTitle()).append("</a>, ");
				}
				event.append("</td>\n</tr>");
			}
			if(!timeLineContent.studied.isEmpty())
			{
				event.append("<th class='success'>【已精读】</th>\n<td>");
				for (int paperid:timeLineContent.studied)
				{
					Paper paper = service.getPaperById(paperid);
					event.append("<a href='/showPaperDetails.action?id=").append(paperid).append("'>").append(paper.getTitle()).append("</a>, ");
				}
				event.append("</td>\n</tr>");
			}
			frontLog.setEvent(String.valueOf(event));
			result.add(frontLog);
		}
		return result;
	}
	public Collection<FrontLog> getLogs()
	{
		return logs;
	}
//	public void setLogs(Collection<FrontLog> logs)
//	{
//		this.logs = logs;
//	}
}
