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
  <title>hello world</title>
  <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
  <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
  <!--[if lt IE 9]>
  <script type="text/javascript" src="resources/js/html5shiv.js"></script>
  <![endif]-->
  <sj:head jqueryui="false"/>
  <sb:head/>
  
  <link rel="stylesheet" href="resources/libs/flatui/css/flat-ui.min.css" type="text/css"/>
  <script type="text/javascript" src="resources/libs/flatui/js/flat-ui.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="javascript:void(0);">logo</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
      <ul class="nav navbar-nav">
        <li><a href="javascript:void(0);">论文</a></li>
        <li><a href="javascript:void(0);">日志</a></li>
        <li><a href="javascript:void(0);">笔记</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="javascript:void(0);"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
        <li><a href="javascript:void(0);"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div>
</nav>