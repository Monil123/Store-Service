<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
<%
String id = request.getParameter("id");
//String proName = request.getParameter("proName");
//int proPrice = Integer.parseInt(request.getParameter("proPrice"));
//int quantity = Integer.parseInt(request.getParameter("quantity"));
%>
<form action="rest/StoreService/update" method="post" >
ID:<input name="id" type="text" value=<%=id %>><br/><br/>
Product Name:<input name="proName" type="text"><br/><br/>
Product Price:<input name="proPrice" type="text"><br/><br/>
Product Quantity:<input name="quantity" type="text"><br/><br/>
<input type="submit" value="Submit">
</form>
</body>
</html>