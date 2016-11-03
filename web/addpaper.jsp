<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>
<div class="well">
  <h2 class="text-center" style="margin-bottom: 20px">添加论文</h2>
<s:form theme="bootstrap" action="addPaper" cssClass="form-horizontal" id="validationForm">
  <s:textfield name="title" label="篇名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
  <s:textfield name="author" label="作者" placeholder="多个作者请使用|分隔" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
  <s:textfield name="keyword" label="关键字" placeholder="多个关键字请使用|分隔" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
  <s:textfield name="fileURI" label="来源链接" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
  <s:textfield name="dateStr" label="发表日期" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
  <s:textarea name="abstct" label="摘要" rows="5" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
  <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
</s:form>
</div>
<%@ include file="includes/footer.jsp" %>