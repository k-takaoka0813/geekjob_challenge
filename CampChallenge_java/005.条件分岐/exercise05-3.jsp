<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
char str = 'C';

switch(str){
    case 'あ':
        out.println("日本語");
        break;
    case 'A':
        out.println("英語");
        break;
    default:
        break;
}
%>