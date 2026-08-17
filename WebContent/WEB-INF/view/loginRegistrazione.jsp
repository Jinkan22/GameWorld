<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Login</title>
</head>
<body>

<main class="login-registrazione">
	<div class="selezioni">
		<button class="login-selezione" type="button" id="login-selezione">Accedi</button>
		<button class="registrazione-selezione" type="button" id="registrazione-selezione">Registrati</button>
	</div>
	
	<div class="login" id="login">
		
		<h2>LOGIN</h2>

		<%
			String erroreLogin = (String) request.getAttribute("erroreLogin");
	
			if(erroreLogin != null){
		%>
				<p><%= erroreLogin %><br><br>
		<% 
			}
		%>
	
		<form id="form-login" action="<%= request.getContextPath()%>/Login" method=post>
		
			<div class="campi-login">
				<div class="campo">
					<input type="email" name="email" placeholder="Email" required>	
				</div>
				<div class="campo">
					<input type="password" name="password" placeholder="Password" required>
				</div>
			</div>
	
			<div class="login-submit">
				<input type="submit" value="Accedi">
			</div>
			
		</form>
	</div>
	
	<div class="registrazione" id="registrazione">
	
		<h2>REGISTRAZIONE</h2>
	
		<%
			String erroreRegistrazione = (String) request.getAttribute("erroreRegistrazione");

			if(erroreRegistrazione != null){
		%>
				<p><%= erroreRegistrazione %><br><br>
		<% 
			}
		%>
		
		<form id="form-registrazione" action="<%= request.getContextPath()%>/Registrazione" method=post>
			
			<div class="campi-registrazione">
				<div class="campo">
					<input type="text" name="nome" placeholder="Nome" required>
				</div>
				<div class="campo">
					<input type="text" name="cognome" placeholder="Cognome" required>
				</div>
				<div class="campo">
					<input type="email" name="email" placeholder="Email" required>
				</div>
				<div class="campo">
					<input type="password" name="password" placeholder="Password" required>
				</div>
				<div class="campo">
					<input type="date" name="dataNascita" placeholder="Data di nascita" required>
				</div>
				<div class="campo">
				<input type="tel" name="numeroTelefono" placeholder="Telefono" required>
					</div>
			</div>
	
			<div class="registrazione-submit">
				<input type="submit" value="Registrati">
			</div>
	
		</form>
	</div>
</main>


<script>
const tabLogin = document.getElementById("login-selezione");
const tabRegistrazione = document.getElementById("registrazione-selezione");

const formLogin = document.getElementById("login");
const formRegistrazione = document.getElementById("registrazione");

tabLogin.addEventListener("click", function() {
    formLogin.style.display = "block";
    formRegistrazione.style.display = "none";
});

tabRegistrazione.addEventListener("click", function() {
    formLogin.style.display = "none";
    formRegistrazione.style.display = "block";
});
</script>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>