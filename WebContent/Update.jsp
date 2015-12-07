<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajaxzip3.googlecode.com/svn/trunk/ajaxzip3/ajaxzip3.js" charset="UTF-8"></script>
<title>会員情報修正</title>
</head>
<body>

	<%
		//エラーメッセージを取得
		String errorMessage = (String)request.getAttribute("errMsg");
		
		//エラーメッセージが設定されて呼ばれているときには
		//エラーメッセージを表示する
		if (errorMessage != null){
	%>
	<%= errorMessage %>
	<%
	}
	%>
	<%
		String message = (String)request.getAttribute("message");
		if(message == null){
			message = "";
		}
		
	%>
	<p><%= message%></p><br/>



	修正画面<br/>
	
	<form action="UpdateServlet" method="post">

	<table >
		<tr><th bgcolor="#f0f0f0"><b>名前</b></th>	<td><input type="text" name="C_Name" value="${ C_Name }" style="ime-mode:active;"></td><td>${ nameErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>フリガナ</b></th><td><input type="text" name="C_Yomi" value="${ C_Yomi }" style="ime-mode:active;"></td><td>${ yomiErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>郵便番号</b></th><td>〒<input type="text" style="ime-mode: disabled;"  name="C_AddNo" value="${ C_AddNo }" pattern="^[0-9]+$" title="※数字のみ入力できます" size="10" onKeyUp="AjaxZip3.zip2addr(this,'','C_Address','C_Address');">
			※ハイフンなし		<td>${ addnoErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>住所</b></th><td><input type="text" name="C_Address" size="60"  value="${C_Address}" style="ime-mode:active;">${ addressErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>電話番号</b></th><td><input type="text" name="C_Phone" value="${C_Phone}" style="ime-mode:inactive;"></td><td>${ phoneErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>カード情報</b></th><td><input type="text" name="C_Card"  value="${C_Card}" style="ime-mode:inactive;"></td><td>${ cardErr }</td></tr>
		<tr><th bgcolor="#f0f0f0"><b>メールアドレス</b></th><td><input type="text" name="C_Mail" value="${C_Mail}" style="ime-mode:inactive;"></td><td>${ mailErr }</td></tr>
	</table>
	<input type="submit" value="変更">
	<input type="reset">
	<p><input type="button" value="戻る" onClick="location.href='./Mypage.jsp'"></p>	
	</form>



</body>
</html>