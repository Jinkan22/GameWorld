<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Registrazione</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/index.jsp">Homepage</a><br>

	<form action="<%= request.getContextPath()%>/RegistrazioneServlet" method=post>
	
	<h1>Registrazione</h1>
	
	<%
		String errore = (String) request.getAttribute("errore");

		if(errore != null){
	%>

		<p><%= errore %><br><br>

	<% 
		}
	%>
	
	<label>Nome</label><br>
	<input type="text" name="nome" required><br><br>
	
	<label>Cognome</label><br>
	<input type="text" name="cognome" required><br><br>
	
	<label>Email</label><br>
	<input type="email" name="email" required><br><br>
	
	<label>Password</label><br>
	<input type="password" name="password" required><br><br>
	
	<label>Data di nascita</label><br>
	<input type="date" name="dataNascita" required><br><br>
	
	<label>Numero di telefono</label><br>
	<input type="tel" name="numeroTelefono" required><br><br>
	
	<input type="submit" value="Registrati">
	
	</form>


</body>
</html>