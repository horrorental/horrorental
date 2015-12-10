package servlet;

import io.DetailBean;
import io.HorrorDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		//kurosaki sakusei
		
		String msg = null;
		String flg = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
			
	 	//セッション処理
	 	HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("id");
		
		
		String s1 = (String)request.getParameter("Cart_Num");
//		String Cart_C_ID = (String)request.getParameter("Cart_C_ID");
		String s2 = (String)request.getParameter("Cart_P_ID");
		int Cart_Num = Integer.parseInt(s1);
		int Cart_P_ID = Integer.parseInt(s2);
		if(Cart_Num < 0 || Cart_Num > 99){
			msg = "商品の個数が多すぎます";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("productDetail.jsp").forward(request, response);
		}
		
		
		HorrorDAO dao = new HorrorDAO();
		flg = dao.addCart(Cart_Num, id, Cart_P_ID);
		if(flg == null){
			msg = "エラーが発生しました";
			request.getRequestDispatcher("top.jsp").forward(request, response);
		}else{
			msg = "商品がカートに追加されました";
		}
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("productDetail.jsp").forward(request, response);
	}
}
