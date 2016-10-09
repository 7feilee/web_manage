<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
<div class="col-md-4">
  <div>
    <img src="<s:property value="user.imgURI"/>"/>
  </div>
  <h2 class="page-header"><s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/></h2>
  <s:if test="%{user.bio != null}">
    <div class="text-muted"><s:property value="user.bio"/></div>
  </s:if>
</div>
<div class="col-md-8">
  <%-- todo --%>
  <div>
    <s:if test="%{user.toReadPapers.isEmpty()}">
      <h4 class="text-center">你并没有想读的论文</h4>
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
        <s:iterator value="user.toReadPapers">
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
              <button class="btn btn-sm btn-danger" data-toggle="modal"
                      data-target="#myModal<s:property value="ISBN"/>">
                <span class="glyphicon glyphicon-remove"></span>&nbsp;todo<!--todo-->
              </button>
            </td>
          </tr>
        </s:iterator>
        </tbody>
      </table>
    </s:else>
  </div>
</div>
<%@ include file="includes/footer.jsp" %>