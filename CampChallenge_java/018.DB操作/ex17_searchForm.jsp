<%@page import="java.util.Objects"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
/*
以下の機能を実現してください。 
  ・大単元「データベース基礎」の課題「テーブルの作成とinsert」で作成したテーブルのカラムのうち，名前情報を送信するフォームを記述してください。  
  ・JDBC を利用して，部分一致検索を行い，検索結果の全レコードの全カラムを画面表示してください。
*/
String key = request.getParameter("key");

Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/test?" +
        "user=root&password=root&useUnicode=true&characterEncoding=utf-8");
PreparedStatement st = null;
if(key == null || key.isEmpty()){
  st = conn.prepareStatement("SELECT * FROM profiles");
}else{
  st = conn.prepareStatement("SELECT * FROM profiles WHERE name LIKE ?");
  st.setString(1, "%" + key + "%");
}
ResultSet res = st.executeQuery();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>フォームからデータを挿入</title>
    </head>
    <body>
      <form action="" method="get">
        <p>名前</p>
        <input type="text" name="key" value="<%= key == null ? "" : key %>">
        <input type="submit" value="送信する">
      </form>
      <table>
        <tr><th>ID</th><th>名前</th><th>電話番号</th><th>年齢</th><th>誕生日</th></tr>
        <%
        while(res.next()){
          out.println("<tr>");
          out.println("<td>" + res.getInt("profilesID") + "</td>");
          out.println("<td>" + res.getString("name") + "</td>");
          out.println("<td>" + res.getString("tel") + "</td>");
          out.println("<td>" + res.getInt("age") + "</td>");
          out.println("<td>" + res.getDate("birthday") + "</td>");
          out.println("</tr>");
        }
        st.close();
        conn.close();
        %>
      </table>
    </body>
</html>
