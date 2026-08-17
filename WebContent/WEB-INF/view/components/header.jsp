<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
    
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/site/logo-icona.png">

<%
	UtenteBean utente = (UtenteBean) session.getAttribute("utente");
%>

<header>

    <!-- Parte superiore -->
    <div class="header-top">

        <!-- Logo -->
        <div class="logo">
    		<a href="<%= request.getContextPath() %>/Home">
        		<img src="<%= request.getContextPath() %>/images/site/logo.png">
    		</a>
		</div>

        <!-- Barra di ricerca -->
        <form class="search-bar" action="<%= request.getContextPath() %>/Catalogo" method="get">
            <input type="text" name="ricerca" placeholder="Cerca un videogioco...">
            <button type="submit">Cerca</button>
        </form>

        <!-- Account e carrello -->
        <div class="accedi-registrati">
        	<%
        		if(utente == null) {
        	%>
            		<a href="<%= request.getContextPath() %>/Login">Accedi / Registrati</a>
            <%
        		}
        		else {
            %>
            		<a href="<%= request.getContextPath() %>/Profilo"><%= utente.getNome() %></a>
           	<%
        		}
           	%>

            <a href="<%= request.getContextPath() %>/Carrello">Carrello</a>
        </div>
    </div>

    <!-- Navbar -->
    <nav class="navbar">
        <a href="<%= request.getContextPath() %>/Home">Home</a>
        <a href="<%= request.getContextPath() %>/Catalogo?idPiattaforme=7">Steam</a>
        <a href="<%= request.getContextPath() %>/Catalogo?idPiattaforme=8&idPiattaforme=9">PlayStation</a>
        <a href="<%= request.getContextPath() %>/Catalogo?idPiattaforme=13&idPiattaforme=12">Xbox</a>
        <a href="<%= request.getContextPath() %>/Catalogo?idPiattaforme=10&idPiattaforme=11">Nintendo</a>
        
        <span class="separator"></span>

        <a href="<%= request.getContextPath() %>/ChiSiamo">Chi siamo</a>

        <a href="<%= request.getContextPath() %>/FAQ">FAQ</a>
    </nav>

</header>