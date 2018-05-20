<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
final int BASE = 100;
int num = 5;
out.println("足し算：" + (BASE + num) + "</br>");
out.println("引き算：" + (BASE - num) + "</br>");
out.println("掛け算：" + (BASE * num) + "</br>");
out.println("割り算：" + (BASE / num) + "</br>");
%>