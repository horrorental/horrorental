package io;
// ioioioio
public class CartBean {
	private String Cart_C_ID;
	private int Cart_P_ID;
	private int Cart_Num;
	
	public CartBean(String cart_C_ID, int cart_P_ID, int cart_Num) {
		Cart_C_ID = cart_C_ID;
		Cart_P_ID = cart_P_ID;
		Cart_Num = cart_Num;
	}

	public String getCart_C_ID() {
		return Cart_C_ID;
	}

	public void setCart_C_ID(String cart_C_ID) {
		Cart_C_ID = cart_C_ID;
	}

	public int getCart_P_ID() {
		return Cart_P_ID;
	}

	public void setCart_P_ID(int cart_P_ID) {
		Cart_P_ID = cart_P_ID;
	}

	public int getCart_Num() {
		return Cart_Num;
	}

	public void setCart_Num(int cart_Num) {
		Cart_Num = cart_Num;
	}
}
