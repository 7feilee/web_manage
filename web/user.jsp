<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
<div class="col-md-3">
  <img src="${pageContext.request.contextPath}/resources/img/user/icon-user-default.png" class="img-responsive"/>
  <h2 class="page-header"><s:property value="%{(user.name == null) ? (user.username) : (user.name)}"/></h2>
  <s:if test="%{user.bio != null}">
    <div class="text-muted"><s:property value="%{user.bio}"/></div>
  </s:if>
</div>
<div class="col-md-9">
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h1 class="panel-title">
        <%
          String sruid = request.getParameter("id");
          int iruid = -1;
          if(sruid != null)
            iruid = Integer.valueOf(sruid);
          if (userp != null && userp.getId() == iruid)
            out.print("我");
          else
            out.print("ta");
        %>的论文
      </h1>
    </div>
    <div class="panel-body">
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
            <p class="text-center">并没有计划要读的论文</p>
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
                  <td style='vertical-align: middle;'><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
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
            <p class="text-center">并没有已经粗略读过的论文</p>
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
                  <td style='vertical-align: middle;'><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
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
            <p class="text-center">并没有已经仔细研究过的论文</p>
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
                  <td style='vertical-align: middle;'><s:iterator value="authors"><s:property/>&nbsp;</s:iterator></td>
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
  </div>
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h1 class="panel-title">
        <%
          if (userp != null && userp.getId() == iruid)
            out.print("我");
          else
            out.print("ta");
        %>的动态
      </h1>
    </div>
    <div class="panel-body">
      todo
    </div>
  </div>
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h1 class="panel-title">
        <%
          if (userp != null && userp.getId() == iruid)
            out.print("我");
          else
            out.print("ta");
        %>的笔记
      </h1>
    </div>
    <div class="panel-body">
      todo....
    </div>
  </div>
  <%
    if (userp != null && userp.getId() == iruid)
    {
  %>
  <a href="<s:url action="logout"/>" class="btn btn-danger btn-hg btn-block">
    <span class="glyphicon glyphicon-log-out"></span>&nbsp;退出登录</a>
  <%
    }
  %>
</div>

<%@ include file="includes/footer.jsp" %>