<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="web.action.ShowNoteDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>笔记:<s:property value="note.title"/>|文献管理系统</title>
<%@include file="includes/header2.jsp" %>
<div class="page-header" style="margin-bottom: 10px;">
  <span class="h3"><s:property value="note.title"/></span>
  <%
    int authorId = ((ShowNoteDetails) ActionContext.getContext().getValueStack().peek()).getNote().getAuthor().getId();
    if(userp!=null&&userp.getId() == authorId)
    {
  %>
  <div class="btn-group pull-right">
    <a class="btn btn-primary" href="<s:url action="editNote"><s:param name="id" value="%{note.id}"/></s:url>">
      <span class="glyphicon glyphicon-edit"></span> 编辑</a>
    <button data-toggle="modal" data-target="#myModal" class="btn btn-danger">
      <span class="glyphicon glyphicon-remove"></span> 删除
    </button>
  </div>
  <!-- 模态框（Modal） -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title" id="myModalLabel">您确定吗？</h4>
        </div>
        <div class="modal-body">确定要删除笔记：<s:property value="note.title"/>吗？该操作无法恢复！</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">我后悔了</button>
          <a type="button" class="btn btn-danger"
             href="<s:url action="deleteNote"><s:param name="id" value="%{note.id}"/></s:url>">确定删除</a>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
  <%}%>
</div>
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
  的笔记 &commat; <s:property value="dateStr"/>
</small>
<div class="content" style="margin-top: 40px">
  <s:property value="note.content" escapeHtml="false"/>
</div>
<%@ include file="includes/footer.jsp" %>