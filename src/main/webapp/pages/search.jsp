<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 12.10.2024
  Time: 00:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <div class="container">
                <ul>
                    <c:if test="${type == 'users'}">
                    <c:forEach items="${result}" var="resultValue">
                    <a href="${resultValue.urlUser}" class="card mb-3" style="max-width: 240px; max-height: 70px">
                        <div class="row g-0">
                            <div class="row align-items-center">
                                <div class="col-md-4">
                                    <img src="data:image/jpeg;base64,${resultValue.avatar}" width="60" height="60"
                                         class="rounded-circle">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">${resultValue.username}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                    </c:forEach>
                    </c:if>
                    <c:if test="${type == 'posts'}">
                    <c:forEach items="${result}" var="resultValue">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="${resultValue.urlPublisher}"
                                   class="card-link">${resultValue.username}</a>
                                <p class="card-text invisible">space</p>
                                <p class="card-text">Time create post:</p>
                                <p class="card-text invisible">_</p>
                                <p class="card-text">${resultValue.createdAt}</p>
                            </ul>
                            <c:forEach items="${resultValue.textPost}" var="textPost">
                                <p class="card-text">${resultValue.textPost}</p>
                            </c:forEach>
                        </div>
                        <div id="${resultValue.username}" class="carousel slide">
                            <div class="carousel-inner">
                                <c:forEach items="${resultValue.photos}" var="Photo">
                                    <div class="carousel-item active">
                                        <img width="320px" height="320px" src="data:image/jpeg;base64,${Photo}"
                                             class="d-block w-100" alt="Photo">
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button"
                                    data-bs-target="#${resultValue.username}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button"
                                    data-bs-target="#${resultValue.username}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <ul class="pagination">
                            <form action="/user/like" name="SearchPostForm" method="post">
                                <input type="hidden" value="${resultValue.postUUID}" name="uuidPost">
                                <c:if test="${resultValue.likes > 0}">
                                    <button type="submit"
                                            class="btn btn-outline-info btn-xs disabled"
                                            name="button">Like - ${resultValue.likes}</button>
                                </c:if>
                                <c:if test="${resultValue.likes <= 0}">
                                    <button type="submit" class="btn btn-outline-primary btn-xs"
                                            name="button">Like
                                    </button>
                                </c:if>
                            </form>
                            <form action="/user/dislike" name="SearchPostForm" method="post">
                                <input type="hidden" value="${resultValue.postUUID}" name="uuidPost">
                                <c:if test="${resultValue.dislikes > 0}">
                                    <button type="submit" class="btn btn-outline-info btn-xs disabled"
                                            name="button">Dislike - ${resultValue.dislikes}</button>
                                </c:if>
                                <c:if test="${resultValue.dislikes <= 0}">
                                    <button type="submit" class="btn btn-outline-primary btn-xs"
                                            name="button">Dislike
                                    </button>
                                </c:if>
                            </form>
                            <a href="/user/post?postUUID=${resultValue.postUUID}">
                                <button type="submit"
                                        class="btn btn-outline-primary btn-xs"
                                        name="button">Comment
                                </button>
                            </a>
                        </ul>
                    </div>
                    </c:forEach>
            </div>
            </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>