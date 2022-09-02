<%-- 
    Document   : admin-post
    Created on : May 3, 2022, 10:35:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
<h1 class="txt1 h1 text-center my-5">Post Management</h1>

<section class="container">
    <div class="row justify-content-center my-3">
        <form class="d-flex col-lg-8 mr-4">
            <input type="text" placeholder="Search post..." name="kw" class="form-control" data-toggle="tooltip" title="Search the word 'auction!@#' to group Auction Post">
            <button class="ml-2 btn btn-primary" type="submit"><input class="txt1 h6 bg-transparent border-0 m-0 text-light" type="button" value="Search"></button>
        </form>
    </div>
</section>
<table class="table table-bordered" id="table-users">
    <thead class="thead-dark">
      <tr class="text-center">
        <th scope="col">Id</th>
        <th scope="col">Content</th>
        <th scope="col">Image</th>
        <th scope="col">Post Date</th>
        <th scope="col">Is Auction</th>
        <th scope="col">Price</th>
        <th scope="col">Post Active</th>
        <th scope="col">User Id</th>
        <th scope="col">Username</th>
        <th scope="col"></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${posts}" var="p">
      <tr>
        <th scope="row">${p[0]}</th>
        <td>${p[1]}</td>
        <td><img src="${p[2]}" class='rounded'/></td>
        <td>${p[3]}</td>
        <td class="text-center">
            <c:choose>
                <c:when test="${p[4] == true}"><i class="fa-solid fa-circle-check text-success"></i></c:when>
                <c:otherwise><i class="fa-solid fa-circle-xmark text-danger"></i></c:otherwise>
            </c:choose>
        </td>
        <td>${p[5]}</td>
        <td class="text-center">
            <c:choose>
                <c:when test="${p[6] == true}"><i class="fa-solid fa-circle-check text-success"></i></c:when>
                <c:otherwise><i class="fa-solid fa-circle-xmark text-danger"></i></c:otherwise>
            </c:choose>
        </td>
        <td>${p[7]}</td>
        <td>${p[8]}</td>
        <td class="p-1" style="height: 0px">
            <a href="<c:url value="/admin/admin-post/disable/${p[0]}" />" id="disablePost" style="text-decoration: none">
                <button class="btn btn-danger d-flex justify-content-center p-0 align-items-center w-100 h-100">
                    <i class="fa-solid fa-eye-slash"></i>
                    <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Disable">
                </button>
            </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
</table>