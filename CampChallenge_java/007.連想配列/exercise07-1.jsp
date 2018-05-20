<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashMap" %>
<%
// 配列の作成
// 連想配列の作成
HashMap<String, String> data = new HashMap<String, String>();
// 要素の追加
data.put("1", "AAA");
data.put("Hello", "world");
data.put("soeda", "33");
data.put("20", "20");
out.println(data);
%>