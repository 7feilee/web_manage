<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>用户研究树</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/libs/fancytree/css/skin-bootstrap/ui.fancytree.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/fancytree/js/jquery.fancytree-all.js"></script>
<script>
    $(document).ready(function () {
        var $tree = $("#tree");
        $tree.fancytree({
            extensions: ["dnd", "edit"],
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
<%@ include file="includes/header2.jsp"%>
<div class="row">
  <div class="col-md-12">
    <div class="btn-toolbar">
      <div class="btn-group">
        <button class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> 添加子节点</button>
        <button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> 删除节点</button>
      </div>
    </div>
  </div>
</div>
<div class="row">
<div id="tree" class="col-md-12">
  <s:property value="frontEndTree" escapeHtml="false"/>
</div>
</div>
    <%--<h2 class="text-center" style="margin-bottom: 20px">添加新的标签</h2>--%>
    <%--<s:form theme="bootstrap" action="addtreelabel" cssClass="form-horizontal" id="validationForm">--%>
        <%--<s:textfield name="labelname" label="标签名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>--%>
        <%--<s:textfield name="label_father" label="标签父类" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>--%>
        <%--<s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>--%>
    <%--</s:form>--%>

    <%--<h2 class="text-center" style="margin-bottom: 20px">删除标签</h2>--%>
    <%--<s:form theme="bootstrap" action="deleteTreeLabel" cssClass="form-horizontal" id="validationForm">--%>
        <%--<s:textfield name="labelname" label="标签名" labe-lCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>--%>
        <%--<s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>--%>
    <%--</s:form>--%>

    <%--<h2 class="text-center" style="margin-bottom: 20px">把论文添加到标签下</h2>--%>
    <%--<s:form theme="bootstrap" action="changePaperLabel" cssClass="form-horizontal" id="validationForm">--%>
        <%--<s:textfield name="newlabelname" label="标签名" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>--%>
        <%--<s:textfield name="paper_id" label="论文编号" labelCssClass="col-sm-1" elementCssClass="col-sm-11" requiredLabel="true"/>--%>
        <%--<s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>--%>
    <%--</s:form>--%>

<%@ include file="includes/footer.jsp" %>