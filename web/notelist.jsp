<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<% Boolean useDatatable = true;%>
<%@ include file="includes/header.jsp" %>
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