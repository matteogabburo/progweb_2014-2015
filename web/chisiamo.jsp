<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 18/12/15
  Time: 11.38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cinema Multisala</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="CSS/mycss.css" rel="stylesheet" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="includes/header.jsp" />
<div class="col-md-1 sidebar"></div>
<div class="col-md-10">
    <div class = "row">
        <!-- **************************************** -->
        <div class="col-md-12">
            <h2 class ="center-text film-title">Chi siamo</h2>
            <p>
                <b>Cinema Multisala</b> è un progetto sviluppato da Gabburo Matteo per il corso di Programmazione Web dell'Università di Trento.
                </br><b>Non</b> è da intendersi come prodotto commerciale, ma utile <b>solo</b> a fini didattici.
            </p>
            <h2 class ="center-text film-title">Descrizione</h2>
            <p>
                Il sistema prevede la prenotazione di film a scelta, prevedendo due tipi di biglietto, ovvero normale e ridotto.
                </br>La prenotazione del film è agevolata un <b>percorso guidato</b>, ovvero:
            </p>
            <ul><li>Scelta film</li><li>Scelta Spettacolo</li><li>Scelta posti e tipo di biglietto</li><li>Ricezione mail di conferma con in allegato pdf bigletto</li></ul>
            <p>Per quanto riguarda l'amministrazione del sito, è presente un interfaccia per l'amministratore che gli permette di avere un rapido resoconto degli spettacoli attivi, e la possibilità di eliminare prenotazioni a sua discrezione</p>
            <h2 class ="center-text film-title">Tecnologie utilizzate</h2>
            <p><h3>Linguaggi</h3><ul><li>Java</li><li>HTML</li><li>JS</li><li>CSS</li><li>SQL</li></ul></p>
            <p><h3>Librerie, Server e tool esterni</h3><ul><li>Bootstrap : per lo stylesheet</li><li>iTextPDF : libreria per la generazione di pdf</li><li>zxing e QRGen : per la generazione di qr code</li><li>Glassfish : server locale sul quale è stato fatto il deploy</li><li>Apache Derby : Database utilizzato</li></ul></p>
        </div>
        <!-- ************************************************** -->
    </div>

    <footer>

    </footer>

</div>
<div class="col-md-1 sidebar"></div>
</body>
</html>
