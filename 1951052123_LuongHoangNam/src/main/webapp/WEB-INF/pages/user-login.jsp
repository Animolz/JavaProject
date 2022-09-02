<%-- 
    Document   : user-login
    Created on : Apr 2, 2022, 7:20:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carity</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="<c:url value="/css/style.css" />"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:ital,wght@0,400;0,500;1,400;1,500&display=swap" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <c:url value="/" var="action" />
        <section id="user-login" style="background-image: url('<c:url value="/pics/login_background.png" />')">
            <div class="container wrap-login-signup">
                <form action="${action}" method="POST" >
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <spring:message code="user.login.error1" />
                        </div>
                    </c:if>
                    <c:if test="${param.accessDenied != null}">
                        <div class="alert alert-danger">
                            <spring:message code="user.login.error2" />
                        </div>
                    </c:if>
                    <span class="login-signup-display">Login</span>
                    <div class="mt-5 mb-2">
                        <span class="txt1">Username</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Username is required">
                        <input class="login-signup-form" type="text" name="username" required="Username is required"/>
                    </div>
                    <div class="mt-4 mb-2">
                        <span class="txt1">Password</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                        <input class="login-signup-form" type="password" name="password" required="Password is required"/>
                    </div>
                    <div id="submit-container" class="mt-5">
                        <button  class="w-100" id="submitBtn" type="submit"><input type="button" name="login-submit" value="Login" class="button"/></button>
                    </div>
                </form>
                <div class="txt1 w-100 login-signup-display mt-5 text-capitalize">
                    <p>Don't have an account? <a href="<c:url value="/user-register"/>" class="btn btn-success text-light">Sign up</a></p>
                </div>
            </div>
        </section>
    </body>
</html>
