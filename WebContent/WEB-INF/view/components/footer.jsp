<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">

<footer>
	<!-- Logo -->
	<div class="logo">
        <img src="<%= request.getContextPath() %>/images/site/logo.png">
        <p>Il tuo mondo dei videogiochi</p>
	</div>
	
	<!-- Link -->
	<nav class="nav">
		<a href="<%= request.getContextPath() %>/Home">Home</a>
		<a href="<%= request.getContextPath() %>/Catalogo">Catalogo</a>
		<a href="<%= request.getContextPath() %>/ChiSiamo">Chi siamo</a>
		<a href="<%= request.getContextPath() %>/FAQ">FAQ</a>
	</nav>
	
	<!-- Copyright -->
	<div class="bottom">
		<p>&copy; 2026 gameWorld - All rights are reserved</p>
	</div>
</footer>