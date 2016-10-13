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
    <div class="text-muted"><s:property value="%{user.bio}"/></div>
  </s:if>
</div>
<div class="col-md-8">
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
                  <s:property value="%{title}"/>
                </a>
              </td>
              <td style='vertical-align: middle;'><s:iterator value="authors" var="author"><s:property
                  value="%{#author}"/>,</s:iterator></td>
                <%-- fixme --%>
              <td style='vertical-align: middle;'><s:property value="%{publishDate}"/></td>
              <td style='vertical-align: middle;'>
                <button class="btn btn-sm btn-danger">
                  todo<!--todo-->
                </button>
              </td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </s:else>
    </div>
    <div id="read" class="tab-pane fade">
      <s:if test="%{user.readPapers.isEmpty()}">
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
          <s:iterator value="user.readPapers">
            <tr>
              <td style='vertical-align: middle;'>
                <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
                  <s:property value="%{title}"/>
                </a>
              </td>
              <td style='vertical-align: middle;'><s:iterator value="authors" var="author"><s:property
                  value="%{#author}"/>,</s:iterator></td>
              <td style='vertical-align: middle;'><s:property value="%{publishDate}"/></td>
              <td style='vertical-align: middle;'>
                <button class="btn btn-sm btn-danger">
                  todo<!--todo-->
                </button>
              </td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </s:else>
    </div>
    <div id="studied" class="tab-pane fade">
      <s:if test="%{user.studiedPapers.isEmpty()}">
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
          <s:iterator value="user.studiedPapers">
            <tr>
              <td style='vertical-align: middle;'>
                <a href='<s:url action="showPaperDetails"><s:param name="id" value="id" /></s:url>'>
                  <s:property value="%{title}"/>
                </a>
              </td>
              <td style='vertical-align: middle;'><s:iterator value="authors" var="author"><s:property
                  value="%{#author}"/>,</s:iterator></td>
              <td style='vertical-align: middle;'><s:property value="%{publishDate}"/></td>
              <td style='vertical-align: middle;'>
                <button class="btn btn-sm btn-danger">
                  todo<!--todo-->
                </button>
              </td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </s:else>
    </div>
  </div>
</div>
<%@ include file="includes/footer.jsp" %>