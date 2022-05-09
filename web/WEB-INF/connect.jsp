<%-- 
    Document   : connect
    Created on : 9 mai 2022, 11:31:51
    Author     : stag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>connect JSP</title>
    </head>
    <body>
        <%@include file="./jspf/header.jspf" %>
        <c:forEach var="message" items="${requestScope.connectMessage}">
            <div class="${message.key}"><c:out value="${message.value}"/></div>
        </c:forEach>
        <main>
            <fieldset>
                <legend>Se connecter</legend>
                <form action="" method="post">
                    <div>
                        <label>Votre pseudo</label>
                        <input type="text" id="pseudo" name="pseudo">
                        <span class="error">${requestScope.errors.pseudo}</span>
                    </div>
                    <div>
                        <label>Votre mot de passe</label>
                        <input type="password" id="pwd" name="pwd">
                        <span class="error">${requestScope.errors.pwd}</span>
                    </div>  
                    <div>
                        <input type="submit" value="Se connecter">
                    </div>
                </form>
            </fieldset>
        </main>
            <%@include file="./jspf/footer.jspf" %>
    </body>
</html>
