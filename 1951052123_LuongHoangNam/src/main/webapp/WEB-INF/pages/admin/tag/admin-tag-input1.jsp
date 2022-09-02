<%-- 
    Document   : admin-tag-input1
    Created on : May 3, 2022, 7:48:49 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
<c:url var="action" value="/admin/admin-tag/input/${tag.id}/update"/>

<div class="container">
    <form:form method="POST" action="${action}" modelAttribute="tag" >
        <span class="login-signup-display my-2">Update Tag</span>
        <div class="mt-4 mb-2">
            <span class="txt1">Tag</span>
        </div>
        <div class="wrap-input100 validate-input">
            <form:input cssClass="login-signup-form" path="tag" value="${tag.tag}"/>
        </div>
        <div id="submit-container" class="mt-5">
            <button type="submit" class="w-100" id="submitBtn">
                <input type="button" name="add-tag-submit" value="Add" class="button"/>
            </button>
        </div>
    </form:form>
</div>