<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Objects"%>
<%@page import="java.sql.*"%>
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
/*
以下のファイルを作成して，フォームで送信した値をもとにレコードの削除を行います。

■ HTML ファイル
大単元「データベース基礎」の課題「テーブルの作成とinsert」で作成したテーブルの profileID のカラム情報を送信するフォームを記述してください。

■ JSP or サーブレットファイル（サーブレットとJSPを連携させても構いません）
JDBC を利用して，削除処理を記述します。このとき，削除対象のレコードはフォームから受け取った profileID の値で指定してください。また，全件検索を行い，全レコードの全カラムを画面表示してください。
*/
int profileID = (request.getParameter("form_id") == null) ? 0 : Integer.parseInt(request.getParameter("form_id"));

Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/test?" +
        "user=root&password=root&useUnicode=true&characterEncoding=utf-8");
PreparedStatement st = null;

//Insert部
if(  profileID != 0  ){
  st = conn.prepareStatement("DELETE FROM profiles WHERE profilesID = ?");
  st.setInt(1, profileID);
  st.executeUpdate();
}

//表示部
st = conn.prepareStatement("SELECT * FROM profiles");
ResultSet res = st.executeQuery();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>特定のレコードの削除をするフォーム作成</title>
    </head>
    <body>
      <form action="" method="post">
        <p>ID</p>
        <input type="text" name="form_id" required>
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
