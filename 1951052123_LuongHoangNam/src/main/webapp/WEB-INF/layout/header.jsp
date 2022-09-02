<%-- 
    Document   : header
    Created on : Mar 31, 2022, 9:35:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="header">
        <div class="container pb-2">
            <div class="row pt-1 justify-content-around">
                <div class="col-md-3 d-flex p-0">
                    <div class="logo m-2">
                        <a class="logo-link" href="<c:url value="/home" />">
                            <img src="<c:url value="/resources/media/pics/headerIcon.png" />" alt="alt" class="mt-1"/>
                        </a>
                    </div>
                    <div class="search-bar">
                        <form  action="" class="d-flex">
                            <input type="text" name="search" placeholder="Search...." class="search-content" />
                            <button type="submit" id="searchBtn">
                                <i class="fas fa-search" ></i>
                            </button>
                        </form>
                    </div>
                </div>
                <div class="nav col-auto mt-1">
                    <nav class="navbar navbar-expand-md">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                          <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="collapsibleNavbar">
                          <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value="/home" />">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value="/profile/${currentUser.username}" />">Profile</a>
                                </li>
                                 <li class="nav-item">
                                    <a class="nav-link" href="#">Notifications</a>
                                </li>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                                        Admin
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item btn" href="<c:url value="/admin/admin-user" />">User</a>
                                        <a class="dropdown-item btn" href="<c:url value="/admin/admin-tag" />">Tag</a>
                                        <a class="dropdown-item btn" href="<c:url value="/admin/admin-post" />">Post</a>
                                        <a class="dropdown-item btn dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                                            Stats
                                        </a>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item btn" href="<c:url value="/admin/post-summary" />">Post Summary By User</a>
                                        </div>
                                    </div>
                                </li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div class="col-auto mt-1 align-items-center text-center d-flex">
                    <div class="login-signup-form border-0" style="height: auto; width: 50px"><img src="${currentUser.avatar}" class="rounded-circle"/></div>
                    <a href="<c:url value="/logout"/>" class="ml-1">
                        <button class="btn btn-danger p-0 align-items-center rounded-circle p-2" type="button" data-toggle="tooltip" title="Logout">
                            <i class="fa-solid fa-right-from-bracket"></i>
                        </button>
                    </a>  
                </div>
            </div>
        </div>
    </nav>
</header>