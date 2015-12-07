package io;

import java.sql.Date;

public class Horror {
	/**
	 * 会員情報テーブルに対応したクラス
	 *
	 */
		/** ＩＤ */
		private String C_ID;
		/**顧客種類*/
		private String C_Cat;
		/** ログインパスワード */
		private String C_PW;
		/**適用開始日*/
		private Date C_Start;
		/**適用終了日*/
		private Date C_End;
		/** 名前 */
		private String C_Name;
		/** フリガナ */
		private String C_Yomi;
		/** 性別 */
		private String C_Sex;
		/** 郵便番号 */
		private String C_AddNo;
		/** 住所 */
		private String C_Address;
		/** 電話番号 */
		private String C_Phone;
		/** カード情報 */
		private String C_Card;
		/** メールアドレス */
		private String C_Mail;
		/** タイムスタンプ */
		private Date C_TS;	
	
 
 public Horror(String C_ID,String C_Cat,String C_PW,Date C_Start,Date C_End,String C_Name,String C_Yomi, String C_Sex,
		 String C_AddNo,String C_Address,String C_Phone,String C_Card,String C_Mail,Date C_TS) {
	  super();
	  this.C_ID = C_ID;
	  this.C_Cat = C_Cat;
	  this.C_PW = C_PW;
	  this.C_Start = C_Start;
	  this.C_End = C_End;	  
	  this.C_Name = C_Name;
	  this.C_Yomi = C_Yomi;
	  this.C_Sex = C_Sex;
	  this.C_AddNo = C_AddNo;
	  this.C_Address = C_Address;
	  this.C_Phone = C_Phone;
	  this.C_Card = C_Card;
	  this.C_Mail = C_Mail;
	  this.C_TS = C_TS;
 }
	public String getC_ID() {
		return C_ID;
	}

	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}

 	public String getC_Cat() {
	return C_Cat;
 	}
 	public void setC_Cat(String c_Cat) {
	C_Cat = c_Cat;
	}

	public String getC_PW() {
		return C_PW;
	}

	public void setC_PW(String c_PW) {
		C_PW = c_PW;
	}

	public Date getC_Start() {
		return C_Start;
	}
	public void setC_Start(Date c_Start) {
		C_Start = c_Start;
	}
	
	public Date getC_End() {
		return C_End;
	}
	public void setC_End(Date c_End) {
		C_End = c_End;
	}	
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}

	public String getC_Yomi() {
		return C_Yomi;
	}

	public void setC_Yomi(String c_Yomi) {
		C_Yomi = c_Yomi;
	}

	public String getC_Sex() {
		return C_Sex;
	}

	public void setC_Sex(String c_Sex) {
		C_Sex = c_Sex;
	}

	public String getC_AddNo() {
		return C_AddNo;
	}

	public void setC_AddNo(String c_AddNo) {
		C_AddNo = c_AddNo;
	}

	public String getC_Address() {
		return C_Address;
	}

	public void setC_Address(String c_Address) {
		C_Address = c_Address;
	}

	public String getC_Phone() {
		return C_Phone;
	}

	public void setC_Phone(String c_Phone) {
		C_Phone = c_Phone;
	}

	public String getC_Card() {
		return C_Card;
	}

	public void setC_Card(String c_Card) {
		C_Card = c_Card;
	}

	public String getC_Mail() {
		return C_Mail;
	}

	public void setC_Mail(String c_Mail) {
		C_Mail = c_Mail;
	}
	public Date getC_TS() {
		return C_TS;
	}
	public void setC_TS(Date c_TS) {
		C_TS = c_TS;
	}
}