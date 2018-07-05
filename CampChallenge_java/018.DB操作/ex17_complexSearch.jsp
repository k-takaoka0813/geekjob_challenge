<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Objects"%>
<%@page import="java.sql.*"%>
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
/*
以下のファイルを作成して，フォームで送信した値をもとにレコードの検索を行います。 

■ HTML ファイル 
大単元「データベース基礎」の課題「テーブルの作成とinsert」で作成したテーブルの名前・年齢・誕生日情報を送信するフォームを記述してください。 

■ JSP or サーブレットファイル（サーブレットとJSPを連携させても構いません） 
JDBC を利用して，複合検索処理を記述します。AND検索とするか，OR検索とするかは，自由に決めて構いません。
*/


String name = request.getParameter("form_name");
int age = 0;
String form_age = request.getParameter("form_age");
if( !StringUtils.isEmpty(form_age) ){
  age = Integer.parseInt(form_age);
}
String birthday = request.getParameter("form_birthday");

Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/test?" +
        "user=root&password=root&useUnicode=true&characterEncoding=utf-8");
PreparedStatement st = null;
ResultSet res;

//表示部

if( (!StringUtils.isEmpty(name)) || (age != 0) || (!StringUtils.isEmpty(birthday)) ){
  st = conn.prepareStatement("SELECT * FROM profiles WHERE (name = ? OR ? is null) AND "
          + "(age = ? OR ? is null) AND"
          + "(birthday = ? OR ? is null)");
  //名前
  if( (!StringUtils.isEmpty(name)) ){
    st.setString(1, name);
    st.setString(2, name);
  }else{
    st.setNull(1, java.sql.Types.VARCHAR);
    st.setNull(2, java.sql.Types.VARCHAR);
  }
  //年齢
  if( age != 0 ){
    st.setInt(3, age);
    st.setInt(4, age);
  }
  else{
    st.setNull(3, java.sql.Types.INTEGER);
    st.setNull(4, java.sql.Types.INTEGER);
  }
  //誕生日
  if( (!StringUtils.isEmpty(birthday)) ){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date day = new java.sql.Date(dateFormat .parse(birthday).getTime());
    st.setDate(5, day);
    st.setDate(6, day);
  }else{
    st.setNull(5, java.sql.Types.DATE);
    st.setNull(6, java.sql.Types.DATE); 
  }
  res = st.executeQuery();
}
else{
  st = conn.prepareStatement("SELECT * FROM profiles");
  res = st.executeQuery();
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>複合検索</title>
    </head>
    <body>
      <form action="" method="post">
        <p>名前</p>
        <input type="text" name="form_name">
        <p>年齢</p>
        <input type="text" name="form_age">
        <p>誕生日</p>
        <input type="date" name="form_birthday">
        <input type="submit" value="検索">
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
