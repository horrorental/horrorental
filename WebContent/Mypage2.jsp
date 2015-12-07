<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>ようこそ(名前)<%=session.getAttribute("userName")%>さん</h3>
<h3>id=<%=session.getAttribute("id")%>さん</h3>
↑の情報がセッションでもたれるよ！テスト的にだしてます
<h1>ログイン後のトップページになるんかな？</h1>

ここに中野のsearch.jspの処理を書く予定？
<br/>
とりあえずリンク
<br/>
<a href="http://localhost:8080/HorroRental/search.jsp">検索（中野）</a>
<br/>
<a href="http://localhost:8080/HorroRental/Mypage.jsp">マイページ（高山）</a>
<br/>
<a href="http://localhost:8080/HorroRental/CartServlet">買い物かご（生駒）</a>
<br/>
<a href="http://localhost:8080/HorroRental/MailForm.jsp">お問い合わせ（黒崎）</a>

<br/>
<a href="">利用方法</a>
<br/>
<a href="">店舗情報</a>
<br/>
<a href="http://localhost:8080/HorroRental/LogoutServlet">ログアウト（生駒）</a>
</body>
</html>