<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
  Boolean useDatatable = false;
  Boolean err;
  err = (Boolean) request.getAttribute("err");
%>
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
    <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
        <label class="checkbox" for="login_autoLogin" >
          <input type="checkbox" name="autoLogin" id="login_autoLogin"
                 checked="checked" data-toggle="checkbox">
          自动登录
          <i class="glyphicon glyphicon-info-sign" data-toggle="tooltip" title="请不要在公用电脑上勾选此选项"></i>
        </label>
      </div>
    </div>
    <s:submit value="登录" cssClass="btn btn-primary btn-lg btn-block"/>
  </s:form>
  <%
    if (err != null && err)
    {
  %>
  <div class="alert alert-danger alert-dismissable" style="margin-top: 10px">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">&times;</button>
    用户名或密码错误！
  </div>
  <%
    }
  %>
</div>
<script>
  $(function () {
    $(':checkbox').radiocheck();
    $('[data-toggle="tooltip"]').tooltip();
  })
</script>
<%@ include file="includes/footer.jsp" %>