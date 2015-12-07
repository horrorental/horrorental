package io;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HorrorDAO {
	private boolean isErr = false;
	
	//初期処理　※共通
	public boolean getIsErr(){
		return isErr;
	}
//	※共通
	public HorrorDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
//	※共通
	public Connection getConnection(){
		try{
			File fl = new File("C:/Users/いー/Desktop/Sample.txt");
			if(fl.exists()){
				System.out.println("セマフォファイルが存在します。DBに異常が発生。");
				isErr = true;
				return null;
			}else{	
				//通常の処理
				Context context = new InitialContext();
				DataSource ds = (DataSource) context
						.lookup("java:comp/env/Oracle_JDBC");
				Connection con = ds.getConnection();
				return con;
			}
		}catch(Exception e){
			File newfile = new File("C:/Users/いー/Desktop/Sample.txt");
			try {
				newfile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null ;
		}
	}
//	初期処理おわり　※共通
	
	//検索用メソッド　※中野
	public ArrayList<Product> search(String p_cat_id, String p_name){
		ArrayList<Product> list = new ArrayList<Product>();
		try{
			Connection con = getConnection();
			PreparedStatement ps = null;
//			カテゴリ&ワード検索
			if(!(p_cat_id.equals("all")) && !(p_name.equals(""))){	
				ps = con.prepareStatement("select * from product_mst where p_name like ? or p_maker like ? and p_cat_id = ?");
				ps.setString(1, "%" + p_name + "%");
				ps.setString(2, "%" + p_name + "%");
				ps.setString(3, p_cat_id);
			}
//			カテゴリ
			else if(!(p_cat_id.equals("all")) && (p_name == "")){	
				ps = con.prepareStatement("select * from product_mst where p_cat_id = ?");
				ps.setString(1,  p_cat_id);
			}
//			検索ワード
			else if((p_cat_id.equals("all")) && (p_name != "")){	
				ps = con.prepareStatement("select * from product_mst where p_name like ? or p_maker like ? ");
				ps.setString(1,  "%" + p_name + "%");
				ps.setString(2, "%" + p_name + "%");
			}
//			全商品検索
			else{
				ps = con.prepareStatement("select * from product_mst");
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("p_id");
				String name = rs.getString("p_name");
				String yomi = rs.getString("p_yomi");
				String cat_id = rs.getString("p_cat_id");
				switch(cat_id){
					case "1": cat_id = "CD"; break;
					case "2": cat_id = "DVD"; break;
					case "3": cat_id = "game"; break;
					case "4": cat_id = "book"; break;
					case "5": cat_id = "wear"; break;
					case "6": cat_id = "spot"; break;
				}
				String maker = rs.getString("p_maker");
				String image = rs.getString("p_image");
				Timestamp sale = rs.getTimestamp("p_sale");
				Timestamp ts = rs.getTimestamp("p_ts");
				list.add(new Product(id, name, yomi, cat_id, maker, image, sale, ts));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	}
//	※中野
//	詳細ページ用Bean作成
	public DetailBean makeDetail(int p_id){
		DetailBean db = null;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement
						("select P_name,P_Image,P_Maker,Rate_Price "
								+ "from Product_Mst,Category_Mst,Stock_Tbl,Rate_Tbl "
								+ "where Product_Mst.p_id = ? "
								+ "and Product_Mst.P_Cat_ID = Category_Mst.Cat_ID "
								+ "and Product_Mst.p_id = Stock_Tbl.Stock_P_ID "
								+ "and Stock_Tbl.Stock_Rate_ID = Rate_Tbl.Rate_ID");
				){
				ps.setInt(1, p_id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					String p_name = rs.getString("P_name");
					String p_Image = rs.getString("P_Image");
					String p_Maker = rs.getString("P_Maker");
					int rate_Price = rs.getInt("Rate_Price");
					db = new DetailBean(p_id, p_name, p_Image, p_Maker, rate_Price);
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return db;
	}
	
//	※中野
	public String addCart(int Cart_Num, String Cart_C_ID, int Cart_P_ID){
		int count = 0;
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
			try(
					PreparedStatement ps = con.prepareStatement(
							"update Cart_tbl set Cart_Num = ?  where Cart_C_ID = ? and Cart_P_ID = ?"
							);
					){
						ps.setInt(1, Cart_Num);
						ps.setString(2, Cart_C_ID);
						ps.setInt(3, Cart_P_ID);
						count = ps.executeUpdate();
						if(count == 0){
							PreparedStatement ps2 = con.prepareStatement(
									"insert into Cart_tbl values(?, ?, ?)"
									);
							ps2.setString(1, Cart_C_ID);
							ps2.setInt(2, Cart_P_ID);
							ps2.setInt(3, Cart_Num);
							ResultSet rs = ps2.executeQuery();
						}
			}catch(Exception e){
				e.printStackTrace();
			}
			return "" + count;
		}
	}
//	中野終わり
	
	/**
	 * 購入履歴をoracleサーバから取ってくるクラス
	 * @param セッションで受け取る値:SC_ID
	 * @return
	 */
//	※よしき
	public ArrayList<Horro> History(String SC_ID){
	
		// Userオブジェクトを入れるリスト
		ArrayList<Horro> list = new ArrayList<Horro>();

		// DBと接続する
		Connection con = getConnection();
		if(con == null){
			return null;
		}
		else
			try(
				// SQL実行クラスの取得
				PreparedStatement ps = con.prepareStatement("select * from Ic_mst i, Order_tbl o, O_Detail_tbl od, Product_mst p "
						+ "where i.C_id = o.Order_c_id "
						+ "and o.Order_id = od.Detail_o_id "
						+ "and od.Detail_p_id = p.p_id "
						+ "and c_id=? "
						+ "Order by Order_TS ASC");
				) {
	
				ps.setString(1,SC_ID);

				// SQLを実行し結果セットを取得
				ResultSet rs = ps.executeQuery();

				// 結果セットからデータを取り出す
				while(rs.next()){
					String C_ID = rs.getString("C_ID");// 顧客ID
//					System.out.println(C_ID);
					String C_Name = rs.getString("C_Name"); // 顧客氏名
//					System.out.println(C_Name);
					String P_Name = rs.getString("P_Name"); // 商品名
					int Detail_P_Num = rs.getInt("Detail_P_Num"); // 商品個数
					// リストに追加
					list.add(new Horro(C_ID, C_Name, P_Name, Detail_P_Num));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			// リストを返す
			return list;
		}
//	※よしきおわり
	


//	※吉田
	/**
	 * 個人・法人・顧客テーブルからメールアドレスを取得する
	 * @return メールアドレス
	 */
	public String select2(String id){
		// Userオブジェクトを入れるリスト
		String Mail_adress=null;
		try(
			// 接続クラスの取得
			Connection con = getConnection();
			// SQL実行クラスの取得
			PreparedStatement ps = con.prepareStatement("select c_mail from IC_MST where C_ID = ? UNION select c_mail from cc_mst where c_id = ?");) {


			ps.setString(1, id);
			ps.setString(2, id);
			// SQLを実行し結果セットを取得
			ResultSet rs = ps.executeQuery();

			// 結果セットからデータを取り出す
			while(rs.next()){
				Mail_adress = rs.getString("C_Mail");
				System.out.println(Mail_adress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// リストを返す
		return Mail_adress;
	}
//	※吉田終わり
	
//	※黒崎
//	挿入
	public int insert(String id, String card, Date now){
		 int count = 0;
		 Date tommorow = new Date(System.currentTimeMillis());
//		 tommorow.setDate(tommorow.getDate()+1);
		 try (
				 Connection con = getConnection();
				 PreparedStatement ps = con.prepareStatement("insert into Order_Tbl values(Order_ID_seq.nextval,?,?,Order_C_Card_seq.nextval,?,?,?)");
		 ){
		   ps.setString(1,id);
		   ps.setString(2,card);
		   ps.setDate(3,now);
		   ps.setDate(4,now);
		   ps.setDate(5,now);
		   count = ps.executeUpdate();
		   
		} catch (SQLException e) {
				e.printStackTrace();
		}
		 return count;
	 }

//	黒崎
	/**
	 * カートデータを削除します。
	 * @return 
	 */
	public int delete(String id){
		int count = 0;
		try (
		// 接続クラスの取得
		Connection con = getConnection();
		// SQL実行クラスの取得
		PreparedStatement ps = con.prepareStatement("delete from Cart_tbl where Cart_C_ID=?");) {
			// SQLを実行し結果セットを取得
			ps.setString(1, id);
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}
	
//	※黒崎
	public ArrayList<PayBean> getCart(String cart_C_ID){
		ArrayList<PayBean> list = new ArrayList();
		// 接続クラスの取得		
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
	
			try (				
					// SQL実行クラスの取得
					PreparedStatement ps = con.prepareStatement("select * from Cart_tbl where Cart_C_ID = ?");) {
				// SQLを実行し結果セットを取得
				ps.setString(1, cart_C_ID);
				ResultSet rs = ps.executeQuery();
			
				// 結果セットからデータを取り出す
				while (rs.next()) {
					String Cart_C_ID = rs.getString("Cart_C_ID");
					int Cart_P_ID = rs.getInt("Cart_P_ID");
					int cart_num = rs.getInt("Cart_Num");
					list.add(new PayBean(Cart_C_ID,Cart_P_ID,cart_num));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}		
	}
	
//	黒崎
	public ArrayList<DetailBean2> makeDetail(String id, ArrayList<PayBean> cart){
		ArrayList<DetailBean2> list = new ArrayList();
		Iterator it = cart.iterator();
		// 接続クラスの取得		
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
	
			try {				
				String Order_ID = null;
				Date Order_Date = null;
				int Rate_Price = 0;
				while(it.hasNext()){
					PayBean pb = (PayBean) it.next();
					// SQL実行クラスの取得
					PreparedStatement ps = con.prepareStatement("select Order_ID,Order_Date from Order_Tbl where Order_C_ID = ?");
					// SQLを実行し結果セットを取得
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					// 結果セットからデータを取り出す
					while (rs.next()) {
						Order_ID = rs.getString("Order_ID");
						Order_Date = rs.getDate("Order_Date");
					}
						// SQL実行クラスの取得
					PreparedStatement ps2 = con.prepareStatement("select Rate_Price from Rate_Tbl where Cart_P_ID = ? "
							+ "and Stock_Tbl.Stock_P_ID = Rate_Tbl.Rate_ID");
					
					// SQLを実行し結果セットを取得
					ps2.setString(1, id);
					ResultSet rs2 = ps.executeQuery();
					// 結果セットからデータを取り出す
					while (rs.next()) {
						Rate_Price = rs.getInt("Rate_Price");
					}
					list.add(new DetailBean2(Order_ID, (int)(pb.getCart_P_ID()),(int)(pb.getCart_num()) , Rate_Price, Order_Date, "1", 0, "1"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}		
	}
	
//	※黒崎
	public String insertToOrderDetail(ArrayList<DetailBean2> list ){
		int count = 0;
		Iterator<DetailBean2> it = list.iterator();
		try {
			// 接続クラスの取得
			Connection con = getConnection();
			// SQL実行クラスの取得
			while(it.hasNext()){
				DetailBean2 db = it.next();
				PreparedStatement ps = con.prepareStatement("insert into O_Detail_Tbl values (?,?,?,?,?,?,?,?)");
				// SQLを実行し結果セットを取得
				ps.setString(1,db.getDetaile_O_ID());
				ps.setInt(2,db.getCart_P_ID());
				ps.setInt(3,db.getDetail_P_Num());
				ps.setInt(4,db.getDetail_P_Price());
				ps.setDate(5,db.getReturn_Date());
				ps.setString(6,db.getReturn_Flag());
				ps.setInt(7,db.getDelay_Flag());
				ps.setString(8,db.getOrder_Red());
				
				count = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "" + count;
	}
//	※黒崎おわり
	
//	※山崎
	public String selectPass(String id) {
		String pass = null;
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
			try (
			// SQL実行クラスの取得
			PreparedStatement ps = con.prepareStatement
			("select C_PW from IC_MST where C_ID=? union select C_PW from CC_MST where C_ID = ?");)
			{
				// SQLを実行し結果セットを取得
				ps.setString(1, id);
				ps.setString(2, id);
				ResultSet rs = ps.executeQuery();
				
				// 結果セットからデータを取り出す
				while (rs.next()) {
					pass = rs.getString("C_PW");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return pass;
		}
	}
	
//	※山崎
	public String selectUser(String id) {
		String name = null;
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
			try (// SQL実行クラスの取得
				 PreparedStatement ps = con.prepareStatement
				 ("select C_Name from IC_MST where C_ID=? union select C_Name from CC_MST where C_ID = ?"); )
			{
			
				// SQLを実行し結果セットを取得
				ps.setString(1, id);
				ps.setString(2, id);
				ResultSet rs = ps.executeQuery();
				
				// 結果セットからデータを取り出す
				while (rs.next()) {
					name = rs.getString("C_Name");
				}
				
			} catch (SQLException e) {
					e.printStackTrace();
			}
			return name;
		}
	}
	
//	※生駒
	/**
	 * カートデータを取得します。
	 * @return マイカートデータ
	 */
	public ArrayList<CartBean3> select(String id) {
		ArrayList<CartBean3> list = new ArrayList<>();
		// 接続クラスの取得
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{

			try (				
					// SQL実行クラスの取得
					PreparedStatement ps = con.prepareStatement("select * from Cart_tbl,Product_Mst,Stock_Tbl,Rate_Tbl,Category_Mst "
							+ "where Cart_tbl.Cart_P_ID = Product_Mst.P_ID "
							+ "and Product_Mst.P_ID = Stock_Tbl.Stock_P_ID "
							+ "and Stock_Tbl.Stock_Rate_ID = Rate_Tbl.Rate_ID "
							+ "and Product_Mst.P_Cat_ID = Category_Mst.Cat_ID "
							+ "and Cart_C_ID = ? ORDER BY Cart_P_ID");) {
				
				// SQLを実行し結果セットを取得
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
			
				// 結果セットからデータを取り出す
				while (rs.next()) {
					int cart_id = rs.getInt("Cart_P_ID");
					int cart_num = rs.getInt("Cart_Num");
					String cart_name = rs.getString("P_Name");
					String cat_id = rs.getString("P_Cat_ID");
					int price = rs.getInt("Rate_Price");
					int deli = rs.getInt("Rate_Deliver");
					int point = rs.getInt("Cat_PoNumber");
					list.add(new CartBean3(id,cart_id,cart_name,cart_num,cat_id,price,deli,point));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}
	}
	
	/**
	 * 与信データを取得します。
	 * @return 与信データ
	 */

//	※生駒
	public ArrayList<Integer> yosin(String id) {
		ArrayList<Integer> yosin = new ArrayList<>();
		int Credit_Money = 0,Credit_Now = 0;
		// 接続クラスの取得
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
			try (
					// SQL実行クラスの取得
					PreparedStatement ps = con.prepareStatement("select * from Credit_Tbl where Credit_C_ID = ? ");) {
				// SQLを実行し結果セットを取得
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
			
				// 結果セットからデータを取り出す
				while (rs.next()) {
					Credit_Money = rs.getInt("Credit_Money");
					Credit_Now = rs.getInt("Credit_Now");
				}
			
				if(Credit_Money==0){
					Credit_Money = 999999999;
					Credit_Now = 0;
				}
			
				//与信リストに追加
				yosin.add(Credit_Money);
				yosin.add(Credit_Now);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return yosin;
		}
	}
	

//	※生駒
	/**
	 * カートの更新
	 * @param 
	 * @return カート更新で１、失敗したら２
	 */
	public String update(String id,int cart_id,int cart_num ){
		int count = 0;
		
		// 接続クラスの取得
		Connection con = getConnection();			
		if(con == null){
			return null;
		}else{
			try (
				// SQL実行クラスの取得
				PreparedStatement ps = con.prepareStatement("update Cart_tbl set Cart_Num = ?  where Cart_C_ID = ? and Cart_P_ID = ?");) {
				// SQLを実行し結果セットを取得
				ps.setInt(1, cart_num);
				ps.setString(2,id);
				ps.setInt(3,cart_id);
				count = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return String.valueOf(count);
		}
	}

	/**
	 * カートデータを削除します。
	 * @return 
	 */
//	※生駒
	public String delete(String id,int cart_id){
		int count = 0;
		// 接続クラスの取得
		Connection con = getConnection();
		if(con == null){
			return null;
		}else{
			try (
				// SQL実行クラスの取得
				PreparedStatement ps = con.prepareStatement("delete from Cart_tbl where Cart_C_ID = ? and Cart_P_ID= ?");) {
				// SQLを実行し結果セットを取得
				ps.setString(1, id);
				ps.setInt(2, cart_id);
				count = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return String.valueOf(count);
		}
	}

	
//	高橋※
	public int insert(Horror hrr){
		 int count = 0;
		 try (
				 Connection con = getConnection();
				 PreparedStatement ps = con.prepareStatement("insert into IC_MST values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 ){
		   ps.setString(1,hrr.getC_ID());
		   ps.setString(2,hrr.getC_Cat());
		   ps.setString(3,hrr.getC_PW());
		   ps.setDate(4,hrr.getC_Start());
		   ps.setDate(5,hrr.getC_End());
		   ps.setString(6,hrr.getC_Name());
		   ps.setString(7,hrr.getC_Yomi());
		   ps.setString(8,hrr.getC_Sex());
		   ps.setString(9,hrr.getC_AddNo());
		   ps.setString(10,hrr.getC_Address());
		   ps.setString(11,hrr.getC_Phone());
		   ps.setString(12,hrr.getC_Card());
		   ps.setString(13,hrr.getC_Mail());
		   ps.setDate(14,hrr.getC_TS());
		   count = ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		} 
		 return count;
	 }
	
	//登録時IDが重複しているかチェックする
	public int regist(String C_ID) {
		int ok=0;
		String db_C_ID = null;
		//参照
		try (
			 Connection con = getConnection();
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM IC_MST where C_ID=?");
			PreparedStatement ps = con.prepareStatement("SELECT C_ID FROM IC_MST where C_ID= ? UNION SELECT C_ID FROM CC_MST where C_ID = ?");
				
		){
			ps.setString(1,C_ID);
			ps.setString(2,C_ID);
			ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					db_C_ID = rs.getString("C_ID");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//入力したIDがDBに入っていたらokにをいれる
		if(C_ID.equals(db_C_ID)){
			ok=1;
		}
		return ok;
	 }
}
