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
        <table>
            <thead>入力確認</thead>
            <tbody>
                <tr><th>名前</th><td><%=request.getParameter("name")%></td></tr>
                <tr><th>性別</th><td><%=request.getParameter("sex")%></td></tr>
                <tr><th>趣味</th><td><%=request.getParameter("hobby")%></td></tr>
            </tbody>
        </table>
    </body>
</html>
