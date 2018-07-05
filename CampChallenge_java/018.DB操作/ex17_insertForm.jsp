<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Objects"%>
<%@page import="java.sql.*"%>
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
/*
以下のファイルを作成して，フォームで送信した値をもとにレコードの挿入を行います。

■ HTML ファイル
大単元「データベース基礎」の課題「テーブルの作成とinsert」で作成したテーブルの全カラム情報を送信するフォームを記述してください。

■ JSP or サーブレットファイル（サーブレットとJSPを連携させても構いません）
JDBC を利用して，フォームから受け取った値をもとに，レコードを挿入してください。また，全件検索を行い，全レコードの全カラムを画面表示してください。
*/
String name = request.getParameter("form_name");
String tel = request.getParameter("form_tel");
int age = (request.getParameter("form_age") == null) ? 0 : Integer.parseInt(request.getParameter("form_age"));
String birthday = request.getParameter("form_birthday");

Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/test?" +
        "user=root&password=root&useUnicode=true&characterEncoding=utf-8");
PreparedStatement st = null;

//Insert部
if( !StringUtils.isEmpty(name) 
        && !StringUtils.isEmpty(tel)
        && age != 0 
        && !StringUtils.isEmpty(birthday)){
  st = conn.prepareStatement("INSERT INTO profiles (name,tel,age,birthday) VALUE (?,?,?,?)");
  st.setString(1, name);
  st.setString(2, tel);
  st.setInt(3, age);
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  st.setDate(4, new java.sql.Date(dateFormat .parse(birthday).getTime()));
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
        <title>フォームからデータを挿入</title>
    </head>
    <body>
      <form action="" method="post">
        <p>名前</p>
        <input type="text" name="form_name" required>
        <p>電話番号</p>
        <input type="text" name="form_tel" required>
        <p>年齢</p>
        <input type="text" name="form_age" required>
        <p>誕生日</p>
        <input type="date" name="form_birthday" required>
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
