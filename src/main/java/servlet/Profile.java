//「スッキリわかるサーブレット＆JSP入門」P279のコード10-5を参考

package servlet;

import java.io.IOException;

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
    
    
	
  }
}