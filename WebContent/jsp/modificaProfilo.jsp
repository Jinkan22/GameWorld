<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameWorld - Modifica Profilo </title>
</head>
<body>
	<form action="<%= request.getContextPath()%>/ModificaProfiloServlet" method=post>
	
	<h1> Modifica Profilo </h1>
	
	<%
		String errore = (String) request.getAttribute("errore");

		if(errore != null){
	%>

		<p><%= errore %><br><br>

	<% 
		}
	%>
	
	<label>Nome</label>
	<input type="text" name="nome"><br><br>
	
	<label>Cognome</label>
	<input type="text" name="cognome"><br><br>
	
	<label>Indirizzo</label><br>
	<input type="text" name="indirizzo"><br><br>
	
	<label>Metodo di pagamento</label><br>
	<input type="text" name="metodoPagamento"><br><br>
	
	<input type="submit" name="Modifica Profilo">
	
	</form>

</body>
</html>