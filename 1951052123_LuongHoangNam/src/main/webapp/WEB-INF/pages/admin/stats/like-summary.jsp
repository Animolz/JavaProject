<%-- 
    Document   : like-summary
    Created on : May 7, 2022, 1:15:12 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row mt-5 ml-2">
    <div class="col-lg-4">
        <table class="table table-bordered" id="table-users">
            <thead class="thead-dark">
              <tr class="text-center">
                <th scope="col">User Id</th>
                <th scope="col">Post amount</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${post}" var="p">
              <tr>
                  <td>${p[0]}</td>
                  <td>${p[1]}</td>
              </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-8">
        <canvas id="mychart1" height="100rem" width="auto"></canvas>
    </div>
</div>
<script>
    let
</script>