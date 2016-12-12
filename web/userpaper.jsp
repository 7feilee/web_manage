<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title><%
  String sruid = request.getParameter("id");
  int iruid = -1;
  if (sruid != null)
    iruid = Integer.valueOf(sruid);
  if (userp != null && userp.getId() == iruid)
    out.print("我");
  else
  {%>
  <s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/>
  <%}%>的论文|文献管理系统</title>
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
                var $mid = $("#ms_" + pid);
                $mid.removeClass("hidden");
                $.ajax({
                    type: 'POST',
                    url: "<s:url action="showPaperState"/>",
                    data: {uid:uid,pid:pid},
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
                data: {uid:uid,pid:pid,state:state},
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
    });
</script>
<%@include file="includes/header2.jsp" %>
<div class="col-md-12">
<ol class="breadcrumb">
  <li><a href="<s:url action="showUserDetails"><s:param name="id" value="%{id}"/></s:url>">个人中心</a></li>
  <li class="active">论文</li>
</ol>
<ul id="myTab" class="nav nav-tabs">
  <li class="active">
    <a href="#toRead" data-toggle="tab">
      计划读
    </a>
  </li>
  <li><a href="#read" data-toggle="tab">已粗读</a></li>
  <li><a href="#studied" data-toggle="tab">已精读</a></li>
</ul>
<div id="myTabContent" class="tab-content">
  <div id="toRead" class="tab-pane fade in active">
    <s:if test="%{user.toReadPapers.isEmpty()}">
      <h4 class="text-center">并没有计划要读的论文</h4>
    </s:if>
    <s:else>
      <table class="table table-bordered table-striped table-hover dt">
        <thead>
        <tr>
          <th width="40%">篇名</th>
          <th width="20%">作者</th>
          <th width="20%">发表时间</th>
          <%
            if (userp != null && userp.getId() == iruid)
            {
          %>
          <th width="20%">收藏</th>
          <%}%>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="user.toReadPapers">
          <tr>
            <td>
              <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
                <s:property value="%{title}"/>
              </a>
            </td>
            <td><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
            <td><s:property value="%{publishDate}"/></td>
            <%
              if (userp != null && userp.getId() == iruid)
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
                            style="font-size: 20px;vertical-align: middle;text-align: center;"></span>
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
  </div>
  <div id="read" class="tab-pane fade">
    <s:if test="%{user.readPapers.isEmpty()}">
      <h4 class="text-center">并没有已经粗略读过的论文</h4>
    </s:if>
    <s:else>
      <table class="table table-bordered table-striped table-hover dt">
        <thead>
        <tr>
          <th width="40%">篇名</th>
          <th width="20%">作者</th>
          <th width="20%">发表时间</th>
          <%
            if (userp != null && userp.getId() == iruid)
            {
          %>
          <th width="20%">收藏</th>
          <%}%>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="user.readPapers">
          <tr>
            <td>
              <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
                <s:property value="%{title}"/>
              </a>
            </td>
            <td><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
            <td><s:property value="%{publishDate}"/></td>
            <%
              if (userp != null && userp.getId() == iruid)
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
                            style="font-size: 20px;vertical-align: middle;text-align: center;"></span>
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
  </div>
  <div id="studied" class="tab-pane fade">
    <s:if test="%{user.studiedPapers.isEmpty()}">
      <h4 class="text-center">并没有已经仔细研究过的论文</h4>
    </s:if>
    <s:else>
      <table class="table table-bordered table-striped table-hover dt">
        <thead>
        <tr>
          <th width="40%">篇名</th>
          <th width="20%">作者</th>
          <th width="20%">发表时间</th>
          <%
            if (userp != null && userp.getId() == iruid)
            {
          %>
          <th width="20%">收藏</th>
          <%}%>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="user.studiedPapers">
          <tr>
            <td>
              <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
                <s:property value="%{title}"/>
              </a>
            </td>
            <td><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
            <td><s:property value="%{publishDate}"/></td>
            <%
              if (userp != null && userp.getId() == iruid)
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
                            style="font-size: 20px;vertical-align: middle;text-align: center;"></span>
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
  </div>
</div>
</div>
<%@ include file="includes/footer.jsp" %>
