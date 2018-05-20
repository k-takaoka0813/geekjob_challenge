
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%
// 配列の作成
ArrayList<String> data = new ArrayList<String>(){
  {
    add("10");
    add("100");
    add("soeda");
    add("hayashi");
    add("-20");
    add("118");
    add("END");
  }
};
data.set(2,"33");
for(int i = 0; i < data.size(); i++) {
  out.println(data.get(i));
}
%>