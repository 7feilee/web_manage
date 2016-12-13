<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="web.action.ShowNoteDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title><%
    String paperid;
    Boolean isEdit;
    if(request.getParameter("paperid")==null)
    {
    	paperid = String.valueOf(((ShowNoteDetails) ActionContext.getContext().getValueStack().peek()).getNote().getPaper().getId());
    	isEdit = true;
    	out.print("编辑笔记");
    }
    else
    {
    	paperid = request.getParameter("paperid");
    	isEdit = false;
      out.print("添加笔记");
    }
    ActionContext.getContext().put("paperid",paperid);
  %>|文献管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/wangeditor/css/wangEditor.min.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/wangeditor/js/wangEditor.min.js"></script>
<script>
  $(document).ready(function () {
      var editor = new wangEditor("editor");
      editor.create();
  });
</script>
<%@include file="includes/header2.jsp" %>
<div class="well">
  <h2 class="text-center" style="margin-bottom: 20px"><%
    if(isEdit)
    	out.print("编辑");
    else
    	out.print("新增");
  %>笔记</h2>
  <s:form theme="bootstrap" action="addEditNote" cssClass="form-horizontal" id="validationForm">
    <s:textfield name="title" label="题目" labelCssClass="col-sm-1" elementCssClass="col-sm-11"
                 requiredLabel="true" value="%{note.title}"/>
    <s:textarea name="content" rows="15" elementCssClass="col-sm-12" id="editor" value="%{note.content}"/>
    <s:textfield name="paperid" cssClass="hidden" value='%{#paperid}' readonly="true"/>
    <s:textfield name="id" cssClass="hidden" value='%{note.id}' readonly="true"/>
    <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>