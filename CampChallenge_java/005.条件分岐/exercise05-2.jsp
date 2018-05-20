<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
int num = 3;

switch(num){
    case 1:
        out.println("one");
        break;
    case 2:
        out.println("two");
        break;
    default:
        out.println("想定外");
        break;
}
%>