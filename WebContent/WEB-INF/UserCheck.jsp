<%@ page import="java.net.*, java.util.Date,java.util.List,java.util.ArrayList" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>

<%
if(session.getAttribute("id") == null){
%>

<p>このへんはセッションのためあとでやること</p>
<div>
<p>ログインしていません</p>
<br>
<p>ログインをしてから再度アクセスください。</p>

<a href = "http://localhost:8080/HorroRental/LoginServlet">ログイン画面へ</a>
</div>

<%
}
//else{
%>

<h1>お客様本人確認</h1>

<p>購入手続きの前にお客様が本人であることを確認するため再度パスワードを入力してください。</p>


<form action="UserCheckServlet" method="post">

パスワード<input type="password" name="pass" />${ passErr }

<br />

<button type="submit">認証</button>
<button type="reset" class="btn btn-primary">　リセット</button>
</form>

<p>${ sendErr }</p>



<%
//エラーメッセージを取得
String errorMessage = 
	(String)request.
		getAttribute("errMsg");

//エラーメッセージが設定されて呼ばれているときには
//エラーメッセージを表示する
if (errorMessage != null){
%>
<%= errorMessage %>
<%
}
%>

<p><input type="button" value="マイカートに戻る" onClick="location.href='http://localhost:8080/HorroRental/CartServlet'"></p>

<%
//}
%>


</body>
</html>