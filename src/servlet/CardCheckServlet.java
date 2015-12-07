package servlet;

import io.DetailBean;
import io.DetailBean2;
import io.PayBean;
import io.HorrorDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class CardCheckServlet
 */
@WebServlet("/CardCheckServlet")
public class CardCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardCheckServlet() {
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
		response.setContentType("texth/html; UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String url = "/thanks.jsp";
		String card = request.getParameter("Card");
		
		Date now = new Date(System.currentTimeMillis());
		ArrayList<DetailBean2> list = new ArrayList<DetailBean2>();
		
		
		try{			
			int number = Integer.parseInt(request.getParameter("number"));
			request.setAttribute("number",String.valueOf(number));		
			//Randomクラスの生成
			Random r = new Random();
			boolean b = r.nextBoolean();	//true、falseいずれかを取得する
			//falseの場合
			b= true;
			if(b == false){
				request.setAttribute("errMes", "登録に失敗しました。");
				url = "/cardNumber.jsp";
			//trueの場合
			}else if(b == true){
				url = "/thanks.jsp";
				/*
				HttpSession session = request.getSession();
				String id = (String) session.getAttribute("id");
				id  = "katou";
				*/
				//データを入れる
				HorrorDAO dao = new HorrorDAO();
				dao.insert(id, card, now);
				ArrayList<PayBean> cart = dao.getCart(id);
				list = dao.makeDetail(id,cart);
				dao.insertToOrderDetail(list);
				dao.delete(id);
				
			}	
		}
			catch(NumberFormatException e){
				request.setAttribute("errMes", "数値をいれてください。");
				url = "/cardNumber.jsp";
		}
//		jspから"card"という名前でカード種別を取得
//		String card = request.getParameter("card"");	
		
		request.getRequestDispatcher(url)
			.forward(request, response);
	}


}
