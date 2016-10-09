<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%@ include file="includes/header.jsp" %>
<div class="col-md-4 col-md-offset-4 well" style="margin-top: 80px;">
  <s:form action="login" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal"
          label="用户登录">
    <s:textfield
        label="用户名"
        name="username"
        cssClass="input-sm"/>
    
    <s:password
        label="密码"
        name="password"
        cssClass="input-sm"/>
    <s:submit value="登录" cssClass="btn btn-primary btn-lg btn-block"/>
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>