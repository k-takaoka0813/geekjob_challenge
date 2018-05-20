<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
int total = 0;
for(int i = 0 ; i <= 100 ; i++){
  total += i;
}
out.println(total);
%>