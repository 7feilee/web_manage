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
<!-- initiate datatable and ajax -->
<script type="text/javascript" charset="utf-8">
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
                var url = "<s:url action="showPaperState"/>?uid=" + uid + "&pid=" + pid;
                var $mid = $("#ms_" + pid);
                $mid.removeClass("hidden");
                $.ajax({
                    type: 'POST',
                    url: url,

                    success: function (result, status, xhr) {
                        $mid.addClass("hidden");
                        $this.val(result).trigger("change.select2");
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
        }).on('draw.dt', iniSelector()).on('init.dt', iniSelector());

        $("select.clct").on("change", (function () {
            var $this = $(this);
            var uid, pid, state;
            uid = 0${sessionScope.user.id};
            if (uid == 0)
                return;
            $this.attr("disabled", true);
            pid = $this.attr("id").substring(3, 999);
            state = $this.val();
            var url = "<s:url action="changePaperState"/>?uid=" + uid + "&pid=" + pid + "&state=" + state;
            var $mid = $("#ms_" + pid);
            $mid.removeClass("hidden");
            $mid.addClass("loader primary");
            $.ajax({
                type: 'POST',
                url: url,

                success: function (result, status, xhr) {
                    $mid.removeClass("loader primary");
                    $mid.addClass("glyphicon-ok success");
                    $this.attr("disabled", false);
                },
                error: function (xhr, status, error) {
                    $mid.removeClass("loader primary");
                    $mid.addClass("glyphicon-remove danger");
                    $this.attr("disabled", false);
                }
            });
        }));
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
  <div class="col-md-3 col-md-offset-9">
    <a href="addpaper.jsp" class="btn btn-primary btn-block btn-lg">
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
      <th width="20%">发表时间</th>
      <%
        if (userp != null)
        {
      %>
      <th width="20%">收藏</th>
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
        <%
          if (userp != null)
          {
        %>
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
        <%
          }
        %>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</s:else>
<%@ include file="includes/footer.jsp" %>
