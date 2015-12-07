<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイカート</title>
</head>
<body>


<%
if(session.getAttribute("id") == null){
%>
<div>
<p>ログインしていません</p>
<br>
<p>ログインをしてから再度アクセスください。</p>

<a href = "http://localhost:8080/HorroRental/LoginServlet">ログイン画面へ</a>
</div>
<%
}
else{

%>


<h3>ようこそ<%=session.getAttribute("userName")%>さん</h3>

<p><input type="button" value="ログアウト" onClick="location.href='http://localhost:8080/HorroRental/LogoutServlet'">

<a href="http://localhost:8080/HorroRental/Mypage2.jsp">トップに戻る</a>

<h1>選択中の商品</h1>

<p>ご利用一度につき貸出できるポイントは合計20pointまでとなっております</p>

<p>${ sendMSG }</p>
<p>${ cart_numErr }</p>
<p>${ cart_numErr2 }</p>

<p><input type="button" value="更新" onClick="location.href='http://localhost:8080/HorroRental/CartServlet'"></p>
<TABLE border="3">
<tr>
<td align="center">商品名</td>
<td align="center">単価</td>
<td align="center">使用point</td>
<td align="center">個数</td>
<td align="center">金額</td>
<td align="center">累計point</td>
<td align="center">取り消し</td>
</tr>

<!-- 送料のための変数宣言 -->
<c:set var="max_deli" value="0" />

<!-- 合計金額の変数宣言 -->
<c:set var="sum_price" value="0" />

<!-- 合計ポイントの変数宣言 -->
<c:set var="sum_point" value="0" />


<c:forEach var="u" items="${ list }">

<!-- 送料の最大値をmax_deliにいれる処理 -->
<c:if test="${u.deli > max_deli}" >
<c:set var="max_deli" value="${u.deli}" />
</c:if>

<tr>
<td>${ u.cart_name }</td>
<td align="right">${ u.price }円</td>
<td align="right">${ u.point }point</td>

<td align="right">
<form action="CartServlet" method="post">
<input type="hidden"  name="cart_id" value="${ u.cart_id }" />
<input type="text" name="cart_num" value="${ u.cart_num }" size="1">個
<input type="hidden" name="ptn" value="1" />
<button type="submit">変更</button>
</form>
</td>

<td align="right">${ u.cart_num * u.price }円</td>
<td align="right">${ u.cart_num * u.point }point</td>

<!-- 合計金額を求める処理 -->
<c:set var="sum_price" value="${ (u.cart_num * u.price) + sum_price}" />
<!-- 合計ポイントを求める処理 -->
<c:set var="sum_point" value="${ (u.cart_num * u.point) + sum_point}" />


<td align="center">
<form action="CartServlet" method="post">
<input type="hidden" name="delete_cart_id" value="${ u.cart_id }" />
<input type="hidden" name="ptn" value="2" />
<button type="submit">削除</button>
</form>
</td>

</tr>
</c:forEach>


<tr><td colspan="7" bgcolor="red"></td></tr>


<tr align="center">
<td align="center" colspan="4">カート内合計（合計金額は送料${ max_deli }円を含む）</td>
<td align="right">${ sum_price + max_deli }円</td>
<td align="right">${ sum_point }point</td>

<!-- ポイントに応じてメッセージ -->
<td align="center">
<c:if test="${sum_point > 20}" >
NG!
</c:if>
<c:if test="${sum_point <= 20}" >
OK!
</c:if>
</td>

</tr>
</TABLE>

<br />

<form action="CartServlet" method="post">
<input type="hidden" name="sum_price" value="${ sum_price + max_deli }" />
<input type="hidden" name="sum_point" value="${ sum_point }" />
<input type="hidden" name="ptn" value="3" />
<button type="submit">購入</button>
</form>

<%
}
%>

</body>
</html>