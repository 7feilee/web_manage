<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="web.action.ShowPaperDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ include file="includes/header.jsp" %>
<title><%
  String id;
  Boolean isEdit;
  if (request.getParameter("id") == null)
  {
    id = "0";
    isEdit = false;
    out.print("添加论文");
  }
  else
  {
    id = String.valueOf(((ShowPaperDetails) ActionContext.getContext().getValueStack().peek()).getId());
    isEdit = true;
    out.print("编辑论文");
  }
  ActionContext.getContext().put("id", id);
%>|文献管理系统</title>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/libs/datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<%--<script src="${pageContext.request.contextPath}/resources/libs/tagsinput/bootstrap-tagsinput.js"></script>--%>
<script
    src="${pageContext.request.contextPath}/resources/libs/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
    src="${pageContext.request.contextPath}/resources/libs/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    $(document).ready(function () {
        $('.form_date').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            //todayBtn: 1,
            autoclose: 1,
            //todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
//        $("multslct").tagsinput({
//            confirmKeys: [13, 59]
//        });
    });
</script>
<%@include file="includes/header2.jsp" %>
<div class="well">
  <h2 class="text-center" style="margin-bottom: 20px"><%
    if (isEdit)
      out.print("编辑");
    else
      out.print("添加");
  %>论文</h2>
  <s:form theme="bootstrap" action="addEditPaper" cssClass="form-horizontal" id="validationForm">
    <s:textfield name="title" label="篇名" labelCssClass="col-sm-1" elementCssClass="col-sm-11"
                 requiredLabel="true" value="%{paper.title}"/>
    <s:textfield name="author" label="作者" placeholder="多个作者请使用;分隔" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{authors}"/>
    <s:textfield name="keyword" label="关键字" placeholder="多个关键字请使用;分隔" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{keywords}"/>
    <s:textfield name="fileURI" label="来源链接" labelCssClass="col-sm-1"
                 elementCssClass="col-sm-11" value="%{paper.fileURI}"/>
    <div class="form-group">
      <label for="dtp_input2" class="col-sm-1 control-label">发表日期</label>
      <div class="col-sm-11">
      <div class="input-group date form_date " data-date="${dateStr}" data-date-format="yyyy-mm-dd"
           data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
        <input id="dtp_input2" name="dateStr" class="form-control" size="16" type="text" value="${dateStr}" readonly>
      </div>
      </div>
    </div>
    <%--<s:textfield id="date" label="发表日期" labelCssClass="col-sm-1" inputPrependIcon="calendar"--%>
                 <%--elementCssClass="col-sm-11" value="" readonly="true" data-link-format="yyyy-mm-dd"/>--%>
    <s:textarea name="abstct" label="摘要" rows="5" labelCssClass="col-sm-1"
                elementCssClass="col-sm-11" value="%{paper.abstct}"/>
    <s:textfield name="id" cssClass="hidden" value="%{#id}" readonly="true"/>
    <s:submit value="提交" cssClass="btn btn-primary btn-block btn-hg"/>
  </s:form>
</div>
<%@ include file="includes/footer.jsp" %>