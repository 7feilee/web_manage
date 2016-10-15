<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <!-- made by shuaihuaiyi -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="率怀一">
  <title>文献管理系统</title>
  <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
  <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
  <!--[if lt IE 9]>
  <script type="text/javascript" src="resources/js/html5shiv.js"></script>
  <![endif]-->
  <sj:head jqueryui="false"/>
  <sb:head/>
  
  <link rel="stylesheet" href="resources/libs/flatui/css/flat-ui.min.css" type="text/css"/>
  <script type="text/javascript" src="resources/libs/flatui/js/flat-ui.min.js"></script>
  <%
    if(useDatatable)
    {
  %>
  <link rel="stylesheet" type="text/css" href="resources/libs/datatables/css/dataTables.bootstrap.min.css">
  <script type="text/javascript" charset="utf8" src="resources/libs/datatables/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" charset="utf8" src="resources/libs/datatables/js/dataTables.bootstrap.min.js"></script>
  
  <!-- initiate datatable -->
  <script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
      $('.table').dataTable({
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
        }
      });
    });
  </script>
  <%
    }
  %>
  <link href="resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/index.jsp">logo</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
      <ul class="nav navbar-nav">
        <li><a href="<s:url action="listPapers"/>">论文</a></li>
        <li><a href="javascript:void(0);">动态</a></li>
        <li><a href="javascript:void(0);">笔记</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/register.jsp"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
        <li><a href="/login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
      </ul>
    </div><!-- navbar-collapse -->
  </div>
</nav>
<div class="container">
  <div class="row">