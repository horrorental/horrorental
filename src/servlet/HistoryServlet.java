package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.Horro;
import io.HorrorDAO;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		/**
		 * セッションを開始&取得
		 * セッション値：id
		 * */
		HttpSession session = request.getSession();
		String C_ID = (String) session.getAttribute("id");
		
		// 確認用コンソール表示
//		System.out.println("入ったよ。 by doGet ");
//		System.out.println("セッションの値(ID)：" + C_ID);

		// リストを受け取るHorroDAO：Historyから
		HorrorDAO dao = new HorrorDAO();
		ArrayList<Horro> list = dao.History(C_ID);
	
		// 確認用コンソール表示
//		System.out.println("List中身" + list);
		
		// リストをhistory.jspにフォワード
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/History.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}


