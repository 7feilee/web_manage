<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="model.Log" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
<s:if test="%{notes.isEmpty()}">
  <h4 class="text-center">数据库中没有日志╮（╯＿╰）╭</h4>
</s:if>
<s:else>
  <table class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
      <th>时间</th>
      <th>事件</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="logs" var="log">
      <%
        Log log = (Log) ActionContext.getContext().get("log");
        log.hashCode();
        String event,time;
        if (log.getId() == 0 || log.getOperatorid() == 0 || log.getTarget() == 0 || log.getTargetid() == 0 || log.getType() == 0 || log.getTime() == null)
        {
        	event = "当前日志实例未完全初始化";
        	time = "NaT";
        }
        else
        {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          time = sdf.format(log.getTime());
          // TODO: 2016/12/2 处理event
        }
      %>
      <tr>
        <td>
          <%=time%>
        </td>
        <td>
          <%=event%>
        </td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</s:else>

<s:debug></s:debug>
<%@ include file="includes/footer.jsp" %>