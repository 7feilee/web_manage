<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>我的分类树|文献管理系统</title>
<!--include treemenu-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/treemenu/css/treemenu.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/treemenu/js/treemenu.js"></script>
<!--initiate treemenu-->
<script>
    $(document).ready(function () {
        $("div#tree > ul").treemenu({
            'delay': 100
        });
    });
</script>

<%@include file="includes/header2.jsp" %>
<div id="tree">
  <s:property value="frontEndTree" escapeHtml="false"/>
</div>
<%@ include file="includes/footer.jsp" %>
