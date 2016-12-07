<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>我的分类树|文献管理系统</title>
<!--include treemenu>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/treemenu/css/treemenu.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/treemenu/js/treemenu.js"></script-->
<script src="resources/libs/jquery/js/jquery-ui.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/fancytree/css/skin-bootstrap/ui.fancytree.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/fancytree/js/jquery.fancytree-all.js"></script>
<!--initiate treemenu-->
<script>
    var glyph_opts = {
        map: {
            doc: "glyphicon glyphicon-file",
            docOpen: "glyphicon glyphicon-file",
            checkbox: "glyphicon glyphicon-unchecked",
            checkboxSelected: "glyphicon glyphicon-check",
            checkboxUnknown: "glyphicon glyphicon-share",
            dragHelper: "glyphicon glyphicon-play",
            dropMarker: "glyphicon glyphicon-arrow-right",
            error: "glyphicon glyphicon-warning-sign",
            expanderClosed: "glyphicon glyphicon-menu-right",
            expanderLazy: "glyphicon glyphicon-menu-right",  // glyphicon-plus-sign
            expanderOpen: "glyphicon glyphicon-menu-down",  // glyphicon-collapse-down
            folder: "glyphicon glyphicon-folder-close",
            folderOpen: "glyphicon glyphicon-folder-open",
            loading: "glyphicon glyphicon-refresh glyphicon-spin"
        }
    };
    $(document).ready(function () {
        $("#tree").fancytree({
            extensions:["glyph"],
            glyph: glyph_opts
        });
    });
</script>
<%@include file="includes/header2.jsp" %>
<div id="tree">
  <s:property value="frontEndTree" escapeHtml="false"/>
</div>
<%@ include file="includes/footer.jsp" %>
