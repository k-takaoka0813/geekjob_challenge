<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
/*
以下の機能を実現してください。 
  ・名前，性別，趣味の情報をフォームによって送信することができる。 
  ・フォームによって値を送信すると，次回アクセス時に，その値がフォームの初期値となる（セッションかクッキーを利用する）。
*/

HttpSession hs = request.getSession();
String form_name = request.getParameter("form_name");
String form_sex = request.getParameter("form_sex");
String form_hobby = request.getParameter("form_hobby");
//リクエストデータを読み込む。
//リクエストデータがあればセッションに保存する。
if(form_name != null){
  hs.setAttribute("form_name", form_name);
}
if(form_sex != null){
  hs.setAttribute("form_sex", form_sex);
}
if(form_hobby != null){
  hs.setAttribute("form_hobby", form_hobby);
}

//セッションを読み込む。
//セッションがあればそれをフォームのデフォルト値にする。
if(hs.getAttribute("form_name") != null){
  form_name = (String) hs.getAttribute("form_name");
}
if(hs.getAttribute("form_sex") != null){
  form_sex = (String) hs.getAttribute("form_sex");
}
if(hs.getAttribute("form_hobby") != null){
  form_hobby = (String) hs.getAttribute("form_hobby");
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>入力フィールドの作成と実装</title>
    </head>
    <body>
      <form action="" method="post">
                <p>名前</p>
                <input type="text" name="form_name" value="<%= form_name == null ? "" : form_name %>">
                <p>性別</p>
                <input type="radio" name="form_sex" value="m" <% 
                if(Objects.equals(form_sex,"m")){%>
                  checked
                <%}%>>男
                <input type="radio" name="form_sex" value="f" <% 
                if(Objects.equals(form_sex,"f")){%>
                  checked
                <%}%>>女
                <p>趣味</p>
                <textarea name="form_hobby"><%= form_hobby == null ? "" : form_hobby %></textarea>
                </br>
                <input type="submit" value="送信する">
        </form>
    </body>
</html>
