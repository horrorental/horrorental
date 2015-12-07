<%@ page import="java.net.*, java.util.Date,java.util.List,java.util.ArrayList" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<br>
	<h2>ログイン</h2>
	<br>
	
	<%
		//日時を文字列として取得し、URLエンコードしておく
		Date now = new Date();
		String value = URLEncoder.encode(now.toString(),"utf8");
		
		Cookie cookie = new Cookie("accesstimecookie",value);
		//クッキーの存続時間(秒)(7*24*60*60)
		cookie.setMaxAge(7*24*60*60);
		//クッキーをHTTPヘッダーに追加する
		response.addCookie(cookie);
	%>
	
	
	<%
		//エラーメッセージを取得
		String errorMessage = (String)request.getAttribute("errMsg");
		
		//エラーメッセージが設定されて呼ばれているときにはエラーメッセージを表示する
		if (errorMessage != null){
	%>
	<!-- ログインに失敗しました　IDかPWが間違えていますエラー -->
	<FONT color="#ff0000"><%= errorMessage %></FONT>
	<%
		}
	%>
	
	<!-- ログインに失敗しましたエラー -->
	<p><FONT color="#ff0000"> ${ sendErr } </FONT></p>
	<br>
	<br>
	
	<form action="LoginServlet" method="post">
	
		<p>ID　<input type="text" name="id"></p>
			<FONT color="#ff0000">${ idErr }</FONT>
		<br>
		<br>
		
		<p>パスワード<input type ="password" name="login_pass"></p>
			<FONT color="#ff0000">${ passErr }</FONT>
		<br>
		<br>
	
		<button type="submit">ログイン</button><br><br>
		<button type="reset" class="btn btn-primary">　リセット</button>		
	</form>
	
	<br>
	<br>
	<br>
	
	未登録の方は<a href="http://localhost:8080/HorroRental/SignUp.jsp">［新規会員登録画面］</a>へ！！
	
</body>
</html>