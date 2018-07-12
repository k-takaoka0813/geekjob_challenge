<%@page import="java.sql.*"%>
<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
//セッションチェック
if(!Objects.equals(session.getAttribute("login"),"true")){
  //未登録ならlogin.jspへ飛ばす
  application.getRequestDispatcher("/login.jsp").forward(request, response);
}
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection db_con = DriverManager.getConnection("jdbc:mysql://localhost:8889/inventory","root","root");
PreparedStatement db_st = db_con.prepareStatement("SELECT * FROM products");
ResultSet db_data = db_st.executeQuery();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="inventoryControl.UserBean" />
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在庫管理システム</title>
  </head>
  <body>
    <h1>商品一覧</h1>
    <p>ログイン中：<jsp:getProperty name="userbeanses" property="aName" /></p>
    <table><tr><th>ID</th><th>商品名</th><th>価格</th><th>作成日</th></tr>
    <%
    while(db_data.next()){
      out.print("<tr><td>" + db_data.getInt("productID") + "</td><td>" 
              + db_data.getString("name") + "</td><td>" 
              + db_data.getInt("price") + "</td><td>" 
              + db_data.getTimestamp("created_at") 
              + "</td><td></tr>");
    }
    %>
    </table>
    <p><a href="add.jsp">商品を追加する</a></p>
  </body>
</html>