<%@page import="java.util.Objects"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.*" %>
<%
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("userdata");
    
    //*insertconfirmからinsertへ再度入力する際に、このままではフォームに値が保持されていない。
    //適切な処理を施して、再度入力の際にはフォームに値を保持したままにさせなさい
    
    String name = "";
    String tell = "";
    String comment = "";
    int y = 0;
    int m = 0;
    int d = 0;
    int type = 0;
    
    if(!Objects.isNull(udb)){
      name =  udb.getName();
      tell = udb.getTell();
      comment = udb.getComment();
      y = udb.getY();
      m = udb.getM();
      d = udb.getD();
      type = udb.getType();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
    <form action="insertconfirm" method="POST">
        名前:
        <input type="text" name="name" value="<%= name %>" required>
        <br><br>

        生年月日:
        <select name="year" required>
            <option value="">----</option>
            <%
            for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%= i == y ? "selected" : "" %>> <%=i%> </option>
            <% } %>
        </select>年
        <select name="month" required>
            <option value="">--</option>
            <%
            for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <%= i == m ? "selected" : "" %>><%=i%></option>
            <% } %>
        </select>月
        <select name="day" required>
            <option value="">--</option>
            <%
            for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>" <%= i == d ? "selected" : "" %>><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
        <input type="radio" name="type" value="1" required <%= type == 0 || type == 1 ? "checked" : "" %>>エンジニア<br>
        <input type="radio" name="type" value="2" <%= type == 2 ? "checked" : "" %>>営業<br>
        <input type="radio" name="type" value="3"  <%= type == 3 ? "checked" : "" %>>その他<br>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%= tell %>" required>
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard" required><%= comment %></textarea><br><br>

        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
