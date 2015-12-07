<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力確認ページ</title>
</head>
<body>

<h1>【以下の内容でよろしいですか？】</h1>
<table>
  	<tr><th>ID<th><td><%=request.getAttribute("C_ID") %></td></tr>	
	<tr><th>パスワード<th><td><%=request.getAttribute("C_PW") %></td></tr>
	<tr><th>名前<th><td><%=request.getAttribute("C_Name") %></td></tr>
	<tr><th>フリガナ<th><td><%=request.getAttribute("C_Yomi") %></td></tr>
	
	<% String C_SEX1 = request.getParameter("C_Sex"); %>
	<%
	    String msg="";
	    if(C_SEX1.equals("1")){ msg = "男"; }
	    if(C_SEX1.equals("0")){ msg = "女"; }
	%>
	<tr><th>性別<th><td><%=msg%></td></tr>
	<!-- 郵便番号3文字目に　「-」を追加する -->
	<%StringBuilder C_AddNo1 = new StringBuilder();
		C_AddNo1.append(request.getParameter("C_AddNo"));
		C_AddNo1.insert(3, "-");
		String C_AddNo = C_AddNo1.toString();
		request.setAttribute("C_AddNo",C_AddNo);  
	%>
	<tr><th>郵便番号<th><td>〒<%=request.getAttribute("C_AddNo") %></td></tr>
	<tr><th>住所<th><td><%=request.getAttribute("C_Address") %></td></tr>
	<tr><th>電話番号<th><td><%=request.getAttribute("C_Phone") %></td></tr>
	<tr><th>カード情報<th><td><%=request.getAttribute("C_Card") %></td></tr>
	<tr><th>メールアドレス<th><td><%=request.getAttribute("C_Mail") %></td></tr>	
</table>

<form action="InputConfirmationServlet" method="post">
	<INPUT TYPE="HIDDEN" NAME="C_ID" VALUE=<%=request.getAttribute("C_ID") %>>
	<INPUT TYPE="HIDDEN" NAME="C_PW" VALUE=<%=request.getAttribute("C_PW") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Name" VALUE=<%=request.getAttribute("C_Name") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Yomi" VALUE=<%=request.getAttribute("C_Yomi") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Sex" VALUE=<%=request.getAttribute("C_Sex") %>>
	<INPUT TYPE="HIDDEN" NAME="C_AddNo" VALUE=<%=request.getAttribute("C_AddNo") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Address" VALUE=<%=request.getAttribute("C_Address") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Phone" VALUE=<%=request.getAttribute("C_Phone") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Card" VALUE=<%=request.getAttribute("C_Card") %>>
	<INPUT TYPE="HIDDEN" NAME="C_Mail" VALUE=<%=request.getAttribute("C_Mail") %>>
	<INPUT TYPE="HIDDEN" NAME="menu" VALUE="2">
	<input type="button" value="戻る" onclick="history.back()">
	<button type="submit">この内容で登録する</button>
</form>
</body>
</html>