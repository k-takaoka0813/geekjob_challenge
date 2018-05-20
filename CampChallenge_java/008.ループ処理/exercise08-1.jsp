<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.math.BigInteger" %>
<%
BigInteger num = new BigInteger("8");
BigInteger ans = new BigInteger("8");
for(int i = 1 ; i < 20 ; i++){
  ans = ans.multiply(num);
}
out.println("8の20乗は" + ans + "です。");
%>