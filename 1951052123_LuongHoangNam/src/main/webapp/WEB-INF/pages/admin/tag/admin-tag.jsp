<%-- 
    Document   : admin-tag
    Created on : May 3, 2022, 5:36:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
<h1 class="txt1 h1 text-center my-5">Tag Management</h1>

<section class="container">
    <div class="row justify-content-center my-3">
        <form class="d-flex col-lg-8 mr-4">
            <input type="text" placeholder="Search tag..." name="kw" class="form-control">
            <button class="ml-2 btn btn-primary" type="submit"><input class="txt1 h6 bg-transparent border-0 m-0 text-light" type="button" value="Search"></button>
        </form>
        <a href="<c:url value="/admin/admin-tag/input"/> " class="btn btn-success col-lg-3 d-flex justify-content-center p-0 align-items-center">
            <button class="bg-transparent d-flex justify-content-center p-0 align-items-center border-0" type="button" data-toggle="modal" data-target>
                <i class="fa-solid fa-plus text-light"></i>
                <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Add Tag">
            </button>
        </a>
    </div>
    <table class="table table-bordered display" id="table-users">
        <thead class="thead-dark">
          <tr class="text-center">
            <th scope="col">Id</th>
            <th scope="col">Tag</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${tags}" var="t">
          <tr>
            <th scope="row">${t.id}</th>
            <td>${t.tag}</td>
            <td class="p-1" style="height: 0px">
                <a href="<c:url value="/admin/admin-tag/input/${t.id}" />" id="updateUser">
                    <button class="btn btn-info d-flex justify-content-center p-0 align-items-center w-100 h-100">
                        <i class="fa-solid fa-pencil"></i>
                        <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Update">
                    </button>
                </a>
            </td>
            <td class="p-1" style="height: 0px">
                <a href="<c:url value="/admin/admin-tag/${t.id}" />" id="deleteUser">
                    <button class="btn btn-danger d-flex justify-content-center p-0 align-items-center w-100 h-100">
                        <i class="fa-solid fa-trash"></i>
                        <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Delete">
                    </button>
                </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
    </table>

</section>
