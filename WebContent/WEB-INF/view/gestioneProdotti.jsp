<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/components/header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProdottoViewBean" %>
<%@ page import="model.PiattaformaBean" %>
<%@ page import="model.GenereBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

ArrayList<ProdottoViewBean> prodotti = (ArrayList<ProdottoViewBean>) request.getAttribute("prodotti");

String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gameWorld - Gestione prodotti</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/interfaccia.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</head>
<body>

<main class="pagina-gestione-prodotti">

    <h2>GESTIONE PRODOTTI</h2>

    <%
    if(errore != null) {
    %>
        <p><%= errore %></p>
    <%
    }
    %>

    <div class="bottoni-gestione-prodotti">
        <button type="button" id="aggiungi-prodotto-button" onclick="mostraFormAggiungiProdotto()">
            Aggiungi un prodotto
        </button>

        <button type="button" id="mostra-prodotti-button" onclick="mostraListaProdotti()">
            Mostra prodotti
        </button>
    </div>

    <section id="lista-prodotti">
        <%
        if(prodotti != null && !prodotti.isEmpty()) {

            for(ProdottoViewBean prodotto : prodotti) {
        %>
	            <div class="card-gestione-prodotto">
	                <h3><%= prodotto.getProdotto().getNome() %></h3>
	
	                <div class="dati-prodotto">
	                    <p>
	                        <strong>Prezzo</strong>
	                        <span><%= prodotto.getProdotto().getPrezzo() %> €</span>
	                    </p>
	
	                    <p>
	                        <strong>Sviluppatore</strong>
	                        <span><%= prodotto.getProdotto().getSviluppatore() %></span>
	                    </p>
	
	                    <p>
	                        <strong>Data di uscita</strong>
	                        <span><%= sdf.format(prodotto.getProdotto().getDataUscita()) %></span>
	                    </p>
	
	                </div>
	
	
	                <div class="categorie-prodotto">
	                    <div>
	                        <strong>Piattaforme</strong>
	
	                        <div class="tag-prodotto">
	
	                            <%
								for(int i = 0; i < prodotto.getPiattaforme().size(); i++) {
								%>
								    <span><%= prodotto.getPiattaforme().get(i).getNomePiattaforma() %><%= i < prodotto.getPiattaforme().size() - 1 ? ", " : "" %></span>
								<%
								}
								%>
	
	                        </div>
	                    </div>
	
	                    <div>
	                        <strong>Generi</strong>
	
	                        <div class="tag-prodotto">
	
	                            <%
								for(int i = 0; i < prodotto.getGeneri().size(); i++) {
								%>
								    <span><%= prodotto.getGeneri().get(i).getNomeGenere() %><%= i < prodotto.getGeneri().size() - 1 ? ", " : "" %></span>
								<%
								}
								%>
	
	                        </div>
	                    </div>
	                </div>
	
	                <form action="<%= request.getContextPath() %>/ModificaProdotto" method="get">
	                    <input type="hidden" name="idProdotto" value="<%= prodotto.getProdotto().getIdProdotto() %>">
	
	                    <input type="submit" value="Gestisci prodotto">
	                </form>
	            </div>
        <%
            }
        }
        else {
        %>
            <p>Non sono presenti prodotti.</p>
        <%
        }
        %>
    </section>

    <section id="aggiungi-prodotto">

        <div class="card-aggiungi-prodotto">

            <h3>AGGIUNGI PRODOTTO</h3>

            <form id="form-aggiungi-prodotto" action="<%= request.getContextPath() %>/AggiungiProdotto" method="post" novalidate>

                <div class="campo-prodotto">
                    <label for="nome">Nome prodotto</label>
                    <input type="text" id="nome" name="nome" required>
                </div>

                <div class="campo-prodotto">
                    <label for="descrizione">Descrizione</label>
                    <textarea id="descrizione" name="descrizione" rows="5" required></textarea>
                </div>

                <div class="campo-prodotto">
                    <label for="prezzo">Prezzo</label>
                    <input type="number" id="prezzo" name="prezzo" min="0" step="0.01" required>
                </div>

                <div class="campo-prodotto">
                    <label for="immagine">Immagine</label>
                    <input type="text" id="immagine" name="immagine" required>
                </div>

                <div class="campo-prodotto">
                    <label for="dataUscita">Data di uscita</label>
                    <input type="date" id="dataUscita" name="dataUscita" required>
                </div>

                <div class="campo-prodotto">
                    <label for="sviluppatore">Sviluppatore</label>
                    <input type="text" id="sviluppatore" name="sviluppatore" required>
                </div>

                <button type="submit">Aggiungi prodotto</button>
            </form>
        </div>
    </section>
</main>

<%@ include file="/WEB-INF/view/components/footer.jsp" %>
</body>
</html>