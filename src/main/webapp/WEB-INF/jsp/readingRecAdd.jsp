<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page = "head.jsp" />
</head>
<body>
  <header>
    <jsp:include page = "header.jsp" /> 
  </header>
  
  <main>
  	  <jsp:include page = "myPageMenu.jsp" />
    
        <h2>読書記録を追加</h2>
        <c:if test="${not empty errorMsg}">
          <p><font color="red"><c:out value="${errorMsg}" /></font></p>
        </c:if>
      	<form action="AddReadingRec" method="post">
	        <input type="text" name="title" class="input-field" placeholder="タイトル" required>
	        <input type="text" name="author" class="input-field" placeholder="作者" required>
	        <select name="readStatus" class="input-field" placeholder="読書状況">
				<option value="未登録">未登録</option>
				<option value="読みたい">読みたい</option>
				<option value="積読">積読</option>
				<option value="いま読んでいる">いま読んでいる</option>
				<option value="読み終わった">読み終わった</option>
				<option value="感想を書いた">感想を書いた</option>
			</select><br>
			
			<input type="number" name="count"  class="input-field" min="0" max="50" placeholder="読み終えた回数。0～50以内で入力">
			<input type="number" name="point"  class="input-field" min="0" max="100" placeholder="100点満点で評価。0～100以内で入力">
			<textarea name="impression" class="input-field" cols="20" maxlength="3000" rows="5" placeholder="感想を入力してください"></textarea>
			
	        <button type="submit" class="cert-btn">保存</button>
	     </form>
  </main>
</body>
</html>