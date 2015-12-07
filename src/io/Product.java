package io;

import java.util.Date;

public class Product {
	private int P_ID;
	private String P_Name;
	private String P_Yomi;
	private String P_CatID;
	private String P_Maker;
	private String P_Image;
	private Date P_Sale;
	private Date P_TS;
	
	public Product(int P_ID, String P_Name, String P_Yomi, String P_CatID, String P_Maker, String P_Image, Date P_Sale, Date P_TS){
		this.P_ID = P_ID;
		this.P_Name = P_Name;
		this.P_Yomi = P_Yomi;
		this.P_CatID = P_CatID;
		this.P_Maker = P_Maker;
		this.P_Image = P_Image;
		this.P_Sale = P_Sale;
		this.P_TS = P_TS;
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
	public String getP_Yomi() {
		return P_Yomi;
	}
	public void setP_Yomi(String p_Yomi) {
		P_Yomi = p_Yomi;
	}
	public String getP_CatID() {
		return P_CatID;
	}
	public void setP_CatID(String p_CatID) {
		P_CatID = p_CatID;
	}
	public String getP_Maker() {
		return P_Maker;
	}
	public void setP_Maker(String p_Maker) {
		P_Maker = p_Maker;
	}
	public String getP_Image() {
		return P_Image;
	}
	public void setP_Image(String p_Image) {
		P_Image = p_Image;
	}
	public Date getP_Sale() {
		return P_Sale;
	}
	public void setP_Sale(Date p_Sale) {
		P_Sale = p_Sale;
	}
	public Date getP_TS() {
		return P_TS;
	}
	public void setP_TS(Date p_TS) {
		P_TS = p_TS;
	}
	
}