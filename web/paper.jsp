<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>
<div class="col-md-9">
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
</div>
<div class="col-md-3">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h3 class="panel-title">大家的公开笔记</h3>
    </div>
    <div class="panel-body">
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
</div>
<%@ include file="includes/footer.jsp" %>
