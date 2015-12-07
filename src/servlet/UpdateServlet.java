package servlet;

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
		//■全角文字列を取扱い可能とする
		request.setCharacterEncoding("utf-8");		
		HorrorDAO dao = new HorrorDAO();

		// セッション情報
		
		HttpSession session = request.getSession();
		String C_Id = (String) session.getAttribute("id");
		
		// ↓ここ変更/////////////////////
		String C_Name = request.getParameter("C_Name"); // 顧客名の取得
		String C_Yomi = request.getParameter("C_Yomi"); // フリガナの取得
		String C_AddNo = request.getParameter("C_AddNo"); // フリガナの取得
		String C_Address = request.getParameter("C_Address"); // フリガナの取得
		String C_Phone = request.getParameter("C_Phone"); // フリガナの取得
		String C_Card = request.getParameter("C_Card"); // フリガナの取得
		String C_Mail = request.getParameter("C_Mail"); // フリガナの取得
		
		// SQL文
		String sql_id = "UPDATE IC_MST SET C_Name=?,C_Yomi=?,C_AddNo=?,C_Address=?,C_Phone=?,C_Card=?,C_Mail=? WHERE C_ID=?";

		//////////////////////////////
		
		//■SQLの実行
		Connection con = null;
		PreparedStatement ps = null;
		CachedRowSetImpl crs = null;
		
		try{
			//データベースとのコネクションを取得
			con = dao.getConnection();

			// ↓ここ変更//////////////////////////////////
			//SQL文をプリコンパイルしてプリペアードステートメントを作成
			ps = con.prepareStatement(sql_id);
			//パラメータをセット
			ps.setString(1,C_Name);
			ps.setString(2,C_Yomi);
			ps.setString(3,C_AddNo);
			ps.setString(4,C_Address);
			ps.setString(5,C_Phone);			
			ps.setString(6,C_Card);			
			ps.setString(7,C_Mail);			
			ps.setString(8,C_Id);

			///////////////////////////////////////////

			//SQLを実行して結果を取得
			ResultSet rs = ps.executeQuery();
			//結果をキャッシュ
			crs = new CachedRowSetImpl();
			
		}catch(SQLException e){
		    throw new ServletException(e);
		}finally{
		    if(ps != null){
		        try{
		        	//ステートメントを閉じる
		        	ps.close();
		        }catch(SQLException e){
		        	//処理なし
		        }
		    }
		    if(con != null){
		        try{
		        	//コネクションを閉じる
		        	con.close();
		        }catch(SQLException e){
		        	//処理なし
		        }
		    }
		}
		request.setAttribute("message", "登録しました。");		
		request.setAttribute("crs", crs);
		request.getRequestDispatcher("./Update.jsp").forward(request, response);
	}
}
