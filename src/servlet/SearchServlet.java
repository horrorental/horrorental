package servlet;

import io.HorrorDAO;
import io.Product;

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

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> list = new ArrayList<Product>();
		ArrayList<String> errMsg = new ArrayList<String>();
		boolean isErr = false;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	/*	
	 	セッション処理
	 	HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("id");
		if(id == null){
			System.out.println("セッションなし");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	*/
		String category = request.getParameter("category");
		String query = request.getParameter("query");
		
//		入力チェック
		Pattern p = Pattern.compile("^[0-6]");
		Matcher m = p.matcher(category);
		if(m.find() == false && !(category.equals("all"))){
			errMsg.add("異常なカテゴリが選択されました");
		}
		Pattern p2 = Pattern.compile("[!-/:-@≠\\[-`{-~]");
		m = p2.matcher(query);
		if(m.find()== true){
            errMsg.add("検索出来ない文字が含まれています");
		}
		if(query.length() > 50){
			errMsg.add("検索文字が長すぎます");
		}

//		入力エラーがあった場合
		if(isErr){
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}
		
//		正常な処理
		HorrorDAO dao = new HorrorDAO();
		list = dao.search(category, query);
		
		request.setAttribute("list", list);
		request.setAttribute("errMsg", errMsg);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
