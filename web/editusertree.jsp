<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>用户研究树</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/libs/fancytree/css/skin-win8/ui.fancytree.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/fancytree/js/jquery.fancytree-all.js"></script>
<script>
    $(document).ready(function () {
        function Post(URL, PARAMTERS) {
            //创建form表单
            var temp_form = document.createElement("form");
            temp_form.action = URL;
            //如需打开新窗口，form的target属性要设置为'_blank'
            temp_form.target = "_self";
            temp_form.method = "post";
            temp_form.style.display = "none";
            //添加参数
            for (var item in PARAMTERS) {
                var opt = document.createElement("textarea");
                opt.name = PARAMTERS[item].name;
                opt.value = PARAMTERS[item].value;
                temp_form.appendChild(opt);
            }
            document.body.appendChild(temp_form);
            //提交数据
            temp_form.submit();
        }
        var count = 1;
        var $tree = $("#tree");
        $tree.fancytree({
            extensions: ["dnd", "edit"],
            dnd: {
                autoExpandMS: 100,
                focusOnClick: true,
                preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
                preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
                dragStart: function (node, data) {
                    return true;
                },
                dragDrop: function (node, data) {
                    data.otherNode.moveTo(node, data.hitMode);
                },
                dragEnter: function (node, data) {
                    //这句话的意思是不允许仅排序的拖动
                    if (node.parent === data.otherNode.parent)
                        return ["over"];
                    return true;
                }
            },
            edit: {
                triggerStart: ["f2", "dblclick", "shift+click", "mac+enter"]
            }
        });
        $tree.fancytree("getRootNode").visit(function (node) {
            node.setExpanded(true);
        });
        $("#addChild").click(function () {
            var node = $("#tree").fancytree("getActiveNode");
            if (!node) {
                alert("请选择一个节点");
                return;
            }
            node.editCreateNode("child", "新节点" + count);
            count++;
        });
        $("#delNode").click(function () {
            var node = $("#tree").fancytree("getActiveNode");
            if (!node) {
                alert("请选择一个节点");
                return;
            }
            while (node.hasChildren()) {
                node.getFirstChild().moveTo(node.parent, "child");
            }
            node.remove();
        });
        $("#editNode").click(function () {
            var node = $("#tree").fancytree("getActiveNode");
            if (!node) {
                alert("请选择一个节点");
                return;
            }
            node.editStart();
        });
        $("#submit").click(function () {
            var result = $tree.toDict(false,function (dict, node) {
                
            });
            var url = "<s:url action="editTree"/>";
            var param = [{name: "data", value: JSON.stringify(result)}];
            Post(url,param);
        });
    });
</script>
<%@ include file="includes/header2.jsp" %>
<div class="row" style="margin-bottom: 10px">
  <div class="col-md-12">
    <div class="btn-toolbar">
      <div class="btn-group">
        <button class="btn btn-sm btn-primary" id="addChild"><span class="glyphicon glyphicon-plus"></span> 添加</button>
        <button class="btn btn-sm btn-info" id="editNode"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
        <button class="btn btn-sm btn-danger" id="delNode"><span class="glyphicon glyphicon-remove"></span> 删除</button>
      </div>
      <div class="btn-group pull-right">
        <button id="submit" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-ok"></span> 提交</button>
      </div>
    </div>
  </div>
</div>
<div class="row">
  <div id="tree" class="col-md-12">
    <s:property value="frontEndTree" escapeHtml="false"/>
  </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">您确定吗？</h4>
      </div>
      <div class="modal-body">确定要删除节点：<s:property value="note.title"/>吗？该操作无法恢复！</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">我后悔了</button>
        <a type="button" class="btn btn-danger"
           href="<s:url action="deleteNote"><s:param name="id" value="%{note.id}"/></s:url>">确定删除</a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>
<%--<div class="row">--%>
<%--<div class="col-md-12">--%>
<%--<div class="alert alert-info" style=""><span class="glyphicon glyphicon-info-sign"></span> 支持拖拽，双击可即时编辑</div>--%>
<%--</div>--%>
<%--</div>--%>
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