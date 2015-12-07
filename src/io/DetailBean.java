package io;

public class DetailBean {
	private int P_ID;
	private String P_Name;
	private String P_Image;
	private String P_Maker;
	private int Rate_Price;
	
	public DetailBean(int p_ID, String p_Name, String p_Image,
			String p_Maker, int rate_Price) {
		P_ID = p_ID;
		P_Name = p_Name;
		P_Image = p_Image;
		P_Maker = p_Maker;
		Rate_Price = rate_Price;
	}

	public int getP_ID() {
		return P_ID;
	}

	public void setP_ID(int p_ID) {
		P_ID = p_ID;
	}

	public String getP_Name() {
		return P_Name;
	}

	public void setP_Name(String p_Name) {
		P_Name = p_Name;
	}

	public String getP_Image() {
		return P_Image;
	}

	public void setP_Image(String p_Image) {
		P_Image = p_Image;
	}

	public String getP_Maker() {
		return P_Maker;
	}

	public void setP_Maker(String p_Maker) {
		P_Maker = p_Maker;
	}

	public int getRate_Price() {
		return Rate_Price;
	}

	public void setRate_Price(int rate_Price) {
		Rate_Price = rate_Price;
	}
	
}
