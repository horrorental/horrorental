<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String errMes = (String)request.getAttribute("errMes");
if(errMes == null){
	errMes = "";
}

%>uuuuuuuuuuuuuuuuuuuuuuuuu
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>カード情報入力画面</title>
</head>
<body>

<h1>たぶんこのjsp意味ないかと？</h1>

<h1>カード情報入力画面</h1>
<form method="post" action = "./CardCheckServlet">
	<p>カード番号入力<br>
	<input type="text" name="number"><br></p>
	<p><input type="submit" value="登録"></p>
	<%=errMes %>
</form>


</body>
</html>