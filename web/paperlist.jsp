<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
<div class="col-md-9">
  <s:if test="%{papers.isEmpty()}">
  <h4 class="text-center">数据库中没有论文╮（╯＿╰）╭</h4>
  </s:if>
  <s:else>
  <table class="table table-bordered table-striped table-hover">
    <thead>
    <tr>
      <th style='vertical-align: middle;' width="50%">篇名
        <span class="glyphicon glyphicon-question-sign" data-toggle="tooltip" data-placement="top"
              title="点击书名查看详情"></span>
      </th>
      <th style='vertical-align: middle;' width="20%">作者</th>
      <th style='vertical-align: middle;' width="20%">发表时间</th>
      <th style='vertical-align: middle;' width="10%">收藏</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="papers">
      <tr>
        <td style='vertical-align: middle;'>
          <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
            <s:property value="title"/>
          </a>
        </td>
        <td style='vertical-align: middle;'><s:property value="authors"/></td>
          <%-- fixme --%>
        <td style='vertical-align: middle;'><s:property value="publishDate"/></td>
        <td style='vertical-align: middle;'>
          <button class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal<s:property value="ISBN"/>">
            <span class="glyphicon glyphicon-remove"></span>&nbsp;todo<!--todo-->
          </button>
        </td>
      </tr>
    </s:iterator>
    </tbody>
  </table>
  </s:else>
  <%@ include file="includes/footer.jsp" %>
