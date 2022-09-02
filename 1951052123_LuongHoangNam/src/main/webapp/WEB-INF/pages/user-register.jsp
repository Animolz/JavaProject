<%-- 
    Document   : user-login
    Created on : Apr 2, 2022, 7:20:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carity</title>
        <link rel="stylesheet" href="<c:url value="/css/style.css" />"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="<c:url value="/js/main.js"/>" ></script>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:ital,wght@0,400;0,500;1,400;1,500&display=swap" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <c:url value="/user-register/add" var="action" />
        <section id="user-login" style="background-image: url('<c:url value="/pics/signup_background.png" />')">
            <div class="container wrap-login-signup pl-5 pr-5">
                <form:form method="POST" class="overflow-auto" style="height: 800px" action="${action}" modelAttribute="user"
                            enctype="multipart/form-data">
                    <c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
                    <form:errors path="*" cssClass="alert alert-danger"></form:errors>
                    <span class="login-signup-display">Sign up</span>
                    <div class="mt-4 mb-2">
                        <span class="txt1">Email</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Email is required">
                        <form:input cssClass="login-signup-form" type="email" path="email" required="required" />
                        <form:errors cssClass="alert alert-danger" path="email" ></form:errors>
                    </div>
                    <div class="mt-4 mb-2">
                        <span class="txt1">Username</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Username is required">
                        <form:input cssClass="login-signup-form"  type="text" path="username" required="required" />
                        <form:errors cssClass="alert alert-danger" path="username" ></form:errors>
                    </div>
                    <div class="mt-4 mb-2">
                        <span class="txt1">Password</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                        <form:input cssClass="login-signup-form" type="password" path="password" required="required" />
                        <form:errors cssClass="alert alert-danger" path="password" ></form:errors>
                    </div>
                    <div class="mt-4 mb-2">
                        <span class="txt1">Confirm Password</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Confirm password is required">
                        <form:input cssClass="login-signup-form" type="password" path="confirmPassword" required="required" />
                        <form:errors cssClass="alert alert-danger" path="confirmPassword" ></form:errors>
                    </div>
                    <div class="mt-3 mb-1">
                        <span class="txt1">Full name</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Full name is required">      
                        <form:input cssClass="login-signup-form" type="text" path="fullname" required="required" />
                        <form:errors cssClass="alert alert-danger" path="fullname" ></form:errors>
                    </div>
                    <div class="mt-3 mb-1">
                        <span class="txt1">Phone</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Phone is required">
                        <form:input cssClass="login-signup-form" type="text" path="phone" required="required" />
                        <form:errors cssClass="alert alert-danger" path="phone" ></form:errors>
                    </div>
                    <div class="mt-3 mb-1">
                        <span class="txt1">Avatar</span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Avatar is required">
                        <form:input cssClass="login-signup-form" type="file" path="file" required="required" id="img-output2"/>
                    </div>
                    <div class="wrap-input100 validate-input mt-2 crop">
                        <img id="output2" width="100%" height="auto" class="rounded-circle"/>
                    </div>
                    <div class="mt-4 mb-1">
                        <span class="txt1">About(Optional)</span>
                    </div>
                    <div class="wrap-input100 validate-input mt-2">
                        <form:textarea class="login-signup-form h-100" path="about" placeholder="Tell us something about yourself" rows="3"></form:textarea>
                        <form:errors cssClass="alert alert-danger" path="about" ></form:errors>
                    </div>
                    <div id="submit-container" class="mt-5">
                        <button type="submit" class="w-100" id="submitBtn">
                            <input type="button" name="register-submit" value="Register" class="button"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </section>
    </body>
</html>
