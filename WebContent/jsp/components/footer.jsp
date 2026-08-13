<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

<footer>
	<div class="logo">
        <img src="<%= request.getContextPath() %>/images/site/logo-piccolo.png">
        <p>Il tuo mondo dei videogiochi</p>
	</div>
	
	<nav class="nav">
		<a href="<%= request.getContextPath() %>/HomeServlet">Home</a>
		<a href="<%= request.getContextPath() %>/CatalogoServlet">Catalogo</a>
		<a href="<%= request.getContextPath() %>/#">Chi siamo</a>
		<a href="<%= request.getContextPath() %>#/">FAQ</a>
	</nav>
	
	<div class="bottom">
		<p>&copy; 2026 gameWorld - All rights are reserved</p>
	</div>

</footer>