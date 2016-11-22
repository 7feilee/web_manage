<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>
<div class="well">
  <h2 class="text-center" style="margin-bottom: 20px">写笔记</h2>
  <s:form theme="bootstrap" action="addNote" cssClass="form-horizontal" id="validationForm">
    <s:textfield name="title" label="题目" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
    <s:textarea name="content" label="内容" rows="15" labelCssClass="col-sm-1" elementCssClass="col-sm-11"/>
    <s:textfield name="paperid" cssClass="hidden"/>
    <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>