//「スッキリわかるサーブレット＆JSP入門」コード13-3、コード13-5～13-9を参考

package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.AccountBean;
import beans.ProfileBean;



//アカウント情報をアカウントテーブルに新規登録するDAO
//サンプルデータがアカウントテーブルに登録されていなかったらエラーになる。
//configDB.javaを継承
public class ProfileEditDAO extends ConfigDB{

	
  public void update(ProfileBean profileInfo, AccountBean account) {
	  
	//親クラスConfigDBのメソッドを利用
	//JDBCドライバーを読み込む
		ReadJDBC_Driver();
    
    // データベースへ接続
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
      
    	
      // SELECT文を準備
      String sql = "UPDATE プロフィール SET 性別 = ?, 生年月日 = ?, 職業 = ?, 在住都道府県 = ?, "
      		+ "パーソナルタグ = ?, 興味のあるジャンル = ?, 好きな作家 = ?, お気に入り1位 = ?,"
      		+ " お気に入り2位 = ?, お気に入り3位 = ? WHERE プロフィールID = ? ";
        
      PreparedStatement pStmt = conn.prepareStatement(sql);
      
      //WHERE文の?に代入
      for(int i = 0; i < profileInfo.getStrProfileInfo().length + 1; i++) {
    	  //生年月日のとき
    	  if(i == 1) {
    		  pStmt.setDate(i+1, profileInfo.getBirthday());
    	 	  continue; 
    	  }
    	  
    	  pStmt.setString(i+1, profileInfo.getStrProfileInfo()[i]);
    	  
    	  //プロフィールIDのとき
    	  if(i == profileInfo.getStrProfileInfo().length + 1) {
    		  pStmt.setInt(i+1, account.getProfileID());
    	  }
      }


      pStmt.executeUpdate();
      conn.commit();
          
    }  
      //try文の中でエラーが出たとき実行する
    catch (SQLException e) {
      e.printStackTrace();   
    }
    
  }
}