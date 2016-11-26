<%@ page import="service.Service" %>
<%@ page import="model.Tree" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="model.User" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的研究树</title>
</head>
<body>
    <%! String s=""; %>
    <%!
        void printtree(Tree tree){
            if (tree!=null){
                s+=(tree.getLabelname());
                s+='\n';
                Collection<Tree> ctrees=tree.getChildTree();
                for (Tree ctree : ctrees) {
                    printtree(ctree);
                }
            }
        }
    %>
    <%
        Service service=new Service();
        Object obj = ServletActionContext.getRequest().getSession().getAttribute("user");
        int uid = ((User) obj).getId();
        Tree tree=service.getUserTree(uid);


        for (int )


    %>

</body>
</html>
