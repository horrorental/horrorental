package servlet;

import java.io.IOException;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







import org.apache.commons.codec.digest.DigestUtils;

import io.HorrorDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/UserCheckServlet")
public class UserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* * @see HttpServlet#HttpServlet()
	*/
	public UserCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/UserCheck.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
//		String id = "katou";
		
		//■全角文字列を取扱い可能とする
		request.setCharacterEncoding("utf-8");
		//■リクエスト情報からパラメータを取得
		String pass = 	request.getParameter("pass"); // パスワードの取得

		pass = DigestUtils.sha256Hex(pass);//ハッシュ化
		
		Pattern p = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher m = p.matcher(pass);
		
		boolean isErr = false;

		//エラーメッセ―ジ処理
		
		if(pass == null || pass.length() <= 0 || m.find()== false){
			isErr = true;
			request.setAttribute("passErr", "パスワードを正しく入力してください");
		}
		
		if(isErr){
			request.setAttribute("sendErr", "認証に失敗しました");
			request.getRequestDispatcher("WEB-INF/UserCheck.jsp").forward(request, response);
			return;
		}
 		
		Boolean isLogedin = false;

		
		HorrorDAO dao = new HorrorDAO();
		String dbPassword = dao.selectPass(id);
		
		if(dbPassword != null && dbPassword.equals(pass)){
			//ログイン成功
			isLogedin = true;
		}
					
		
		if (isLogedin){
		//■認証成功時　十亀画面にフォワードする
			request.getRequestDispatcher("cardNumber.jsp").forward(request, response);
				
		}else{
			//■ログイン失敗時　ログイン画面にフォワードする
			request.setAttribute("errMsg", "パスワードが間違っています");
			request.getRequestDispatcher("WEB-INF/UserCheck.jsp").forward(request, response);
		}
				
	}


}
