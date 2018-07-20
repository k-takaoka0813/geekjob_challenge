<%@page import="jums.*"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("userdata");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
   
        <h1>登録確認</h1>
        名前:<%= udb.getName()%><br>
        生年月日:<%= udb.getY()+"年"+udb.getM()+"月"+udb.getD()+"日"%><br>
        種別:<%= udb.getType()%><br>
        電話番号:<%= udb.getTell()%><br>
        自己紹介:<%= udb.getComment()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
          <% //*insertresultにて直リンク防止用の処理が存在しない。insertからinsertconfirmへの流れを参考に修正しなさい %>
          <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
          <input type="submit" name="yes" value="はい">
        </form>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
        </form><br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
