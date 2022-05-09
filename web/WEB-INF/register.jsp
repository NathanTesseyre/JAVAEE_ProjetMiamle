<%-- 
    Document   : register.jsp
    Created on : 9 mai 2022, 11:30:01
    Author     : stag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register JSP</title>
    </head>
    <body>
        <%@include file="./jspf/header.jspf" %>
        <c:forEach var="message" items="${requestScope.registerMessage}">
            <div class="${message.key}"><c:out value="${message.value}"/></div>
        </c:forEach>
        <main>
            <fieldset>
                <legend>Inscription</legend>
                <div>
                    <label for="">Votre psuedo</label>
                    <input type="text" id="" name="" value="">
                    <span class="error"></span>
                </div>
                <div>
                    <label for="">Votre email</label>
                    <input type="text" id="" name="" value="">
                    <span class="error"></span>
                </div>
                <div>
                    <label for="">Votre téléphone</label>
                    <input type="text" id="" name="" value="">
                    <span class="error"></span>
                </div>
                <div>
                    <label for="">Votre mot de passe</label>
                    <input type="password" id="" name="">
                    <span class="error"></span>
                </div>
                <div>
                    <label for="confirm">Confirmation</label>
                    <input type="password" id="" name="confirm">
                    <span class="error"></span>
                </div>
                <div>
                    <input type="submit" value="S'inscrire">
                </div>
            </fieldset>
        </main>
        <%@include file="./jspf/footer.jspf" %>
    </body>
</html>
