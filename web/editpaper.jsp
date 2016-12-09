<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="web.action.ShowPaperDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title><%
  String id;
  Boolean isEdit;
  if(request.getParameter("id")==null)
  {
  	id = "0";
    isEdit = false;
    out.print("添加论文");
  }
  else
  {
    id = String.valueOf(((ShowPaperDetails) ActionContext.getContext().getValueStack().peek()).getId());
    isEdit = true;
    out.print("编辑论文");
  }
  ActionContext.getContext().put("id",id);
%>|文献管理系统</title>
<%@include file="includes/header2.jsp" %>
<div class="well">
  <h2 class="text-center" style="margin-bottom: 20px"><%
    if(isEdit)
      out.print("编辑");
    else
      out.print("添加");
  %>论文</h2>
  <s:form theme="bootstrap" action="addEditPaper" cssClass="form-horizontal" id="validationForm">
    <s:textfield name="title" label="篇名" labelCssClass="col-sm-1" elementCssClass="col-sm-11"
                 requiredLabel="true" value="%{paper.title}"/>
    <s:textfield name="author" label="作者" placeholder="多个作者请使用;分隔" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{authors}"/>
    <s:textfield name="keyword" label="关键字" placeholder="多个关键字请使用;分隔" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{keywords}"/>
    <s:textfield name="fileURI" label="来源链接" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{paper.fileURI}"/>
    <s:textfield name="dateStr" label="发表日期" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{dateStr}"/>
    <s:textarea name="abstct" label="摘要" rows="5" labelCssClass="col-sm-1"
                elementCssClass="col-sm-11" value="%{paper.abstct}"/>
    <s:textfield name="id" cssClass="" value="%{#id}"/>
    <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>