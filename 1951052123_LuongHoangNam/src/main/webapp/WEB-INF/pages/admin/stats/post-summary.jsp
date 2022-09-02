<%-- 
    Document   : PostSummary
    Created on : May 6, 2022, 8:41:18 PM
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
            <c:forEach items="${auction}" var="p">
              <tr>
                  <td>${p[0]}</td>
                  <td>${p[1]}</td>
              </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-8">
        <canvas id="mychart2" height="100rem" width="auto"></canvas>
    </div>
</div>
<script>
let labels=[];
let data=[];

<c:forEach items="${post}" var="p">
    labels.push(`${p[0]}`);
    data.push(${p[1]});
</c:forEach>
    
let labels1=[];
let data1=[];
let colors1=[]
let borderColor1=[];

<c:forEach items="${auction}" var="p">
    labels1.push(`${p[0]}`);
    data1.push(${p[1]});
</c:forEach>
    
window.onload = function (){
    drawChart(document.getElementById('mychart1').getContext("2d"),data,labels,"Post","bar");
    drawChart(document.getElementById('mychart2').getContext("2d"),data1,labels1,"Auction Post","bar")
};    


</script>
