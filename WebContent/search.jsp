<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="io.Product" %>

<!DOCTYPE html>
<html>

<head>
<meta charset=UTF-8>
<title>HorroRental検索画面</title>
</head>

<body>
<%
	out.print("ようこそ");
	if(session.getAttribute("id") != null){
		out.print(session.getAttribute("id"));
	}else{
		out.print("ゲスト");
	}
	out.println("さん");
%>
<br>
<br>

<form action="./SearchServlet" method="post">
	検索ワード<br>
	<input type="text" name="query">
	<br>
	<br>
	カテゴリ選択<br>
	<select name="category">
		<option value="all">すべてのカテゴリ</option>
		<option value="1">CD</option>
		<option value="2">DVD</option>
		<option value="3">GAME</option>
		<option value="4">書籍</option>
		<option value="5">衣装類</option>
		<option value="6">スポット</option>
	</select>
	<br>
	<br>
	<input type="submit" value="けんさく">
</form>
<br>
<br>

<%
	ArrayList<String> errMsg = (ArrayList<String>)request.getAttribute("errMsg");
	if(errMsg != null){
		Iterator<String> errIt = errMsg.iterator();
		while(errIt.hasNext()){
			out.println(errIt.next()); %><br><% 			
		}
	}
	
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
	if(list != null && !(list.isEmpty())){
		out.println("あなたが検索した商品");
%>
<table border="1">
	<tr><th>商品名</th><th>アーティスト</th><th>画像</th></tr>
<%		
		Iterator<Product> listIt = list.iterator();
		while(listIt.hasNext()){
			Product	tmp = listIt.next();
%>
<tr>
	<td><a href="javascript:void(0)" onclick="document.defaultLink<%=tmp.getP_ID()%>.submit(); return false;"><%=tmp.getP_Name()%></a></td>
	<td><a href="javascript:void(0)" onclick="document.defaultLink<%=tmp.getP_ID()%>.submit(); return false;"><%=tmp.getP_Maker()%></a></td>
	<td><a href="javascript:void(0)" onclick="document.defaultLink<%=tmp.getP_ID()%>.submit(); return false;"><%=tmp.getP_Image()%></a></td>
	<form name="defaultLink<%=tmp.getP_ID()%>" method="post" action="./ProductDetailServlet">
	<input type="hidden" name="p_id" value="<%=tmp.getP_ID()%>">
	</form>
</tr>
<%
		}
	}
%>
</table>

</body>
</html>