<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>
<h4 class="page-header"><s:property value="note.title"/></h4>
<small class="text-right">
  <a href="<s:url action="showUserDetails">
                <s:param name="id"><s:property value="note.author.id"/></s:param>
           </s:url>">
      <s:property value="%{note.author.name==null?note.author.username:note.author.name}"/>
  </a>
  &nbsp;对
  <a href="<s:url action="showPaperDetails">
                <s:param name="id"><s:property value="note.paper.id"/></s:param>
           </s:url>">
    <s:property value="%{note.paper.title}"/>
  </a>
  的笔记&nbsp;<s:property value="dateStr"/>
</small>
<div class="content">
  <s:property value="note.content"/>
</div>
<%@ include file="includes/footer.jsp" %>