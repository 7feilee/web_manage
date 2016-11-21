<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>

<h2 class="page-header text-center"><s:property value="paper.title"/></h2>

<h5 class="text-center"><s:iterator value="paper.authors"><s:property/>&nbsp;</s:iterator></h5>
<h5 class="page-header">摘要</h5>
<p class="lead"><s:property value="paper.abstct"/></p>
<h5 class="page-header">关键字</h5>
<p class="lead"><s:iterator value="paper.keywords"><s:property/>&nbsp;</s:iterator></p>
<h5 class="page-header">来源链接</h5>
<a href="<s:property value="paper.fileURI"/>"><s:property value="paper.fileURI"/></a>

<%if (userp != null) {%>
<div class="col-md-3 col-md-offset-9">
  <a href="addnote.jsp?paperid=${requestScope.get("id")}" class="btn btn-primary btn-block btn-hg">
    <span class="glyphicon glyphicon-edit"></span>&nbsp;写笔记
  </a>
</div>
<%}%>

<%@ include file="includes/footer.jsp" %>
