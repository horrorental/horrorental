<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="io.DetailBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<%
	DetailBean db = (DetailBean)(session.getAttribute("DetailBean"));
%>

<title><%= db.getP_Name() %>のページ</title>
</head>
<body>

<table>
	<tr><th>商品名</th><th>画像</th><th>製作者</th><th>値段</th><th>カートに入れるボタン</th></tr>
	<tr><td><%= db.getP_Name() %></td><td><%= db.getP_Image() %></td><td><%= db.getP_Maker() %></td>
	<td><%= db.getRate_Price() %></td><td>
	<form action="./AddCartServlet" method="post">
	<input type="hidden" name="Cart_P_ID" value="<%= db.getP_ID() %>">
	<input type="text" name="Cart_Num" size="1" maxlength="2" value="1">個数
	<input type="submit" value="カートに入れる"></td>
	</form></tr>
</table>
<%
	String msg = (String)(request.getAttribute("msg"));
	if(msg != null){
		out.println(msg);
	}
%>

</body>
</html>