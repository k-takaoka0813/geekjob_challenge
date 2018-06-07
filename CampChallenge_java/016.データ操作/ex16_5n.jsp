<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%
int input_num = Integer.parseInt(request.getParameter("num"));
int temp_num = input_num;
String result = "";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>クエリストリング（簡易素因数分解）</title>
    </head>
    <body>
      <%
        out.println(input_num + "＝");
        //判定：素数かどうか
        boolean prime_flag = true;
        for(int i = 2; i < input_num; i++) {
          if(input_num % i == 0) {
            //素数ではないので素因数分解へ
            prime_flag = false;
          }
        }
        //
        if(prime_flag == true){
          out.println("素数です。");
        }
        else{
          //素数ではないので素因数分解をする
          //ループ：割る側の数(今回は1桁まで)
          PRIME:for(int prime_num = 2; prime_num < 10; prime_num++){
            //判定：割る数側が素数かどうか
            for(int i = 2; i < prime_num; i++) {
              if(prime_num % i == 0) {
                //素数ではないので次の数へ
                continue PRIME;
              }
            }
            //ループ判定：素数で割り切れるかどうか
            while(true){
              if(temp_num % prime_num == 0){
                //割り切れるので素因数と断定
                temp_num = temp_num / prime_num;
                result = result.concat(prime_num + "*");
              }
              else{
                //割り切れないので次の数へ
                continue PRIME;
              }
            }
          }
          //最後の「*」を削除する
          result = result.substring(0, result.length()-1);
          if(1 < temp_num){
            //temp_numが1以上なら余り
            result = result.concat("あまり" + temp_num );
          }
          //結果の出力
          out.println(result);
        }
        
      %>
    </body>
</html>
