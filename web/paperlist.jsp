<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>论文列表|文献管理系统</title>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/libs/datatables/css/dataTables.bootstrap.min.css">
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/libs/fancytree/css/skin-bootstrap/ui.fancytree.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/fancytree/js/jquery.fancytree-all.js"></script>
<script type="text/javascript" charset="utf-8">
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
        function iniSelector() {
            $('select.select').select2();
            $("select.clct").each(function () {
                var $this = $(this);
                var uid, pid;
                uid = 0${sessionScope.user.id};
                if (uid == 0)
                    return;
                $this.attr("disabled", true);
                pid = $this.attr("id").substring(3, 999);
                var $mid = $("#ms_" + pid);
                $mid.removeClass("hidden");
                $.ajax({
                    type: 'POST',
                    url: "<s:url action="showPaperState"/>",
                    data: {uid: uid, pid: pid},
                    success: function (result, status, xhr) {
                        $mid.addClass("hidden");
                        $this.val(result.state).trigger("change.select2");
                        $this.attr("disabled", false);
                    },
                    error: function (xhr, status, error) {
                        $mid.addClass("hidden");
                        $this.attr("disabled", false);
                    }
                });
            });
        }
        $(".table").dataTable({
            lengthMenu: [25, 50, 100, 150, 300],
            pageLength: 50,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "每页显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "表格内搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "autoWidth": false
        }).on('draw.dt', iniSelector());
        $("select.clct").on("change", (function () {
            var $this = $(this);
            var uid, pid, state;
            uid = 0${sessionScope.user.id};
            if (uid == 0)
                return;
            $this.attr("disabled", true);
            pid = $this.attr("id").substring(3, 999);
            state = $this.val();
            var $mid = $("#ms_" + pid);
            $mid.removeClass("hidden");
            $mid.addClass("loader primary");
            $.ajax({
                type: 'POST',
                url: '<s:url action="changePaperState"/>',
                data: {uid: uid, pid: pid, state: state},
                success: function (result, status, xhr) {
                    $mid.removeClass("loader primary");
                    $mid.addClass("glyphicon-ok success");
                    $this.val(result.state).trigger("change.select2");
                    $this.attr("disabled", false);
                },
                error: function (xhr, status, error) {
                    $mid.removeClass("loader primary");
                    $mid.addClass("glyphicon-remove danger");
                    $this.attr("disabled", false);
                }
            });
        }));
        <%if(userp!=null){%>
        //设定用户树
        $.ajax({
            url: "<s:url action="showUserTree"/>",
            success: function (result, status, xhr) {
                var $tree = $("#tree");
                $tree.html(result.frontEndTree);
                $tree.fancytree({
                    extensions: ["glyph"],
                    glyph: glyph_opts
                });
                $tree.fancytree("getRootNode").visit(function (node) {
                    node.setExpanded(true);
                });
            },
            error: function (xhr, status, error) {
                $("#tree").text("用户分类树载入失败，请刷新重试");
            }
        });
        $(".showmodel").each(function () {
            var $this = $(this);
            var pid = $this.attr("pid");
            //得到分类情况
            $.ajax({
                url:"<s:url action="getPaperNode"/>",
                data:{uid:'<%=userp.getId()%>',pid:pid},
                success: function (result, status, xhr) {
                    if(result.tree.labelname!==null) {
                        $this.attr("nid", result.tree.id);
                        $this.removeClass("disabled");
                        $this.text(result.tree.labelname);
                    }
                    else{
                        $this.text("未分类");
                        $this.removeClass("disabled");
                    }
                }
            });
        }).click(function () {
            var $this=$(this);
            //显示模态框
            $('#myModal').modal('show');
            //激活当前节点
            $("#tree").fancytree("getTree").activateKey($(this).attr("nid"));
            //提交修改
            $("#submit").click(function () {
                var node = $("#tree").fancytree("getActiveNode");
                var newlabelname;
                if( node ){
                    newlabelname=node.title;
                    $.ajax({
                        url : "<s:url action="changePaperLabel"/>",
                        data:{paper_id:$this.attr("pid"),newlabelname:newlabelname},
                        success: function (result, status, xhr) {
                            $this.text(newlabelname);
                        }
                    });
                    $('#myModal').modal('hide');
                }else{
                    alert("请选中一个节点！");
                }
            });
        });
        <%}%>
    });
</script>

<%@include file="includes/header2.jsp" %>
<s:if test="%{papers.isEmpty()}">
  <h4 class="text-center">数据库中没有论文╮（╯＿╰）╭</h4>
</s:if>
<s:else>
  <%
    if (userp != null)
    {
  %>
  <!-- 模态框（Modal） -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">编辑收藏</h4>
        </div>
        <div class="modal-body">
          <div id="tree"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button id="submit" type="button" class="btn btn-primary">提交更改</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
  <div class="col-md-3 col-md-offset-9">
    <a href="editpaper.jsp" class="btn btn-primary btn-block btn-lg">
      <span class="glyphicon glyphicon-plus"></span>&nbsp;新增论文
    </a>
  </div>
  </div>
  <div class="row" style="margin-top: 30px">
  <%
    }
  %>
  <table class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
      <th width="40%">篇名</th>
      <th width="20%">作者</th>
      <th width="10%">发表时间</th>
      <%
        if (userp != null)
        {
      %>
      <th width="20%">阅读状态</th>
      <th width="10">分类</th>
      <%}%>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="papers">
      <tr>
        <td>
          <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
            <s:property value="%{title}"/>
          </a>
        </td>
        <td><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
        <td><s:property value="%{publishDate}"/></td>
        <%if (userp != null) {%>
        <td>
          <select id="ps_<s:property value="%{id}"/>" style="width: 75%; min-width: 0; float: left"
                  class="form-control select select-primary clct" title="收藏状态">
            <option value="0">未收藏</option>
            <option value="1">计划读</option>
            <option value="2">已粗读</option>
            <option value="3">已精读</option>
          </select>
          <div class="loading-icon">
            <span id="ms_<s:property value="%{id}"/>" class="glyphicon loader hidden primary"
                  style="font-size: 20px;text-align: center;"></span>
          </div>
        </td>
        <td>
          <button pid="<s:property value="%{id}"/>" class="showmodel btn btn-primary disabled">载入中...</button>
        </td>
        <%}%>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</s:else>
<%@ include file="includes/footer.jsp" %>
