<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
    
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/site/logo-icon.png">

<%
	UtenteBean utente = (UtenteBean) session.getAttribute("utente");
%>

<header>

    <!-- Parte superiore -->
    <div class="header-top">

        <!-- Logo -->
        <div class="logo">
    		<a href="<%= request.getContextPath() %>/HomeServlet">
        		<img src="<%= request.getContextPath() %>/images/site/logo-piccolo.png">
    		</a>
		</div>

        <!-- Barra di ricerca -->
        <form class="search-bar" action="<%= request.getContextPath() %>/CatalogoServlet" method="get">
            <input type="text" name="ricerca" placeholder="Cerca un videogioco...">
            <button type="submit">Cerca</button>
        </form>

        <!-- Account e carrello -->
        <div class="header-actions">
        	<%
        		if(utente == null) {
        	%>
            		<a href="<%= request.getContextPath() %>/LoginServlet">Accedi / Registrati</a>
            <%
        		}
        		else {
            %>
            		<a href="<%= request.getContextPath() %>/ProfiloServlet"><%= utente.getNome() %></a>
           	<%
        		}
           	%>

            <a href="<%= request.getContextPath() %>/CarrelloServlet">Carrello</a>
        </div>
    </div>

    <!-- Navbar -->
    <nav class="navbar">
        <a href="<%= request.getContextPath() %>/HomeServlet">Home</a>
        <a href="<%= request.getContextPath() %>/CatalogoServlet?idPiattaforme=7">Steam</a>
        <a href="<%= request.getContextPath() %>/CatalogoServlet?idPiattaforme=8&idPiattaforme=9">PlayStation</a>
        <a href="<%= request.getContextPath() %>/CatalogoServlet?idPiattaforme=13&idPiattaforme=12">Xbox</a>
        <a href="<%= request.getContextPath() %>/CatalogoServlet?idPiattaforme=10&idPiattaforme=11">Nintendo</a>
        
        <span class="separator"></span>

        <a href="#">Chi siamo</a>

        <a href="#">FAQ</a>
    </nav>

</header>