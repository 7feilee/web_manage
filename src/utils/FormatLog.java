package utils;

import model.Log;
import model.Note;
import model.Paper;
import model.User;
import service.Service;
import web.model.FrontLog;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
public class FormatLog
{
	public static Collection<FrontLog> formatLogs(Collection<Log> logs)
	{
		if (logs != null)
		{
			Collection<FrontLog> results = new LinkedList<>();
			Log[] logs1 = logs.toArray(new Log[0]);
			for (int i = logs.size() - 1; i >= 0; --i)
			{
				Log log = logs1[i];
				results.add(formatLog(log));
			}
			return results;
		}
		return null;
	}
	
	public static FrontLog formatLog(Log log)
	{
		FrontLog result = new FrontLog();
		Service service = new Service();
		if (log.getId() == 0 || log.getOperatorid() == 0 || log.getTarget() == 0
				|| log.getTargetid() == 0 || log.getType() == 0 || log.getTime() == null)
		{
			result.setEvent("当前日志实例未完全初始化");
			result.setTime("NaT");
			return result;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result.setTime(sdf.format(log.getTime()));
		if (log.getTarget() == Log.PAPER)
		{
			if (log.getType() == Log.ADD)
			{//新增论文
				User operator = service.getUserById(log.getOperatorid());
				Paper paper = service.getPaperById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"添加了论文：<a href='/showPaperDetails.action?id=" + paper.getId() + "'>"
						+ paper.getTitle() + "</a>";
				result.setEvent(event);
			}
			else if (log.getType() == Log.TOREAD)
			{//计划读论文
				User operator = service.getUserById(log.getOperatorid());
				Paper paper = service.getPaperById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"计划读论文：<a href='/showPaperDetails.action?id=" + paper.getId() + "'>"
						+ paper.getTitle() + "</a>";
				result.setEvent(event);
			}
			else if (log.getType() == Log.READ)
			{//粗读论文
				User operator = service.getUserById(log.getOperatorid());
				Paper paper = service.getPaperById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"已粗读论文：<a href='/showPaperDetails.action?id=" + paper.getId() + "'>"
						+ paper.getTitle() + "</a>";
				result.setEvent(event);
			}
			else if (log.getType() == Log.STUDIED)
			{//精读论文
				User operator = service.getUserById(log.getOperatorid());
				Paper paper = service.getPaperById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"已精读论文：<a href='/showPaperDetails.action?id=" + paper.getId() + "'>"
						+ paper.getTitle() + "</a>";
				result.setEvent(event);
			}
			else if (log.getType() == Log.UPDATETREELABLE)
			{//更新分类树
				User operator = service.getUserById(log.getOperatorid());
				Paper paper = service.getPaperById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"更新了论文：<a href='/showPaperDetails.action?id=" + paper.getId() + "'>"
						+ paper.getTitle() + "</a>的分类";
				result.setEvent(event);
			}
			// TODO: 2016/12/2 修改和删除论文
		}
		else if(log.getTarget()==Log.NOTE)
		{
			if (log.getType()==Log.ADD)
			{//新增笔记
				User operator = service.getUserById(log.getOperatorid());
				Note note = service.getNoteById(log.getTargetid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>" +
						"新增了笔记：<a href='/showNoteDetails.action?id=" + note.getId() + "'>"
						+ note.getTitle() + "</a>";
				result.setEvent(event);
			}
			// TODO: 2016/12/2 修改和删除笔记
		}
		else if (log.getTarget()==Log.USER)
		{
			if (log.getType()==Log.ADD)
			{//用户注册
				User operator = service.getUserById(log.getOperatorid());
				String event = "<a href='/showUserDetails.action?id=" + operator.getId() + "'>"
						+ operator.getUsername() + "</a>注册成为本系统第" + operator.getId() + "位会员";
				result.setEvent(event);
			}
			// TODO: 2016/12/2 用户资料修改
		}
		return result;
	}
}
