<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title>阅读时间线|文献管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/timeline/css/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/timeline/css/style.css"/>
<script src="${pageContext.request.contextPath}/resources/libs/timeline/js/modernizr.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/timeline/js/main.js"></script>
<style>
  table{
    word-break:break-all;
  }
  th{
    width: 6em
  }
</style>
<%@include file="includes/header2.jsp" %>
</div></div><div style="overflow: visible"><div style="overflow: visible">
<header style="margin-top: -27px">
  <h1>我的阅读时间线</h1>
</header>

<section id="cd-timeline" class="cd-container">
  <s:iterator value="logs">
  <div class="cd-timeline-block">
    <div class="cd-timeline-img cd-picture">
      <img src="${pageContext.request.contextPath}/resources/img/timeline.svg" alt="T">
      <%--<span class="glyphicon glyphicon-calendar"></span>--%>
    </div> <!-- cd-timeline-img -->
    <div class="cd-timeline-content">
      <table class="table table-bordered table-hover table-condensed">
        <s:property value="event" escapeHtml="false"/>
      </table>
      <span class="cd-date"><s:property value="time"/> </span>
    </div> <!-- cd-timeline-content -->
  </div> <!-- cd-timeline-block -->
  </s:iterator>
</section> <!-- cd-timeline -->

<%@ include file="includes/footer.jsp" %>