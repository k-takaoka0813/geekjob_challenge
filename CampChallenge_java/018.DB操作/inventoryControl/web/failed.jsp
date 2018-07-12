<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="inventoryControl.UserBean" />
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在庫管理システム</title>
  </head>
  <body>
    <h1>認証失敗</h1>
    <p><jsp:getProperty name="userbeanses" property="aName" /> は存在しないかパスワードが違います。</p>
  </body>
</html>