<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的研究树</title>
</head>
<body>
    <table class="table table-bordered table-striped table-hover">
        <tbody>
        <s:iterator value="trees">
            <tr>
                <s:property value="depth"/>
                <s:property value="labelname"/>
            </tr>
        </s:iterator>
        <s:iterator value="papers">
            <tr>
                <s:property value="title"/>
            </tr>
        </s:iterator>
        </tbody>
    </table>

</body>
</html>
