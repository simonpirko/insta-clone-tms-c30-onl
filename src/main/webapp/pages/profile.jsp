<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 04.10.2024
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>

<div class="row">
    <jsp:include page="_menubar.jsp"/>
    <div class="row col-9">
        <div class="card w-100 mb-4">
            <div class="row g-0">
                <div class="col-md-4">
                    <img width="200px" height="200px"
                         src="data:image/jpeg;base64,${avatar}">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${usernameProfile}</h5>
                        <p class="card-text">
                        <ul class="nav nav-underline">

                            <li class="nav-item">
                                <a class="nav-link disabled" aria-disabled="true">${countPostProfile} posts</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/user/subscriber?curUser=${usernameProfile}">${countSubscriberProfile} Subscription </a>
                                <!--вставить ссылку на лист пользователей на которых подписан профиль(пользователь)-->
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/user/subscription?curUser=${usernameProfile}">${countSubscriptionProfile} Subscriber</a>
                                <!--вставить ссылку на лист пользователей которые подписаны на профиль(пользователя)-->
                            </li>
                        </ul>
                        </p>


                        <c:if test="${statusSubscriptionProfile == 0}">
                        <form class="col-4"
                              action="/user/refactorProfile/${usernameProfile}"
                              method="get"
                              enctype="multipart/form-data">
                            <button type="submit" class="btn btn-primary">Refactor profile</button>
                            </c:if>
                            <c:if test="${statusSubscriptionProfile == 1}">
                            <form class="col-4"
                                  action="/user/profileService/subscribe/${usernameSession}/${usernameProfile}"
                                  method="post"
                                  enctype="multipart/form-data">

                                <button type="submit" name="type" value="Subscribe" class="btn btn-primary">Subscribe
                                </button>
                            </form>
                            </c:if>
                            <c:if test="${statusSubscriptionProfile == 2}">
                            <form class="col-4"
                                  action="/user/profileService/unsubscribe/${usernameSession}/${usernameProfile}"
                                  method="post">
                                <button type="submit" name="type" value="Unsubscribe" class="btn btn-danger">
                                    Unsubscribe
                                </button>
                            </form>
                            </c:if>

                    </div>
                </div>
            </div>
            <div class="alert alert-primary" role="alert">
                Card Posts User
            </div>
            <div class="row">
                <c:forEach items="${profileDTO.getCardProfileDTOS()}" var="post">
                    <div class="card" style="width: 22rem;">
                        <div class="card-body">
                            <a href="/user/post?postUUID=${post.postUUID}">${post.getCreateAtPost()}</a>
                        </div>
                        <div id="${post.getCarouselName()}" class="carousel slide">
                            <div class="carousel-inner">
                                <c:forEach items="${post.getPhotosPost()}" var="photo">
                                    <div class="carousel-item active">
                                        <img width="200px" height="200px" src="data:image/jpeg;base64,${photo}" class="d-block w-100" alt="photo">
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#${post.getCarouselName()}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#${post.getCarouselName()}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>


</body>
</html>
