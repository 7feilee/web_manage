<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>注册|文献管理系统</title>
<%@include file="includes/header2.jsp" %>
<div class="col-md-4 col-md-offset-4 well" style="margin-top: 80px;">
  <s:form action="register" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal"
          label="用户注册">
    <s:textfield
        label="用户名"
        name="username"
        cssClass="input-sm"/>
    
    <s:password
        label="密码"
        name="password"
        cssClass="input-sm"/>
    <s:submit value="注册" cssClass="btn btn-primary btn-lg btn-block"/>
    
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>