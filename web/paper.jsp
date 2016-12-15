<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>论文:<s:property value="paper.title"/>|文献管理系统</title>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/libs/datatables/css/dataTables.bootstrap.min.css">
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.bootstrap.min.js"></script>
<!-- initiate datatable and ajax -->
<script>
    $(document).ready(function () {
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
        });
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
        var leftHeight = $("#left").height();
        var mainHeight = $("#main").height();
        if(leftHeight < mainHeight)
            $("#left").children("div.well").css("padding-bottom",mainHeight-leftHeight+19);
        else
            $("#main").children("div.well").css("padding-bottom",leftHeight-mainHeight+19);
    });
</script>
<%@include file="includes/header2.jsp" %>

<div class="page-header">
  <span class="h3"><s:property value="paper.title"/></span>
  <%
    if (userp != null)
    {
  %>
  <a class="btn btn-primary pull-right" href="<s:url action="editPaper">
    <s:param name="id" value="id"/></s:url>">
    <span class="glyphicon glyphicon-edit"></span> 编辑</a>
  <%}%>
</div>
</div>
<div class="row">
  <div id="left" class="col-md-4">
    <div class="well">
      <h5 class="page-header" style="margin-top: 20px">作者</h5>
      <p class="lead"><s:property value="authors"/></p>
      <h5 class="page-header">关键字</h5>
      <p class="lead"><s:property value="keywords"/></p>
      <h5 class="page-header">发表日期</h5>
      <p class="lead"><s:property value="dateStr"/></p>
    </div>
  </div>
  <div id="main" class="col-md-8">
    <div class="well">
      <h5 class="page-header" style="margin-top: 20px">摘要</h5>
      <p class="lead"><s:property value="paper.abstct"/></p>
      <s:if test="%{paper.fileURI!=null}">
      <a href="<s:url action="downloadFile"><s:param name="paper_id" value="%{id}"/></s:url>" target="_blank" class="btn btn-block btn-hg btn-primary">
        <img src="resources/img/阅读全文.svg" style="max-width:1em;max-height:1em;"> 阅读全文</a>
      </s:if>
    </div>
  </div>
</div>
<div class="row">
  <div class="panel panel-primary" style="margin-top: 30px">
    <div class="panel-heading">
      <h5 style="margin: 0">大家的笔记</h5>
    </div>
    <div class="panel-body">
      <%if (userp != null) {%>
      <div class="col-md-3 col-md-offset-9" style="margin-bottom: 15px">
        <a href="editnote.jsp?paperid=${requestScope.get("id")}" class="btn btn-primary btn-block btn-hg">
          <span class="glyphicon glyphicon-plus"></span>&nbsp;添加笔记
        </a>
      </div>
      <%}%>
      <s:if test="%{notes.isEmpty()}">
        <h4 class="text-center">并没有用户留下笔记╮（╯＿╰）╭</h4>
      </s:if>
      <s:else>
        <table class="table table-bordered table-striped table-hover">
          <thead>
          <tr>
            <th width="50%">题目</th>
            <th width="30%">作者</th>
            <th width="20%">发表时间</th>
          </tr>
          </thead>
          <tbody>
          <s:iterator value="notes">
            <tr>
              <td>
                <a href='<s:url action="showNoteDetails"><s:param name="id" value="id"/></s:url>'>
                  <s:property value="%{title}"/>
                </a>
              </td>
              <td>
                <a href="<s:url action="showUserDetails">
                      <s:param name="id"><s:property value="author.id"/></s:param>
                   </s:url>">
                  <s:property value="author.name==null?author.username:author.name"/>
                </a>
              </td>
              <td><s:property value="%{publishTime}"/></td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </s:else>
    </div>
  </div>
</div>
<div class="row">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <span class="panel-title h6">
        大家的动态
      </span>
    </div>
    <div class="panel-body">
      <table class="table table-bordered table-striped table-hover dtno">
        <thead>
        <tr>
          <th>时间</th>
          <th>事件</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="logs">
          <tr>
            <td>
              <s:property value="time"/>
            </td>
            <td>
              <s:property value="event" escapeHtml="false"/>
            </td>
          </tr>
        </s:iterator>
        </tbody>
      </table>
    </div>
  </div>
<%@ include file="includes/footer.jsp" %>
