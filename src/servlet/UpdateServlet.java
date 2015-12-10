package servlet;

import io.HorroDAO;
import io.HorrorDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		/**
		 * セッションを開始&取得
		 * セッション値：id
		 * */
		HttpSession session = request.getSession();
		String C_Id = (String) session.getAttribute("C_Id");
		C_Id = "katou";

		// 確認用コンソール表示
		System.out.println("入ったよ。 by UpdateServlet_doPost ");
		System.out.println("セッションの値(ID) by HistoryServlet:" + C_Id);

		// リストを受け取るHorroDAO：Historyから
		HorroDAO dao = new HorroDAO();
		dao.Update(C_Id, request);

		//完了メール
		//JMail.main(引数で2を渡す); 

		request.setAttribute("message", "登録しました。");
		request.getRequestDispatcher("./Update.jsp").forward(request, response);

	}
}
