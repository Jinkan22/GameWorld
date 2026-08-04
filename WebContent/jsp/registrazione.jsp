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
	
	<h1> Registrazione </h1>
	
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
	
	<label>Indirizzo</label><br>
	<input type="text" name="indirizzo" required><br><br>
	
	<label>Metodo di pagamento</label><br>
	<input type="text" name="metodoPagamento" required><br><br>
	
	<input type="submit" value="Registrati">
	
	</form>


</body>
</html>