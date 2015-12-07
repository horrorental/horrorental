package io;

/**
 * 商品テーブルに対応したクラス
 *
 * @author ikoma
 */
public class PayBean {
	/** 顧客ID */
	private String Cart_C_ID;
	/** 商品ID */
	private int Cart_P_ID;
	/** 個数 */
	private int cart_num;
	
	
	/**
	 *
	 */
	public PayBean(){}

	/**
	 *
	 * @param Cart_C_ID 顧客ID
	 * @param Cart_P_ID　商品ID
	 * @param cart_num　個数
	 */
	public PayBean(String Cart_C_ID,int Cart_P_ID,int cart_num) {
		this.Cart_C_ID = Cart_C_ID;
		this.Cart_P_ID = Cart_P_ID;
		this.cart_num = cart_num;
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

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	
}
