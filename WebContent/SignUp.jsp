<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!DOCTYPE html>
<html>
<head>
<script src="http://ajaxzip3.googlecode.com/svn/trunk/ajaxzip3/ajaxzip3.js" charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホラーレンタル　新規登録</title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8"); %>
<h1>■新規会員登録■</h1>

<form action="InputConfirmationServlet" method="post">
	<table>

		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>ID</b></th>
			<td><input type="text" style="ime-mode: disabled;" name="C_ID" value="${ C_ID }"><small>(5~15桁英数字)</small></td><td><p style="color: red">${ idErr }<p></td>
		</tr>
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>パスワード</b></th>
			<td><input type="text" name="C_PW" value="${ C_PW }" style="ime-mode: disabled;" /><small>(4~20桁英数字)</small></td><td><p style="color: red">${ pwErr }</p></td>
		</tr>
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>名前</b></th>	
			<td><input type="text" name="C_Name" value="${ C_Name }" style="ime-mode:active;"></td><td><p style="color: red">${ nameErr }</p></td>
		</tr>
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>フリガナ</b></th>
			<td><input type="text" name="C_Yomi" value="${ C_Yomi }" style="ime-mode:active;"></td><td><p style="color: red">${ yomiErr }</p></td>
		</tr>
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>性別</b></th>
			<td><input type="radio" name="C_Sex" value="1">男
			<input type="radio" name="C_Sex" value="0">女</td><td><p style="color: red">${ sexErr }</p></td></tr>
			
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>郵便番号</b></th>
			<td>〒<input type="text" style="ime-mode: disabled;"  name="C_AddNo" value="${ C_AddNo }" pattern="^[0-9]+$" title="※数字のみ入力できます" size="10" onKeyUp="AjaxZip3.zip2addr(this,'','C_Address','C_Address');">
			<small>(ハイフンなし)</small></td><td><p style="color: red">${ addnoErr }</p></td>
		</tr>
		
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>住所</b></th>
			<td><input type="text" name="C_Address" size="60"  value="${C_Address}" style="ime-mode:active;"></td><td><p style="color: red">${ addressErr }</p></td>
		</tr>
		
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>電話番号</b></th>
			<td><input type="tel" name="C_Phone" value="${C_Phone}" style="ime-mode:inactive;">
			<small>(ハイフンなし)</small></td><td><p style="color: red">${ phoneErr }</p></td>
		</tr>
			
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><b>　カード情報</b></th>
			<td><input type="text" name="C_Card"  value="${C_Card}" style="ime-mode:inactive;"></td><td><p style="color: red">${ cardErr }</p></td>
		</tr>
		
		<tr><th bgcolor="#f0f0f0" width="150" align="left"><small style="color: red">※</small><b>メールアドレス</b></th>
			<td><input type="text" name="C_Mail" value="${C_Mail}" style="ime-mode:inactive;"></td><td><p style="color: red">${ mailErr }</p></td>
		</tr>
	</table>
	<INPUT TYPE="HIDDEN" NAME="menu" VALUE="1">
	<br>
	<button type="submit">登録する</button>
</form>
</body>
</html>
