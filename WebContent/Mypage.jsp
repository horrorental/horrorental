<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Myページ</title>
</head>
<body>

Myページ
ようこそ  <%=session.getAttribute("userName")%>さん
	

<form action="HistoryServlet" method="get">
<input type="button" onclick="location.href='./HistoryServlet'" value="注文履歴">
<br/>
<input type="button" onclick="location.href='./Update.jsp'" value="会員情報修正">
</form>


<a href="http://localhost:8080/HorroRental/Mypage2.jsp">トップに戻る</a>


</body>
</html>