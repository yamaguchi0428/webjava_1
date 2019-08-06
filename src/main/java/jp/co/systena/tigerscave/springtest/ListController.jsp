<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
  <h1>ショッピングカート一覧</h1>
  <br>
  <br>
  <p>商品A</p>
  <p>100円</p>
  <input type="submit"value="カートに入れる"/>


  <p>商品B</p>
  <p>200円</p>
  <input type="submit"value="カートに入れる"/>


   <p>商品C</p>
   <p>300円</p>
  <input type="submit"value="カートに入れる"/>


 <p>カート一覧</p>

<form method="POST" action="ListController.jsp">
<input type="submit"value="注文を確定する"/>
 <%
  String textValue = request.getParameter("text1");
  if(textValue !=null
  && !"".equals(textValue)){
    out.println("カートリスト"+textValue+"<br/>");
    }
    %>
    </form>
</body>
</html>