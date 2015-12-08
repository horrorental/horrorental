<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>メール送信フォーム</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</HEAD>
<BODY>
<FORM method="POST" action="owari.jsp">
<TABLE>
  <TR>
    <TD>お名前(必須)<br>
    <INPUT type="text" name="name" size=40><br></TD>
  </TR>
  <TR> <TD> </TD></TR><TR> <TD> </TD></TR>
  <TR>
    <TD>メールアドレス（必須）<br>
    <INPUT type="text" name="From" size=40><br></TD>
  </TR>
  <TR> <TD> </TD></TR><TR> <TD> </TD></TR><TR> <TD> </TD></TR>
  <td>お問い合わせはなんですか？はよこたえろ<br>
<input type="radio" name="q1" value="商品の損傷、損失">商品の損傷、損失<br>
<input type="radio" name="q1" value="店員の態度、言葉使い">店員の態度、言葉使い<br>
<input type="radio" name="q1" value="商品の発注、お届けについて">商品の発注、お届けについて<br>
<input type="radio" name="q1" value="ほしい商品について">ほしい商品について<br>
<input type="radio" name="q1" value="その他">その他<br>
</td>
<TR> <TD> </TD></TR><TR> <TD> </TD></TR><TR> <TD> </TD></TR>
  <TR> 
    <TD>どんな内容ですか？
    <br><TEXTAREA name="comment" rows=5 cols=45></TEXTAREA></TD>
  </TR>
  <TR>
    <TD><INPUT type="submit" value="送信">
    <INPUT type="reset" value="クリア"></TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</HTML>