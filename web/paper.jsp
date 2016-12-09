<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>论文:<s:property value="paper.title"/>|文献管理系统</title>
<%@include file="includes/header2.jsp" %>

<div class="page-header">
  <span class="h3"><s:property value="paper.title"/></span>
  <%
    if (userp != null)
    {
  %>
  <a class="btn btn-primary pull-right"><span class="glyphicon glyphicon-edit"></span> 编辑</a>
  <%}%>
</div>
</div>
<div class="row">
  <div class="col-md-4" style="padding: 0 5px">
    <div class="well">
      <h5 class="page-header" style="margin-top: 20px">作者</h5>
      <p class="lead">
        <s:iterator value="paper.authors"><s:property/>;</s:iterator>
      <h5 class="page-header">关键字</h5>
      <p class="lead"><s:iterator value="paper.keywords"><s:property/>;</s:iterator></p>
      <a href="<s:property value="paper.fileURI"/>" class="btn btn-block btn-hg btn-primary">
        <span class="glyphicon glyphicon-download>"></span> 下载</a>
    </div>
  </div>
  <div class="col-md-8" style="padding: 0 5px">
    <div class="well">
      <h5 class="page-header" style="margin-top: 20px">摘要</h5>
      <p class="lead"><s:property value="paper.abstct"/></p>
    </div>
  </div>
</div>
<div class="row">
  <div class="panel panel-primary" style="margin-top: 30px">
    <div class="panel-heading">
      <h3 class="panel-title">大家的笔记</h3>
    </div>
    <div class="panel-body">
      <%if (userp != null) {%>
      <div class="col-md-3 col-md-offset-9" style="margin-bottom: 15px">
        <a href="editnote.jsp?paperid=${requestScope.get("id")}" class="btn btn-primary btn-block btn-hg">
          <span class="glyphicon glyphicon-edit"></span>&nbsp;写笔记
        </a>
      </div>
      <%}%>
      <s:if test="%{notes.isEmpty()}">
        <h4 class="text-center">并没有用户留下笔记╮（╯＿╰）╭</h4>
      </s:if>
      <s:else>
        <table class="table table-bordered table-striped table-hover">
          <thead>
          <tr>
            <th width="50%">题目</th>
            <th width="30%">作者</th>
            <th width="20%">发表时间</th>
          </tr>
          </thead>
          <tbody>
          <s:iterator value="notes">
            <tr>
              <td>
                <a href='<s:url action="showNoteDetails"><s:param name="id" value="id" /></s:url>'>
                  <s:property value="%{title}"/>
                </a>
              </td>
              <td>
                <a href="<s:url action="showUserDetails">
                      <s:param name="id"><s:property value="author.id"/></s:param>
                   </s:url>">
                  <s:property value="author.name==null?author.username:author.name"/>
                </a>
              </td>
              <td><s:property value="%{publishTime}"/></td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </s:else>
    </div>
  </div>
<%@ include file="includes/footer.jsp" %>
