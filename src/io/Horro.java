package io;

public class Horro {
	/** 顧客ID*/
	private String C_ID;
	/** 顧客名*/
	private String C_Name;
	/** 商品名 */
	private String P_Name;
	/** 商品個数*/
	private int Detail_P_Num;
	
	public Horro(String C_ID, String C_Name, String P_Name, int Detail_P_Num) {
		this.C_ID = C_ID;
		this.C_Name = C_Name;
		this.P_Name = P_Name;
		this.Detail_P_Num = Detail_P_Num;

	}

	public String getC_ID() {
		return C_ID;
	}

	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}

	public String getC_Name() {
		return C_Name;
	}

	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}

	public String getP_Name() {
		return P_Name;
	}

	public void setP_Name(String p_Name) {
		P_Name = p_Name;
	}

	public int getDetail_P_Num() {
		return Detail_P_Num;
	}

	public void setDetail_P_Num(int detail_P_Num) {
		Detail_P_Num = detail_P_Num;
	}
	
	
}