<%-- 
    Document   : home
    Created on : 9 mai 2022, 11:12:23
    Author     : stag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home JSP</title>
    </head>
    <body>
        <%@include file="./jspf/header.jspf" %>
        <c:forEach var="message" items="${requestScope.registerMessage}">
            <div class="${message.key}"><c:out value="${message.value}"/></div>
        </c:forEach>
        <main>
            <h1>Projet de Groupe 3</h1>
        </main>
            <%@include file="./jspf/footer.jspf" %>
    </body>
</html>
