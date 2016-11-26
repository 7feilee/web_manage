<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = false;%>
<%--@ include file="includes/header.jsp" --%>
<html>
<head>
    <title>用户研究树</title>
</head>
<body>
    <div class="well">
        <h2 class="text-center" style="margin-bottom: 20px">添加新的标签</h2>
        <s:form theme="bootstrap" action="addtreelabel" cssClass="form-horizontal" id="validationForm">
            <s:textfield name="labelname" label="标签名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
            <s:textfield name="label_father" label="标签父类" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
            <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
        </s:form>
    </div>


</body>
</html>
<%--@ include file="includes/footer.jsp" --%>