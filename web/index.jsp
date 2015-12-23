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
<header>
    <jsp:include page="includes/header.jsp" />
</header>

<div class = "row">
    <div class=\"col-md-2\"></div>
    <!-- Locandine **************************************** -->
    <div class=\"col-md-8\">
        <jsp:include page="/filmPreviewTable" />
    </div>
    <!-- ************************************************** -->
    <div class=\"col-md-2\"></div>
</div>

<footer>

</footer>


</body>
</html>
