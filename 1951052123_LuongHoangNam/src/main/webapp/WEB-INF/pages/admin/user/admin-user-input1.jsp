<%-- 
    Document   : admin-user-input1
    Created on : May 3, 2022, 12:08:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>

<div class="container">
    <c:url var="action" value="/admin/admin-user/input/${user.id}/update"/>
    <form:form method="POST" action="${action}" modelAttribute="user" 
                   enctype="multipart/form-data">
        <span class="login-signup-display my-2">Update User</span>
        <div class="mt-4 mb-2">
            <span class="txt1">Username</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input cssClass="login-signup-form" path="username" value="${user.username}"/>
            <form:errors cssClass="login-signup-form text-danger" path="username" />
        </div>
        <div class="mt-4 mb-2">
            <span class="txt1">Password</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input type="password" cssClass="login-signup-form" path="password" value="${user.password}"/>
            <form:errors cssClass="login-signup-form text-danger" path="password" />
        </div>
        <div class="mt-4 mb-2">
            <span class="txt1">Email</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input type="email" cssClass="login-signup-form" path="email" value="${user.email}"/>
            <form:errors cssClass="login-signup-form text-danger" path="email" />
        </div>
        <div class="mt-4 mb-2">
            <span class="txt1">Full name</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input cssClass="login-signup-form" path="fullname" value="${user.fullname}"/>
            <form:errors cssClass="login-signup-form text-danger" path="fullname" />
        </div>
        <div class="mt-4 mb-2">
            <span class="txt1">Phone</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input cssClass="login-signup-form" path="phone" value="${user.phone}"/>
            <form:errors cssClass="login-signup-form text-danger" path="phone" />
        </div>
        <div class="mt-4 mb-2">
            <span class="txt1">Role</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:select path="userRole" cssClass="login-signup-form">
                    <form:option value="ROLE_ADMIN" cssClass="login-signup-form" label="Admin" />
                    <form:option value="ROLE_USER" cssClass="login-signup-form" label="User" />
            </form:select>
        </div>
        <div id="submit-container" class="mt-5">
            <button type="submit" class="w-100" id="submitBtn">
                <input type="button" name="update-user-submit" value="Update" class="button"/>
            </button>
        </div>
    </form:form>
</div>
