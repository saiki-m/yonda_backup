//「スッキリわかるサーブレット＆JSP入門」P279のコード10-5を参考

package servlet;

import java.io.IOException;
import java.sql.Date;

import beans.ProfileBean;
import dao.ProfileEditDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
  private static final long serialVersionUID = 1L; 
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  request.setCharacterEncoding("UTF-8");
	  String edit = request.getParameter("edit");
	  
	  //マイページの「プロフィール」ボタンを押したとき
	  if(edit == null) {
		  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
	      dispatcher.forward(request, response);   //フォワードはjspフォルダ内に置く
	  }
	  
	  //プロフィールページの「編集」ボタンを押したとき
	  else if(edit.equals("done")){
		  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profileEdit.jsp");
	      dispatcher.forward(request, response);   //フォワードはjspフォルダ内に置く
	  }
  }  

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  
	  String[] inputInfo = {"gender", "birthday", "profession", "prefectures", "keyword",
			  			"genru", "author", "book_1", "book_2", "book_3"};
	    
	    for(int i = 0; i < inputInfo.length; i++) {
	    	inputInfo[i] = request.getParameter(inputInfo[i]);
	    }
	    
//    String gender = request.getParameter("gender");
//	  String birthday = request.getParameter("birthday");
//	  String profession = request.getParameter("profession");
//	  String prefectures = request.getParameter("prefectures");
//	  String keyword = request.getParameter("keyword");
//	  String genru_1 = request.getParameter("genru_1");
//	  String genru_2 = request.getParameter("genru_2");
//	  String genru_3 = request.getParameter("genru_3");
//	  String author_1 = request.getParameter("author_1");
//	  String author_2 = request.getParameter("author_2");
//	  String author_3 = request.getParameter("author_3");
//	  String book_1 = request.getParameter("book_1");
//	  String book_2 = request.getParameter("book_2");
//	  String book_3 = request.getParameter("book_3");
	  
	  Date Birthday = java.sql.Date.valueOf(inputInfo[1]);
	    
	  ProfileBean profile = new ProfileBean(Birthday, inputInfo);
	  

	  ProfileEditDAO dao = new ProfileEditDAO();
	  dao.update(profile, account);
	  
	  
	  
  }
}