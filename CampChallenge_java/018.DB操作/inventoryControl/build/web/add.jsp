<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Objects"%>
<%request.setCharacterEncoding("utf-8");%>
<%
//セッションチェック
if(!Objects.equals(session.getAttribute("login"),"true")){
  //未登録ならlogin.jspへ飛ばす
  application.getRequestDispatcher("/login.jsp").forward(request, response);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在庫管理システム</title>
  </head>
  <body>
    <h1>商品追加</h1>
    <FORM method="POST" action="Add">
      商品名 : <INPUT type="text" name="productName" required><br><br>
      価格 : <INPUT type="number" name="price" required><br><br>
      <INPUT type="submit" value="SEND">
      <INPUT type="reset" value="RESET">  
    </FORM>  
  </body>
</html>