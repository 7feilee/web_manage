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
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/libs/fancytree/css/skin-bootstrap/ui.fancytree.css"/>
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
        var $tree = $("#tree");
        $tree.fancytree({
            extensions: ["glyph", "dnd", "edit"],
            glyph: glyph_opts,
            dnd: {
                autoExpandMS: 100,
                focusOnClick: true,
                preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
                preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
                dragStart: function(node, data) {
                    /** This function MUST be defined to enable dragging for the tree.
                     *  Return false to cancel dragging of node.
                     */
                    return true;
                },
                dragDrop: function(node, data) {
                    /** This function MUST be defined to enable dropping of items on
                     *  the tree.
                     */
                    data.otherNode.moveTo(node, data.hitMode);
                },
                dragEnter: function(node, data) {
                  /* data.otherNode may be null for non-fancytree droppables.
                   * Return false to disallow dropping on node. In this case
                   * dragOver and dragLeave are not called.
                   * Return 'over', 'before, or 'after' to force a hitMode.
                   * Return ['before', 'after'] to restrict available hitModes.
                   * Any other return value will calc the hitMode from the cursor position.
                   */
                  //这句话的意思是不允许仅排序的拖动
                    return node.parent !== data.otherNode.parent;
                }
            },
            edit: {
                triggerStart: ["f2", "dblclick", "shift+click", "mac+enter"]
            }
        });
        $tree.fancytree("getRootNode").visit(function(node){
            node.setExpanded(true);
        });
    });
</script>
<%@include file="includes/header2.jsp" %>
<div id="tree">
  <s:property value="frontEndTree" escapeHtml="false"/>
</div>
<%@ include file="includes/footer.jsp" %>
