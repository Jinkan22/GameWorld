<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Home</title>
</head>
<body>

<h1>GameWorld</h1>

<a href="<%= request.getContextPath() %>/LoginServlet">Login</a><br><br>

<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo prodotti</a>

</body>
</html>