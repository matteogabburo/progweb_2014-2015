<%@ page import="introwebprog.utility.MailSender" %><%--
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


<form role="form" action="http://localhost:8080/CinemaMultisala_war_exploded/rec" method="get">
    <div class="form-group">
        <label>Inserici la tua mail e premi il pulsante, una mail contentente la tua password verrà spedita alla tua mail</label>
        <input type="text" class="form-control" id="logmail" name = "mail">
    </div>

    <button type="reset" class="btn btn-default">Cancel</button>
    <button type="submit" class="btn btn-default">Recupera</button>

</form>

</body>
</html>
