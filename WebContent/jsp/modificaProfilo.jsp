<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Modifica profilo</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/ProfiloServlet">Profilo</a><br>

	<form action="<%= request.getContextPath()%>/ModificaProfiloServlet" method=post>
	
	<h1>Modifica Profilo</h1>
	
	<%
		String errore = (String) request.getAttribute("errore");

		if(errore != null){
	%>

		<p><%= errore %><br><br>

	<% 
		}
	%>
	
	<label>Nome</label><br>
	<input type="text" name="nome"><br><br>
	
	<label>Cognome</label><br>
	<input type="text" name="cognome"><br><br>
	
	<label>Data di nascita</label><br>
	<input type="date" name="dataNascita"><br><br>
	
	<label>Numero di telefono</label><br>
	<input type="tel" name="numeroTelefono"><br><br>
	
	<input type="submit" value="Modifica Profilo">
	
	</form>

</body>
</html>