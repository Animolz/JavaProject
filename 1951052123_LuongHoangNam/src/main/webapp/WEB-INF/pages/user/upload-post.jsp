<%-- 
    Document   : uploadPost
    Created on : May 5, 2022, 8:25:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url value="/home/upstory/upload" var="action"/>
<c:url value="/update-post/${post.id}/update" var="action1"/>
<div class="container wrap-login-signup p-0 justify-content-between w-100">
    <c:choose>
    <c:when test="${(post.content == null)}">
    <form:form method="POST" action="${action}" modelAttribute="post"
                enctype="multipart/form-data" class="d-flex justify-content-between">
        <c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
        <div>
            <span class="login-signup-display">Update story</span>
            <div class="mt-3 mb-1">
                <span class="txt1">Image</span>
            </div>
            <div class="wrap-input100 validate-input" data-validate="Image is required">
                <form:input cssClass="login-signup-form" type="file" path="file" required="Image is required" id="img-output3"/>
            </div>
            <div class="wrap-input100 validate-input mt-2 border" style="height: auto; width: 20rem">
                <img id="output3" width="50 rem" height="auto"/>
            </div>
        </div>
        <div style="margin-top: 3.3rem; width: 25rem">
            <div class="mt-4 mb-1">
                <span class="txt1">Content</span>
            </div>
            <div class="wrap-input100 validate-input mt-2">
                <form:textarea cssClass="login-signup-form h-100 w-100" path="content" placeholder="Tell us something about yourself" rows="3"></form:textarea>
                <form:errors cssClass="alert alert-danger" path="content"></form:errors>
            </div>
            <div id="submit-container" class="mt-5">
                <button type="submit" class="w-100" id="submitBtn">
                    <input type="button" name="register-submit" value="Upload story" class="button"/>
                </button>
            </div>
        </div>
    </form:form>
    </c:when>
    <c:otherwise>
    <form:form method="POST" action="${action1}" modelAttribute="post"
                enctype="multipart/form-data" class="d-flex justify-content-between">
        <c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
        <div>
            <span class="login-signup-display">Update story</span>
            <div class="mt-3 mb-1">
                <span class="txt1">Image</span>
            </div>
            <div class="wrap-input100 validate-input" data-validate="Image is required">
                <form:input cssClass="login-signup-form" type="file" path="file" required="Image is required" id="img-output3"/>
            </div>
            <div class="wrap-input100 validate-input mt-2 border" style="height: auto; width: 20rem">
                <img id="output3" width="50 rem" height="auto"/>
            </div>
        </div>
        <div style="margin-top: 3.3rem; width: 25rem">
            <div class="mt-4 mb-1">
                <span class="txt1">Content</span>
            </div>
            <div class="wrap-input100 validate-input mt-2">
                <form:textarea cssClass="login-signup-form h-100 w-100" path="content" placeholder="Tell us something about yourself" rows="3" value="${post.content}"></form:textarea>
                <form:errors cssClass="alert alert-danger" path="content"></form:errors>
            </div>
            <div id="submit-container" class="mt-5">
                <button type="submit" class="w-100" id="submitBtn">
                    <input type="button" name="register-submit" value="Update story" class="button"/>
                </button>
            </div>
        </div>
    </form:form>
    </c:otherwise>
    </c:choose>
</div>
