package servlet;

import io.HorrorDAO;

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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);//Login.jspへ遷移
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id =request.getParameter("id");//IDを取得
		String login_pass =request.getParameter("login_pass");//PWを取得

		//書式チェック
		Pattern p = Pattern.compile("^[0-9a-zA-Z]+$");
	    Matcher m = p.matcher(id);
	    
	    String jsp = "Login.jsp"; 

		boolean isErr = false;
		
		//エラーメッセ―ジ処理
		//IDが６より小さくて１６より大きい場合のエラー
		if(id == null || (id.length() < 6 && id.length() > 16) || m.find()== false){
			isErr = true;
			request.setAttribute("idErr", "IDを正しく入力してください");
		}		
		//PWが入力されていない場合のエラー
		if(login_pass == null || login_pass.length() <= 0){
			isErr = true;
			request.setAttribute("passErr", "パスワードを入力してください");
		}		
		//ログインに失敗した場合はこれを表示する
		if(isErr){
			request.setAttribute("sendErr", "ログインに失敗しました");
			request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
			return;
		}
		
		login_pass = DigestUtils.sha256Hex(login_pass);//ハッシュ化
		
		
		Boolean isLogedin = false;
		HttpSession session = request.getSession();
		
		HorrorDAO dao = new HorrorDAO();
		
		String dbPassword = dao.selectPass(id);
		
		if(dao.getIsErr()){
			//セマフォファイルが存在していた場合（DBに異常がある場合）				
			jsp = "sinnderu.jsp";				
		}
		
		if(dbPassword != null && dbPassword.equals(login_pass)){
			//ログイン成功　 セッションを生成する(名前、ID)
			isLogedin = true;
			session.setAttribute("userName", dao.selectUser(id));
			session.setAttribute("id", id);
		}
		
		if (isLogedin){
			//ログイン成功時のURL
			request.getRequestDispatcher("Mypage2.jsp").forward(request, response);
		}else{
			//■ログイン失敗時　ログイン画面にフォワードする
			request.setAttribute("errMsg", "ログインに失敗しました<br>IDかパスワードが間違っています");
			session.removeAttribute("isLogedin");
			session.removeAttribute("id");
			session.removeAttribute("login_pass");
			request.getRequestDispatcher("WEB-INF/"+jsp).forward(request, response);
		}
	}
}