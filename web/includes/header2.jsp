<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="model.User" %>
<%@ page import="utils.CheckLogin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">logo</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
      <ul class="nav navbar-nav">
        <li><a href="<s:url action="listPapers"/>">论文</a></li>
        <li><a href="<s:url action="listLogs"/>">动态</a></li>
        <li><a href="<s:url action="listNotes"/>">笔记</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <%if (userp!=null) {%>
        <li><a href="<s:url action="showUserDetails">
                       <s:param name="id">${sessionScope.user.id}</s:param>
                     </s:url>"><span class="glyphicon glyphicon-user"></span>&nbsp;
          ${sessionScope.user.username}
        </a></li>
        <%}else {%>
        <li><a href="${pageContext.request.contextPath}/register.jsp"><span class="glyphicon glyphicon-user"></span>
          注册</a></li>
        <li><a href="${pageContext.request.contextPath}/login.jsp"><span class="glyphicon glyphicon-log-in"></span>
          登录</a></li>
        <%
          }
        %>
      </ul>
    </div><!-- navbar-collapse -->
  </div>
</nav>
<div class="container">
  <div class="row">