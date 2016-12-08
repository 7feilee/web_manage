<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>笔记:<s:property value="note.title"/>|文献管理系统</title>
<%@include file="includes/header2.jsp" %>
<h2 class="page-header" style="margin-bottom: 10px;"><s:property value="note.title"/></h2>
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
  的笔记&commat;<s:property value="dateStr"/>
</small>
<div class="content" style="margin-top: 40px">
  <s:property value="note.content"/>
</div>
<%@ include file="includes/footer.jsp" %>