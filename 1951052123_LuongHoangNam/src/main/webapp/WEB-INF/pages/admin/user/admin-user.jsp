<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${error != null}"><div class="txt1 h5 alert alert-danger text-center my-2 w-100 p-2">${error}</div></c:if>
<h1 class="txt1 h1 text-center my-5">User Management</h1>

<section class="container">
    <div class="row justify-content-center my-3">
        <form class="d-flex col-lg-8 mr-4">
            <input type="text" placeholder="Search user..." name="kw" class="form-control">
            <button class="ml-2 btn btn-primary" type="submit"><input class="txt1 h6 bg-transparent border-0 m-0 text-light" type="button" value="Search"></button>
        </form>
        <a href="<c:url value="/admin/admin-user/input"/> " class="btn btn-success col-lg-3 d-flex justify-content-center p-0 align-items-center">
            <button class="bg-transparent d-flex justify-content-center p-0 align-items-center border-0" type="button">
                <i class="fa-solid fa-plus text-light"></i>
                <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Add user">
            </button>
        </a>
    </div>
</section>
<table class="table table-bordered display" id="table-users">
    <thead class="thead-dark">
      <tr class="text-center">
        <th scope="col">Id</th>
        <th scope="col">Full Name</th>
        <th scope="col">Username</th>
        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Avatar</th>
        <th scope="col">Is Reported</th>
        <th scope="col">Is Banned</th>
        <th scope="col">Active</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
      <tr>
        <th scope="row">${u.id}</th>
        <td>${u.fullname}</td>
        <td>${u.username}</td>
        <td>${u.email}</td>
        <td>${u.phone}</td>
        <td class="p-1 text-center"><img src="${u.avatar}" alt="alt" class="rounded-circle"/></td>
        <td class="text-center">
            <c:choose>
                <c:when test="${u.isReported == true}"><i class="fa-solid fa-circle-check text-success"></i></c:when>
                <c:otherwise><i class="fa-solid fa-circle-xmark text-danger"></i></c:otherwise>
            </c:choose>
        </td>
        <td class="text-center">
            <c:choose>
                <c:when test="${u.isBanned == true}"><i class="fa-solid fa-circle-check text-success"></i></c:when>
                <c:otherwise><i class="fa-solid fa-circle-xmark text-danger"></i></c:otherwise>
            </c:choose>
        </td>
        <td class="text-center">
            <c:choose>
                <c:when test="${u.active == true}"><i class="fa-solid fa-circle-check text-success"></i></c:when>
                <c:otherwise><i class="fa-solid fa-circle-xmark text-danger"></i></c:otherwise>
            </c:choose>
        </td>
        <td>${u.userRole}</td>
        <td class="p-1" style="height: 0px">
            <a href="<c:url value="/admin/admin-user/input/${u.id}" />" id="updateUser">
                <button class="btn btn-info d-flex justify-content-center p-0 align-items-center w-100 h-100">
                    <i class="fa-solid fa-pencil"></i>
                    <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Update">
                </button>
            </a>
        </td>
        <td class="p-1" style="height: 0px">
            <a href="<c:url value="/admin/admin-user/ban/${u.id}" />" id="banUser">
                <button class="btn btn-warning d-flex justify-content-center p-0 align-items-center w-100 h-100">
                    <i class="fa-solid fa-eye-slash"></i>
                    <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Ban">
                </button>
            </a>
        </td>
        <td class="p-1" style="height: 0px">
            <a href="<c:url value="/admin/admin-user/${u.id}" />" id="deleteUser">
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