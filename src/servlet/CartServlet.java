package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.CartBean;
import io.CartBean3;
import io.HorrorDAO;

	/**
	Servlet implementation class CartServlet
	 */

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
//		String name = (String) session.getAttribute("name");
		
//		String id = "katou";
		
		String jsp = "Cart.jsp";
		
		HorrorDAO dao = new HorrorDAO();
		
		ArrayList<CartBean3> list = dao.select(id);
		
		if(dao.getIsErr()){
			//セマフォファイルが存在していた場合（DBに異常がある場合）
			jsp = "sinnderu.jsp";
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		//テストデータ
//		String id = "katou";
		String jsp = "Cart.jsp";
		
		request.setCharacterEncoding("utf-8");
		
		//処理のパターンを取得　ptn=1 更新　ptn=2 削除　ptn=3 購入　
		String ptnS = request.getParameter("ptn");
		
		int ptn = Integer.parseInt(ptnS);

		//■更新（個数変更処理）
		if(ptn==1){
			
			//アイテムの個数の取得
			String cart_numS = request.getParameter("cart_num");
			//カートidの取得
			String cart_idS = request.getParameter("cart_id");
			
			//■全角半角数値チェック
			Pattern p = Pattern.compile("^[0-9０-９]+$");
			Matcher m = p.matcher(cart_numS);
		
			boolean isErr = false;
		
			//■入力チェック
			if(cart_numS == null || cart_numS.length() <= 0 || cart_numS.length() >= 3 || cart_numS.equals("0")|| cart_numS.equals("０") || m.find()== false){
				isErr = true;
				request.setAttribute("cart_numErr", "正しい数値を入力してください");
			}
		
			//■入力エラー処理     DBにアクセスする前に注意を促したいため一度ここで書く必要がある
			if(isErr){
				HorrorDAO dao = new HorrorDAO();
				ArrayList<CartBean3> list = dao.select(id);
				request.setAttribute("list", list);
				request.setAttribute("sendMSG", "■更新に失敗しました");
				request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
				return;
			}
		
			//全角数字を半角数字に変換
			cart_numS=fullWidthNumberToHalfWidthNumber(cart_numS);
			//数字型にキャスト
			int cart_num = Integer.parseInt(cart_numS);
			int cart_id = Integer.parseInt(cart_idS);
			
			//インスタンスの生成
			HorrorDAO dao = new HorrorDAO();
			
			//■update処理
			dao.update(id,cart_id,cart_num);
			
			if(dao.getIsErr()){
				//セマフォファイルが存在していた場合（DBに異常がある場合）
				jsp = "sinnderu.jsp";
			}

			ArrayList<CartBean3> list = dao.select(id);
			
			if(dao.getIsErr()){
				//セマフォファイルが存在していた場合（DBに異常がある場合）
				jsp = "sinnderu.jsp";
			}
			
			request.setAttribute("list", list);
			request.setAttribute("sendMSG", "■個数の変更が完了しました");
			request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
		}

		//■削除処理
		if(ptn==2){
			
			//削除するidの取得
			String delete_cart_id = request.getParameter("delete_cart_id");

			//削除するカートidの型のキャスト
			int cart_id = Integer.parseInt(delete_cart_id);
			
			//インスタンスの生成
			HorrorDAO dao = new HorrorDAO();
			
			dao.delete(id,cart_id);
		
			if(dao.getIsErr()){
				//セマフォファイルが存在していた場合（DBに異常がある場合）
				jsp = "sinnderu.jsp";
			}
			
			ArrayList<CartBean3> list = dao.select(id);
			
			if(dao.getIsErr()){
				//セマフォファイルが存在していた場合（DBに異常がある場合）
				jsp = "sinnderu.jsp";
			}

			request.setAttribute("list", list);
			request.setAttribute("sendMSG", "■商品を削除しました");
			request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
		}
		
		
		//■購入チェック処理
		if(ptn==3){
			
			//合計ポイントの取得
			String sum_pointS = request.getParameter("sum_point");
			int sum_point = Integer.parseInt(sum_pointS);
			
			//合計金額の取得
			String sum_priceS = request.getParameter("sum_price");
			int sum_price = Integer.parseInt(sum_priceS);

			boolean isErr = false;
						
			//■ポイントチェック
			//合計ポイントが20ポイントを越した場合はNG
			if(sum_point>20){
				isErr = true;
				request.setAttribute("cart_numErr", "※一度にレンタルできる個数を超えています");
			}
			if(sum_point==0){
				isErr = true;
				request.setAttribute("cart_numErr", "※カートの中身がありません");
			}
			
			//インスタンスの生成
			HorrorDAO dao = new HorrorDAO();
					
			//■与信チェック
			ArrayList<Integer> yosin = dao.yosin(id);
			if(dao.getIsErr()){
				//セマフォファイルが存在していた場合（DBに異常がある場合）
				jsp = "sinnderu.jsp";
			}
			
			if(!isErr){
				//与信
				int max_yosin = yosin.get(0);
				//利用額
				int used = yosin.get(1);
				//与信残高
				int credit = max_yosin - used;

				//商品の合計金額(送料含む)が与信残高を超えた場合NG
				if(sum_price > credit){
					isErr = true;
					request.setAttribute("cart_numErr2", "※ご利用限度額を超えています");
				}
			}
			if(isErr){
				ArrayList<CartBean3> list = dao.select(id);
				if(dao.getIsErr()){
					//セマフォファイルが存在していた場合（DBに異常がある場合）
					jsp = "sinnderu.jsp";
				}
				request.setAttribute("list", list);
				request.setAttribute("sendMSG", "■購入処理に失敗しました");
				request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
				return;
			}
			
//			JMail.main(1);
			//チェックに引っかからなかった場合
			request.getRequestDispatcher("WEB-INF/UserCheck.jsp").forward(request, response);
			
		}
		
	}
	
	//■全角数字→半角数字変換メソッド
	public static String fullWidthNumberToHalfWidthNumber(String str) {
		if(str == null){
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ('０' <= c && c <= '９') {
				sb.setCharAt(i, (char) (c - '０' + '0'));
			}
		}
		return sb.toString();
	}

}
