//「スッキリわかるサーブレット＆JSP入門」P279のコード10-5を参考
//パスワード変更のための本人確認


package servlet;

import java.io.IOException;

import dao.RePassIdDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *   パスワード変更する前の本人確認画面
 */
@WebServlet("/RePassId")
public class RePassId extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/jsp/rePassId.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "WEB-INF/jsp/rePassId.jsp";
		
		// 入力した名前、メールアドレス、秘密の質問を取得
	    String name = request.getParameter("name");
	    String mailAd = request.getParameter("mailAd");
	    String secret_q = request.getParameter("secret_q");
	    
	    //数をスコープに保存するため、Integerにする。
		Integer accountID = new RePassIdDAO().findAccountID(name, mailAd, secret_q);
		
		if (accountID == 0) { 
			// アカウントIDが取得できなかったとき
			request.setAttribute("errorMsg", "本人確認できませんでした。すべての項目を正しく入力してください");		
		}
	    else {
	    	//アカウントIDを保存し、パスワード再設定する画面へ
	    	request.getSession().setAttribute("accountID", accountID);
	    	path = "WEB-INF/jsp/rePass.jsp";
	    }
	
		request.getRequestDispatcher(path).forward(request, response);    
	}
}