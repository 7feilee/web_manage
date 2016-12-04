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
    <h2 class="text-center" style="margin-bottom: 20px">添加新的标签</h2>
    <s:form theme="bootstrap" action="addtreelabel" cssClass="form-horizontal" id="validationForm">
        <s:textfield name="labelname" label="标签名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
        <s:textfield name="label_father" label="标签父类" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
        <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
    </s:form>

    <h2 class="text-center" style="margin-bottom: 20px">删除标签</h2>
    <s:form theme="bootstrap" action="deleteTreeLabel" cssClass="form-horizontal" id="validationForm">
        <s:textfield name="labelname" label="标签名" labe-lCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
        <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
    </s:form>

    <h2 class="text-center" style="margin-bottom: 20px">把论文添加到标签下</h2>
    <s:form theme="bootstrap" action="changePaperLabel" cssClass="form-horizontal" id="validationForm">
        <s:textfield name="newlabelname" label="标签名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
        <s:textfield name="paper_id" label="论文编号" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>
        <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
    </s:form>




</body>
</html>
<%--@ include file="includes/footer.jsp" --%>