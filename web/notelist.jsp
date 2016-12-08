<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>笔记列表|文献管理系统</title>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/resources/libs/datatables/css/dataTables.bootstrap.min.css">
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="${pageContext.request.contextPath}/resources/libs/datatables/js/dataTables.bootstrap.min.js"></script>
<!-- initiate datatable and ajax -->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
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
        });
    });
</script>

<%@include file="includes/header2.jsp" %>
<s:if test="%{notes.isEmpty()}">
  <h4 class="text-center">数据库中没有笔记╮（╯＿╰）╭</h4>
</s:if>
<s:else>
  <table class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
      <th width="40%">题目</th>
      <th width="20%">作者</th>
      <th width="20%">论文</th>
      <th width="20%">发表时间</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="notes">
      <tr>
        <td>
          <a href='<s:url action="showNoteDetails"><s:param name="id" value="id" /></s:url>'>
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
        <td>
          <a href="<s:url action="showPaperDetails">
                <s:param name="id"><s:property value="paper.id"/></s:param>
           </s:url>">
            <s:property value="%{paper.title}"/>
          </a>
        </td>
        <td><s:property value="%{publishTime}"/></td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
</s:else>
<%@ include file="includes/footer.jsp" %>