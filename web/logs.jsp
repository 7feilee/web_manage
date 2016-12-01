<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
<%--
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
      <tr>
        <td>
            <s:property value="%{time}"/>
        </td>
        <td>
          //事件
        </td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</s:else>
--%>
<s:debug></s:debug>
<%@ include file="includes/footer.jsp" %>