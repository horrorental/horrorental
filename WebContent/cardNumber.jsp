<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>カード情報入力画面</title>
</head>
<body>
<%
String errMes = (String)request.getAttribute("errMes");
if(errMes == null){
	errMes = "";
}

%>



<h1>ユーザー認証ができました</h1>

<form method="post" action="./CardCheckServlet">
カード会社選択
<br>
<select name="Card">
	<option value="JCB">JCB</option>
	<option value="Visa">Visa</option>
	<option value="Master Card">Master Card</option>
	<option value="MUFG">MUFG</option>
</select>
<br>
<br>
カードナンバー入力<br>
<input type="password" name="C_Card">

<br>
<p>カード番号入力<br>
	<input type="text" name="number"><br></p>
	<%=errMes %>
<br/>

<input type="submit" value="送信">
</form>
<br>
<%
	String msg = (String)request.getAttribute("msg");
	if(msg != null){
		out.println(msg);
	}
%>
<br>
<br>
<a href="http://localhost:8080/HorrorRental/Mypage2.jsp">トップに戻る</a>
</body>
</html>