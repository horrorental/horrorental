package servlet;
import io.Horror;
import io.HorrorDAO;

import java.io.IOException;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InputConfirmationServlet
 */
@WebServlet("/InputConfirmationServlet")
public class InputConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//メニューの指定
		int menu = Integer.parseInt(request.getParameter("menu"));
		//ID
		String C_ID=request.getParameter("C_ID");  
		request.setAttribute("C_ID",C_ID);  
		//パスワード
		String C_PW=request.getParameter("C_PW");  
		request.setAttribute("C_PW",C_PW);  
		//名前
		String C_Name=request.getParameter("C_Name"); 
		request.setAttribute("C_Name",C_Name);  
		//フリガナ
		String C_Yomi=request.getParameter("C_Yomi");  
		request.setAttribute("C_Yomi",C_Yomi);  
		//性別
		String C_Sex=request.getParameter("C_Sex"); 
		request.setAttribute("C_Sex",C_Sex); 
		//郵便番号
		String C_AddNo=request.getParameter("C_AddNo");  
		request.setAttribute("C_AddNo",C_AddNo);  
		//住所
		String C_Address=request.getParameter("C_Address");  
		request.setAttribute("C_Address",C_Address);
		//電話番号
		String C_Phone=request.getParameter("C_Phone");
		request.setAttribute("C_Phone",C_Phone);
		//カード情報
		String C_Card=request.getParameter("C_Card");
		request.setAttribute("C_Card",C_Card);
		//メールアドレス
		String C_Mail=request.getParameter("C_Mail");
		request.setAttribute("C_Mail",C_Mail);
		
		// データ入力確認
		if(menu==1){
			response.setContentType("text/plain;charset=UTF-8");
			boolean isErr = false;
			//パターン処理
			//パターン　ID アルファベット&ナンバー 5文字以上15文字以下
			Pattern MAN1 = Pattern.compile("^[0-9a-zA-Z]{5,15}$");
			//パターン　PW アルファベット&ナンバー 4文字以上20文字以下
			Pattern MAN2 = Pattern.compile("^[0-9a-zA-Z]{4,20}$");
			//パターン　全角文字
			Pattern Z = Pattern.compile("^[^\\x00-\\x7F]");
			//パターン　カタカナ
			Pattern K = Pattern.compile("^[\\u30A0-\\u30FF]+$");
			//パターン　郵便番号　数字だけ　7文字限定
			Pattern N1 = Pattern.compile("^[0-9]{7}$");
			//パターン　数字だけ　11文字限定
			Pattern N2 = Pattern.compile("^[0-9]{11}$");
			//パターン　メールアドレス 英数字
			Pattern M = Pattern.compile("^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+"
					+ "(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$");

			//------------------------------------------------------------
			Matcher id = MAN1.matcher(C_ID);
			if(C_ID == null || id.find()== false){
			           isErr = true;
                       request.setAttribute("idErr", "×IDを正しく入力してください");
			 }
			
			Matcher pw = MAN2.matcher(C_PW);
			if(C_PW == null  || pw.find()== false){
				isErr = true;
				request.setAttribute("pwErr", "×パスワードを入力してください");
			}
			
			Matcher name = Z.matcher(C_Name);
			if(C_Name == null || name.find()== false){
				isErr = true;
				request.setAttribute("nameErr", "×名前を入力してください");
			}

			Matcher yomi = K.matcher(C_Yomi);
			if(C_Yomi == null || yomi.find()== false){
				isErr = true;
				request.setAttribute("yomiErr", "×フリガナを入力してください");
			}
			
			if(C_Sex == null || C_Sex.length() <= 0){
				isErr = true;
				request.setAttribute("sexErr", "×性別を選択してください");
			}

			Matcher addno = N1.matcher(C_AddNo);
			if(C_AddNo == null || addno.find()== false){
				isErr = true;
				request.setAttribute("addnoErr", "×郵便番号を入力してください");
			}
			
			if(C_Address == null || C_Address.length() <= 0){
				isErr = true;
				request.setAttribute("addressErr", "×住所を入力してください");
			}
			
			Matcher phone = N2.matcher(C_Phone);
			if(C_Phone == null || phone.find()== false){
				isErr = true;
				request.setAttribute("phoneErr", "×電話番号を入力してください");
			}
			/*if(C_Card == null || C_Card.length() <= 0){
				isErr = true;
				request.setAttribute("cardErr", "カード情報を入力してください");
			}*/
			
			Matcher mail = M.matcher(C_Mail);
			if(C_Mail == null || mail.find()== false){
				isErr = true;
				request.setAttribute("mailErr", "×メールアドレスを正しく入力してください");
			}	
			
			if(isErr){
				request.getRequestDispatcher("./SignUp.jsp").forward(request, response);
				return;
			}
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("./InputConfirm.jsp");
		
			dispatcher.forward(request,response);
		}
		
		//------------------ データ登録メニュー
		if(menu==2){
			response.setContentType("text/plain;charset=UTF-8");
			int ok =0;
			HorrorDAO dao1 = new HorrorDAO();
			ok = dao1.regist(C_ID);
			
			 //--------------------登録実行時の遷移の処理--------------------------------------------------
			 //ログイン成功時のURL
			 String sendUrl = null;				

			 if(ok == 1){
				 //失敗時の時の処理
				 sendUrl = "ErrRegist.jsp";
				 request.setAttribute("errMes", "×登録エラー");				 
			 }else{				 
				 	// データを入れる
					Date C_End = Date.valueOf("9999-12-31");
					Horror hrr = new Horror(C_ID,"1",C_PW,new Date(System.currentTimeMillis())
						,C_End,C_Name,C_Yomi,C_Sex,C_AddNo,C_Address,C_Phone,C_Card,C_Mail,new Date(System.currentTimeMillis()));
					HorrorDAO dao = new HorrorDAO();
					dao.insert(hrr);
				 sendUrl = "RegisterFinish.jsp";
				  request.setAttribute("sendErr", "会員登録が完了しました。");
		 }
			  request.getRequestDispatcher(sendUrl).forward(request, response);
						
			//request.setAttribute("sendErr", "完了【入力した社員を登録しました】");
			//request.getRequestDispatcher("RegisterFinish.jsp").forward(request, response);
		}
	}
}