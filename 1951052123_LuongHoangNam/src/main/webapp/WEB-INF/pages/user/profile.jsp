<%-- 
    Document   : profile
    Created on : Apr 29, 2022, 8:36:41 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="cover-sec">
    <img src="<c:url value="/pics/cover-img.jpg"/>" alt="alt"/>
</section>

<div class="container mt-5">
    <div class="row justify-content-between">
        <div class="col-lg-3 col-md-4 p-0">
            <div class="profile-pic text-center">
                <div class="user-profile mb-2">
                    <div class="user-picture w-100 p-0 bg-transparent pic-resize">
                        <img src="${user.avatar}" alt="" class="user-pic"/>
                    </div>
                </div>
                <c:if test="${user.facebook}">
                <ul id="social-link" class="text-left p-0 border">
                    <li>
                        <a href="https://www.facebook.com/profile.php?id=100038061072521" class="d-flex">
                            <i class="fa-2x  fa-brands fa-facebook-square mr-1"></i>
                            <h6 class="txt1 no-wrap m-0">https://www.facebook.com/profile.php?id=100038061072521</h6>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/l.h.n_animol/" class="d-flex">
                            <i class="fa-2x fa-brands fa-instagram-square mr-1"></i>
                            <h6 class="txt1 no-wrap m-0">https://www.instagram.com/l.h.n_animol/</h6>
                        </a>
                    </li>
                </ul>
                </c:if>
            </div>
            <div class="suggestion mt-3">
                <div class="sg-title m-3 d-flex justify-content-between">
                    <h6 class="text-dark">Suggestions</h6>
                    <i class="fa fa-address-book m-1" aria-hidden="true"></i>
                </div>
                <div class="sg-content">
                    <ul class='sg-users p-0 ml-4 mr-5'>
                        <c:forEach items="${users}" var="u">
                        <li>
                            <a href="<c:url value="/profile/${u.username}"/>">
                            <img src="${u.avatar}" class="user-pic" />
                            <h6>${u.username}</h6>
                            </a>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-6 col-md-8">
            <div class="main-post-sec margin-resize-1">
                <section class="profile-header posts">
                    <div class="d-flex align-items-center"><i class="fa-2x fa-solid fa-circle-info text-success mr-2"></i><p class="txt1 m-0 h1 text-success">INFO</p></div>
                    <div class="posts border border-top-0 pt-3 pb-2 pl-4 pr-4 margin-resize-1">
                        <div class="row align-items-center mb-2">
                            <span class="txt1 h4 max-w-fit mr-1 col-auto m-0">${user.username}</span>
                        </div>
                        <span class="txt1 h6 text-secondary">${user.about}</span>
                    </div>
                </section>
                <section class="posts mt-4">
                    <div class="post-tab row border-bottom pt-2 pl-2 pr-2 tab-wrapper">
                        <div class="col-lg-1"></div>
                        <button class="col-auto border border-bottom-0 p-2 text-center w-25 rounded general tab active" data-post="norm-post">
                            <i class="fa-2x fa-solid fa-users"></i>
                            <span class="txt1 h6 m-0">Posts</span>
                        </button>
                        <button class="col-auto border border-bottom-0 p-2 text-center w-25 rounded ml-2 general tab" data-post="auction-post">
                            <i class="fa-2x fa-solid fa-gavel"></i>
                            <span class="txt1 h6 m-0">Auctions</span>
                        </button>
                    </div>
                    <div id="norm-post" class="tab-item active">
                        <c:forEach items="${posts}" var="p">
                        <c:if test="${p[4] == false}">
                            <div class="posts mt-2" id="post">
                                <div class="post border pb-0">
                                    <div class="post-head border-bottom">
                                        <div class="row justify-content-between">
                                            <div class="col-md-10 user-pi d-flex">
                                                <img src="${p[9]}" class="user-pic" />
                                                <div class="ml-2">
                                                    <h6 class="m-0">${p[8]}</h6>
                                                    <span><i class="fa-regular fa-clock"></i></span>
                                                    <span id="postedDate">${p[3]}</span>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <button class="border-0 button-ellips rounded-circle" data-toggle="collapse" data-target="#post-option${p[0]}"><i class="fa-2x fa-solid fa-ellipsis"></i></button>
                                                <c:choose>
                                                    <c:when test="${p[7] ==  currentUser.id}">
                                                        <div class="position-absolute bg-secondary p-2 collapse" id="post-option${p[0]}" style="top: -5rem;right: 1.5rem; width: 7rem; height: 5rem;border-radius: 10px">
                                                            <a href="<c:url value="/update-post/${p[0]}" />" id="up${p[0]}" class="btn btn-info txt1 mb-1 text-light" style="font-size: 12px;">Update Post</a>
                                                            <a href="<c:url value="/del-post?postId=${p[0]}" />" id="del${p[0]}" class="btn btn-danger txt1 text-light" style="font-size: 12px;">Delete Post</a>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${p[7] !=  currentUser.id}">
                                                        <div class="position-absolute bg-secondary p-2 collapse" id="post-option${p[0]}" style="top: -3rem;right: 1.5rem; width: 7rem; height: 5rem;border-radius: 10px">
                                                            <a href="<c:url value="/report?userId=${p[7]}" />" class="btn btn-info txt1 mb-1 text-light" style="font-size: 12px;">Report</a>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="post-body mt-3 pb-1 border-bottom">
                                        <div class="post-content">
                                            <h6 class="pr-3 pl-3">${p[1]}</h6>
                                        </div>
                                        <div class="post-pic">
                                            <img src="${p[2]}" class="border mt-1">
                                        </div>
                                        <div class="tag">
                                            
                                        </div>
                                    </div>
                                    <div id="tag-section${p[0]}" class="d-flex">Tags:</div>
                                    <div class="post-footer mt-1 mb-3 justify-content-between position-relative d-flex">
                                        <button class="btn btn-outline-light w-100 text-dark general" id="likebtn" type="button" data-toggle="button">
                                            <i class="fa-regular fa-thumbs-up"></i>
                                            <input type="button" name="like" value="Like" class="border-0 bg-transparent"/>
                                            <span>100</span>
                                        </button>
                                        <button class="btn btn-outline-light w-100 text-dark general" onclick="showComment(${p[0]}); this.disabled=true" id="commentsbtn${p[0]}" type="button">
                                            <i class="fa-solid fa-comment"></i>
                                            <input type="button" name="comment" value="Comment" class="border-0 bg-transparent"/>
                                        </button>
                                        <form action="<c:url value="/home" />" class="d-flex border p-1">
                                            <select name="tag">
                                                <c:forEach items="${tags}" var="c">
                                                    <option value="${c.id}" label="${c.tag}"></option>
                                                </c:forEach>
                                            </select>
                                            <input type="text" value="${p[0]}" name="postId" class="d-none"/>
                                            <button class="btn btn-success d-flex justify-content-center p-2 align-items-center border-0 ml-1" type="submit">
                                                <i class="fa-solid fa-plus text-light"></i>
                                                <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="submit" value="Add tag">
                                            </button>
                                        </form>
                                    </div>
                                    <div class="d-flex mb-2">
                                        <textarea placeholder="Share your thoughts..." id="comment-content${p[0]}" class="post-font border" name="comment"></textarea>
                                        <button class="btn btn-success d-flex justify-content-center p-2 align-items-center border-0 ml-1" onclick="addComment(${p[0]},${currentUser.id})" onmouseup="showComment(${p[0]})" type="button">
                                            <i class="fa-solid fa-plus text-light"></i>
                                            <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Comment">
                                        </button>
                                    </div>
                                    <div id="comment-section${p[0]}">
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        </c:forEach>
                    </div>
                    <div id="auction-post" class="tab-item">
                        <c:forEach items="${posts}" var="p">
                        <c:if test="${p[4] == true}">
                        <div class="posts mt-2" id="auction">
                            <div class="post border pb-0">
                                <div class="post-head border-bottom">
                                    <div class="row justify-content-between">
                                        <div class="col-md-10 user-pi d-flex">
                                            <img src="${p[9]}" class="user-pic" />
                                            <div class="ml-2">
                                                <h6 class="m-0">${p[8]}</h6>
                                                <span><i class="fa-regular fa-clock"></i></span>
                                                <span id="postedDate">${p[3]}</span>
                                            </div>
                                        </div>
                                        <div class="col-md-2 position-relative">
                                            <button class="border-0 button-ellips rounded-circle" data-toggle="collapse" data-target="#post-option${p[0]}"><i class="fa-2x fa-solid fa-ellipsis"></i></button>
                                            <c:choose>
                                                <c:when test="${p[7] ==  currentUser.id}">
                                                    <div class="position-absolute bg-secondary p-2 collapse" id="post-option${p[0]}" style="top: -5rem;right: 1.5rem; width: 7rem; height: 5rem;border-radius: 10px">
                                                        <a href="<c:url value="/update-post/${p[0]}" />" class="btn btn-info txt1 mb-1 text-light" style="font-size: 12px;">Update Post</a>
                                                        <a href="<c:url value="/del-post?postId=${p[0]}" />" class="btn btn-danger txt1 text-light" style="font-size: 12px;">Delete Post</a>
                                                    </div>
                                                </c:when>
                                                <c:when test="${p[7] !=  currentUser.id}">
                                                    <div class="position-absolute bg-secondary p-2 collapse" id="post-option${p[0]}" style="top: -5rem;right: 1.5rem; width: 7rem; height: 5rem;border-radius: 10px">
                                                        <a href="#" class="btn btn-info txt1 mb-1 text-light" style="font-size: 12px;">Report Post</a>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-body mt-3 pb-1 border-bottom">
                                    <div class="post-content">
                                        <h6 class="pr-3 pl-3">${p[1]}</h6>
                                        <div class="row align-items-center">
                                            <h6 class="col-md-6 m-0">Current Bid: ${p[5]}</h6>
                                            <button class="btn btn-outline-light w-100 text-dark col-md-6 general" id="bidbtn" type="button" data-toggle="modal" data-target="#auction-bid">
                                                <i class="fa-solid fa-gavel"></i>
                                                <input type="button" name="Bid" value="Name your price" class="border-0 bg-transparent"/>
                                            </button>
                                            <div class="modal hide fade" id="auction-bid" tabindex="-1" role="dialog" aria-labelledby="auction-bid" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                  <div class="modal-content">
                                                    <div class="modal-header">
                                                      <h5 class="modal-title" id="post-auction">Enter your bid</h5>
                                                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                      </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form>
                                                            <div class="wrap-input100 validate-input mt-2">
                                                                <input class="post-font" type="number" min="0" id="price" name="price" placeholder="Enter min item price" step="1000"/>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                      <button type="submit" class="btn btn-primary" data-dismiss="modal">Bid</button>
                                                    </div>
                                                  </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="post-pic">
                                        <img src="${p[2]}" class="border mt-1">
                                    </div>
                                </div>
                                <div id="tag-section${p[0]}" class="d-flex"></div>
                                <div class="post-footer mt-1 mb-1 d-flex justify-content-between position-relative">
                                    <button class="btn btn-outline-light w-100 text-dark general" id="likebtn" type="button" data-toggle="button">
                                        <i class="fa-regular fa-thumbs-up"></i>
                                        <input type="button" name="like" value="Like" class="border-0 bg-transparent"/>
                                        <span>100</span>
                                    </button>
                                    <button class="btn btn-outline-light w-100 text-dark general" onclick="showComment(${p[0]}); this.disabled=true" id="commentsbtn${p[0]}" type="button">
                                        <i class="fa-solid fa-comment"></i>
                                        <input type="button" name="comment" value="Comment" class="border-0 bg-transparent"/>
                                    </button>
                                    <form action="<c:url value="/home" />" class="d-flex border p-1">
                                        <select name="tag">
                                            <c:forEach items="${tags}" var="c">
                                                <option value="${c.id}" label="${c.tag}"></option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" value="${p[0]}" name="postId" class="d-none"/>
                                        <button class="btn btn-success d-flex justify-content-center p-2 align-items-center border-0 ml-1" type="submit">
                                            <i class="fa-solid fa-plus text-light"></i>
                                            <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="submit" value="Add tag">
                                        </button>
                                    </form>
                                </div>
                                <div class="d-flex mb-2">
                                    <textarea placeholder="Share your thoughts..." id="comment-content${p[0]}" class="post-font border" name="comment"></textarea>
                                    <button class="btn btn-success d-flex justify-content-center p-2 align-items-center border-0 ml-1" onclick="addComment(${p[0]},${currentUser.id})" onmouseup="showComment(${p[0]})" type="button">
                                        <i class="fa-solid fa-plus text-light"></i>
                                        <input class="txt1 text-light h6 bg-transparent border-0 m-0 p-0 w-auto" type="button" value="Comment">
                                    </button>
                                </div>
                                <div id="comment-section${p[0]}">
                                </div>
                            </div>
                        </div>
                        </c:if>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>
        <div class="col-md-2 col-lg-2">
            <div class="widget border border-top-0 posts mt-3 p-2">
                <div class="widget-header d-flex align-items-center b-2 border-bottom">
                    <h3 class="txt1 m-0">Media</h3>
                    <i class="fa-regular fa-image text-muted"></i>
                </div>
                <div class="widget-gallery mt-2">
                    <ul class="p-0 d-flex flex-wrap justify-content-between">
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245612/Java/wp5609581_u3xpe2.jpg" alt="alt"/>
                        </li>
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245593/Java/download_qimbd9.jpg" alt="alt"/>
                        </li>
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245588/Java/download_a1rlhw.jpg" alt="alt"/>
                        </li>
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245584/Java/download_lkwf3e.jpg" alt="alt"/>
                        </li>
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245612/Java/wp5609581_u3xpe2.jpg" alt="alt"/>
                        </li>
                        <li>
                            <img src="https://res.cloudinary.com/dyhp6kio1/image/upload/v1651245593/Java/download_qimbd9.jpg" alt="alt"/>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    
    
    window.onload = function (){
        let date = document.querySelectorAll("#postedDate");
        for(let i = 0; i < date.length; i ++){
            let d = date[i];
            d.innerText = formattime(d.innerText);
        }
        
        <c:forEach items="${posts}" var="p">
            loadTag(${p[0]});
        </c:forEach>
    };
    function showComment(postId){
        fetch("/BTL_clone/api/load-comments",{
            method: 'post',
            body: JSON.stringify({
                "postId": postId
            }),
            headers: {
            "Content-Type": "application/json"
            } 
        }).then(function(res){
            console.info(res);
            return res.json();
        }).then(function (data){
            let area = document.getElementById("comment-section"+postId);
            for (var i = 0; i < data.length; i++) {
                area.innerHTML = area.innerHTML + `
                        <div class="media border p-2 mt-1 align-items-center">
                                        <img src="`+ data[i].userId.avatar +`" alt="John Doe" class="mr-3 rounded-circle" style="width:60px;">
                                        <div class="media-body">
                                            <h6>`+ data[i].userId.username +`<small class="text-muted"><i class="fa-regular fa-clock ml-2 text-muted"></i> `+ formattime(data[i].createdDate) +`</small></h6>
                                            <p>`+ data[i].comment +`</p>
                                        </div>
                                    </div>`;
            }
        });
    };
    
    function addComment(postId, userId){
            $('#commentsbtn'+postId).prop('disabled', true);
            fetch("/BTL_clone/api/add-comment",{
            method: 'post',
            body: JSON.stringify({
                "comment": document.getElementById("comment-content"+postId).value,
                "postId": postId,
                "userId": userId
            }),
            headers: {
                "Content-Type": "application/json"
            } 
        }).then(function(res){
            console.info(res);
            return res.json();
        }).then(function (data){
            let area = document.getElementById("comment-section"+postId);
            area.innerHTML =`
                        <div class="media border p-2 mt-1 align-items-center">
                                        <img src="`+ data[0].userId.avatar +`" alt="John Doe" class="mr-3 rounded-circle" style="width:60px;">
                                        <div class="media-body">
                                            <h6>`+ data[0].userId.username +`<small class="text-muted"><i class="fa-regular fa-clock ml-2 text-muted"></i> `+ formattime(data[0].createdDate) +`</small></h6>
                                            <p>`+ data[0].comment +`</p>
                                        </div>
                                    </div>` + area.innerHTML;
        });
    };
    
    function loadTag(postId){
        fetch("/BTL_clone/api/load-tag",{
            method: 'post',
            body: JSON.stringify({
                "postId":postId
            }),
            headers: {
                "Content-Type": "application/json"
            } 
        }).then(function(res){
            console.info(res);
            return res.json();
        }).then(function (data){
            let area = document.getElementById("tag-section"+postId);
            for(var i = 0; i < data.length; i++){
            area.innerHTML = area.innerHTML + `<p class="txt1 w-auto ml-2 mt-2" style="font-size: 14px">`+ data[i].tag +`</p>`;
            }   
        });
    }

    function formattime(date){
        return moment(date).locale("vi").fromNow();
    };
    
</script>