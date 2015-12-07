package io;

/**
 * 商品テーブルに対応したクラス
 *
 * @author ikoma
 */
public class CartBean3 {
	/** ＩＤ */
	private String id;
	/** カテゴリid */
	private String cat_id;
	/** 商品ID */
	private int cart_id;
	/** 商品名 */
	private String cart_name;
	/** 個数 */
	private int cart_num;
	/** 単価 */
	private int price;
	/** 送料 */
	private int deli;
	/** ポイント */
	private int point;
	
	/**
	 *
	 * @param id ID
	 * @param cart_id　商品id
	 * @param cart_num　個数
	 */
	public CartBean3(String id,int cart_id,String cart_name,int cart_num,String cat_id,int price,int deli,int point) {
		this.id = id;
		this.cart_id = cart_id;
		this.cart_name = cart_name;
		this.cart_num = cart_num;
		this.cat_id = cat_id;
		this.price = price;
		this.deli = deli;
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public String getCart_name() {
		return cart_name;
	}

	public void setCart_name(String cart_name) {
		this.cart_name = cart_name;
	}

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDeli() {
		return deli;
	}

	public void setDeli(int deli) {
		this.deli = deli;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	

}
