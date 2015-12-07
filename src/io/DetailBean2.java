package io;

import java.sql.Date;

public class DetailBean2 {
	/** 注文番号 */
	private String Detaile_O_ID;
	/** 商品ID */
	private int Cart_P_ID;
	/** 商品個数 */
	private int Detail_P_Num;
	/** 注文時単価 */
	private int Detail_P_Price;
	/** 返却予定日 */
	private Date Return_Date;
	/** 返却フラグ */
	private String Return_Flag;
	/** 滞納フラグ */
	private int Delay_Flag;
	/** 赤伝 */
	private String Order_Red;
	
	
	public DetailBean2(String detaile_O_ID, int cart_P_ID, int detail_P_Num,
			int detail_P_Price, Date return_Date, String return_Flag,
			int delay_Flag, String order_Red) {
		Detaile_O_ID = detaile_O_ID;
		Cart_P_ID = cart_P_ID;
		Detail_P_Num = detail_P_Num;
		Detail_P_Price = detail_P_Price;
		Return_Date = return_Date;
		Return_Flag = return_Flag;
		Delay_Flag = delay_Flag;
		Order_Red = order_Red;
	}
	public String getDetaile_O_ID() {
		return Detaile_O_ID;
	}
	public void setDetaile_O_ID(String detaile_O_ID) {
		Detaile_O_ID = detaile_O_ID;
	}
	public int getCart_P_ID() {
		return Cart_P_ID;
	}
	public void setCart_P_ID(int cart_P_ID) {
		Cart_P_ID = cart_P_ID;
	}
	public int getDetail_P_Num() {
		return Detail_P_Num;
	}
	public void setDetail_P_Num(int detail_P_Num) {
		Detail_P_Num = detail_P_Num;
	}
	public int getDetail_P_Price() {
		return Detail_P_Price;
	}
	public void setDetail_P_Price(int detail_P_Price) {
		Detail_P_Price = detail_P_Price;
	}
	public Date getReturn_Date() {
		return Return_Date;
	}
	public void setReturn_Date(Date return_Date) {
		Return_Date = return_Date;
	}
	public String getReturn_Flag() {
		return Return_Flag;
	}
	public void setReturn_Flag(String return_Flag) {
		Return_Flag = return_Flag;
	}
	public int getDelay_Flag() {
		return Delay_Flag;
	}
	public void setDelay_Flag(int delay_Flag) {
		Delay_Flag = delay_Flag;
	}
	public String getOrder_Red() {
		return Order_Red;
	}
	public void setOrder_Red(String order_Red) {
		Order_Red = order_Red;
	}
	
	
}
