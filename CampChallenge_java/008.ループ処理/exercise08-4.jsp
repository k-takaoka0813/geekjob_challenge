<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- while文 --%>
<%
float num = 1000;
while(num >= 100){
  num /= 2;
}
out.println("最終数値：" + num);
%>