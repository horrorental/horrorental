<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.* ,io.Horro,java.util.List" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="HistoryServlet">
		注文履歴

		<table border="1">
			<tr>
				<td><center><b>顧客ID</b></center></td>
				<td><center><b>顧客名</b></center></td>
				<td><center><b>商品名</b></center></td>
				<td><center><b>商品個数</b></center></td>
			</tr>

			<c:forEach var="u" items="${ list }">		
				<tr>
					<td><center><b>${ u.c_ID }</b></center></td>
					<td><center><b>${ u.c_Name }</b></center></td>
					<td><center><b>${ u.p_Name }</b></center></td>
					<td><center><b>${ u.detail_P_Num }</b></center></td>
				</tr>

			</c:forEach>		
		</TABLE>
	</form>

	<p><input type="button" value="戻る" onClick="location.href='./Mypage.jsp'"></p>	
</body>
</html>