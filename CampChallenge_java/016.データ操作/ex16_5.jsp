<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
int num = Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>クエリストリング（簡易素因数分解）</title>
    </head>
    <body>
        <table>
            <thead>入力確認</thead>
            <tbody>
                <tr><th>商品種別</th><td><%
                  switch(type){
                    case 1:
                      out.println("雑貨");
                      break;
                    case 2:
                      out.println("生鮮食品");
                      break;
                    case 3:
                      out.println("その他");
                      break;
                    default:
                      out.println("不明");
                  }
                %></td></tr>
                <tr><th>単価</th><td><%
                  out.println( total / count );
                %></td></tr>
                <tr><th>ポイント</th><td><%
                  if( total < 3000 ){
                    out.println("0");
                  }
                  else if( 3000 <= total && total < 5000 ){
                    out.println(4*(total/100));
                  }
                  else if( 5000 <= total ){
                    out.println(5*(total/100));
                  }
                %>pt</td></tr>
            </tbody>
        </table>
    </body>
</html>
