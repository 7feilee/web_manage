<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp"%>
<table class="table table-bordered table-striped table-hover">
  <thead>
  <tr>
    <th width="25%">属性</th>
    <th>值</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>篇名</td>
    <td><s:property value="paper.title"/></td>
  </tr>
  <tr>
    <td>作者</td>
    <td><s:iterator value="paper.authors">
      <s:property/>
    </s:iterator></td>
  </tr>
  <tr>
    <td>发表日期</td>
    <td><s:property value="dateStr"/></td>
  </tr>
  <tr>
    <td>关键字</td>
    <td><s:property value="paper.keywords"/></td>
  </tr>
  <tr>
    <td>来源链接</td>
    <td><s:property value="paper.fileURI"/></td>
  </tr>
  </tbody>
</table>
<h5 class="page-header">摘要</h5>
<blockquote>
  <p><s:property value="paper.abstrct"/> </p>
</blockquote>
<%@ include file="includes/footer.jsp"%>