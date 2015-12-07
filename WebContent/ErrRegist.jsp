<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録エラー</title>
</head>
<body>
<h1 style="color: red">${errMes}</h1><br>
　<HR width="100%" color="red" size="10">
<b style="color: red">→→このIDは既に登録されています<br />
　　　戻ってもう一度やり直してください</b>

<p><input type="Button" value="入力フォームに戻る"  style="WIDTH: 150px; HEIGHT: 30px" onClick="location.href='./SignUp.jsp'">
</body>
</html>