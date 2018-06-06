<%-- 
    Document   : test
    Created on : 2018/06/05, 14:09:51
    Author     : 和希
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>入力確認</h1>
        <p>名前：<%=request.getParameter("name")%></p>
        <p>性別：<%=request.getParameter("sex")%></p>
        <p>趣味：<%=request.getParameter("hobby")%></p>
    </body>
</html>
