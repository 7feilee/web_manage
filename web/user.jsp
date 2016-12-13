<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title><s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/>的个人中心|文献管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttons.css"/>
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
<!-- initiate datatable and ajax -->
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
                    data: {"uid": uid, "pid": pid},
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
        $(".dt").dataTable({
            lengthMenu: [5, 10, 15, 30, 50],
            pageLength: 5,
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
            autoWidth: false
        }).on('draw.dt', iniSelector());
        $(".dtno").dataTable({
            lengthMenu: [5, 10, 15, 30, 50],
            pageLength: 5,
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
            ordering: false
        });
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
                url: "<s:url action="changePaperState"/>",
                data: {"uid": uid, "pid": pid, "state": state},
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
        var $tree = $("#tree");
        $tree.fancytree({
            extensions: ["glyph"],
            glyph: glyph_opts
//            activate: function (event, data) {
//                $(".fancytree-active").children(".fancytree-title").popover("show");
//                $(".fancytree-title").popover("hide");
//                $(".fancytree-active").children(".fancytree-title").popover("show");
//            }
        });
        $tree.fancytree("getRootNode").visit(function (node) {
            node.setExpanded(true);
        });
        $(".fancytree-title").each(function () {
            var $this = $(this);
            $.ajax({
                url:"<s:url action="getPapersByTreeNodeName"><s:param name="uid" value="%{id}"/></s:url>",
                data:{'nodeName':$(this).text()},
                success: function (result, status, xhr) {
                    var context="";
                    if(result.papers.length == 0)
                        context = "没有论文";
                    else
                        result.papers.forEach(function (paper) {
                            context+="<a href='<s:url action="showPaperDetails"/>?id="+paper.id+"'>"+paper.title+"</a>, ";
                        });
                    $this.popover({
                        html:true,
                        placement: "right",
                        title: "节点论文",
                        content: context
                    });
                },
                error: function (xhr, status, error) {
                    $this.popover({
                        html:true,
                        placement: "right",
                        title: "节点论文",
                        content: "加载失败！"
                    });
                }
            });
        }).click(function () {
            $(".fancytree-title").popover("hide");
            $(this).popover('show');
        }).on('hidden.bs.popover',function () {
            $(".fancytree-active").children(".fancytree-title").popover('show');
        });
    });
</script>
<%@include file="includes/header2.jsp" %>
<div class="col-md-3">
  <img src="${pageContext.request.contextPath}/resources/img/user/icon-user-default.png" class="img-responsive"/>
  <h2 class="page-header"><s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/></h2>
  <s:if test="%{user.bio != null}">
    <div class="text-muted"><s:property value="%{user.bio}"/></div>
  </s:if>
  <div class="panel panel-primary">
    <div class="panel-heading">
      <span class="panel-title h6">
        <%
          String sruid = request.getParameter("id");
          int iruid = -1;
          if (sruid != null)
            iruid = Integer.valueOf(sruid);
          if (userp != null && userp.getId() == iruid)
            out.print("我");
          else
          {%>
        <s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/>
        <%}%>的分类树
      </span>
      <%if(userp != null && userp.getId() == iruid){%>
      <a class="button button-tiny button-plain button-border button-circle pull-right" href="<s:url action="editUserTree"/>">
        <span class="glyphicon glyphicon-edit"></span></a>
      <%}%>
    </div>
    <div class="panel-body">
      <div id="tree">
        <s:property value="frontEndTree" escapeHtml="false"/>
      </div>
    </div>
  </div>
  <a class="btn btn-block btn-primary" href="<s:url action="showTimeLine"><s:param name="id" value="%{#request.id}"/></s:url>">
    <span class="glyphicon glyphicon-calendar"></span> <%
    if (userp != null && userp.getId() == iruid)
      out.print("我");
    else
    {%><s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/><%}%>的阅读时间线</a>
</div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"32"},"slide":{"type":"slide","bdImg":"6","bdPos":"right","bdTop":"100"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<%@ include file="includes/footer.jsp" %>