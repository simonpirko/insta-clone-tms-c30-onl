<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">

            <div class="container">
                <c:forEach items="${content}" var="contentValue">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="${contentValue.urlPublisher}"
                                   class="card-link">${contentValue.namePublisher}</a>
                                <p class="card-text invisible">space</p>
                                <c:forEach items="${contentValue.createAtLastPost}" var="text">
                                    <p class="card-text">Time create post:</p>
                                    <p class="card-text invisible">_</p>
                                    <p class="card-text">${text}</p>
                                </c:forEach>
                            </ul>
                            <c:forEach items="${contentValue.textLastPostPublisher}" var="textPost">
                                <p class="card-text">${textPost}</p>
                            </c:forEach>
                        </div>
                        <div id="${contentValue.namePublisher}" class="carousel slide">
                            <div class="carousel-inner">
                                <c:forEach items="${contentValue.photosLastPost}" var="Photo">
                                    <div class="carousel-item active">
                                        <img width="320px" height="320px" src="data:image/jpeg;base64,${Photo}"
                                             class="d-block w-100" alt="Photo">
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button"
                                    data-bs-target="#${contentValue.namePublisher}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button"
                                    data-bs-target="#${contentValue.namePublisher}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <ul class="pagination">
                            <form action="/user/like" method="post">
                                <input type="hidden" value="${contentValue.uuidPost}" name="uuidPost">
                                <c:if test="${contentValue.countLikeLastPost > 0}">
                                    <c:if test="${contentValue.valueReaction == 'like'}">
                                        <button type="submit"
                                                class="btn btn-outline-info btn-xs disabled"
                                                name="button">Like - ${contentValue.countLikeLastPost}</button>
                                    </c:if>
                                    <c:if test="${contentValue.valueReaction == 'dislike'}">
                                        <button type="submit"
                                                class="btn btn-outline-primary btn-xs"
                                                name="button">Like - ${contentValue.countLikeLastPost}</button>
                                    </c:if>
                                    <c:if test="${contentValue.valueReaction == 'none'}">
                                        <button type="submit"
                                                class="btn btn-outline-primary btn-xs"
                                                name="button">Like - ${contentValue.countLikeLastPost}</button>
                                    </c:if>
                                </c:if>
                                <c:if test="${contentValue.countLikeLastPost <= 0}">
                                    <button type="submit" class="btn btn-outline-primary btn-xs"
                                            name="button">Like
                                    </button>
                                </c:if>
                            </form>
                            <form action="/user/dislike" name= "HomePostForm" method="post">
                                <input type="hidden" value="${contentValue.uuidPost}" name="uuidPost">
                                <c:if test="${contentValue.countDislikeLastPost > 0}">
                                    <c:if test="${contentValue.valueReaction == 'dislike'}">
                                        <button type="submit" class="btn btn-outline-info btn-xs disabled"
                                                name="button">Dislike - ${contentValue.countDislikeLastPost}</button>
                                    </c:if>
                                    <c:if test="${contentValue.valueReaction == 'like'}">
                                        <button type="submit" class="btn btn-outline-primary btn-xs"
                                                name="button">Dislike - ${contentValue.countDislikeLastPost}</button>
                                    </c:if>
                                    <c:if test="${contentValue.valueReaction == 'none'}">
                                        <button type="submit" class="btn btn-outline-primary btn-xs"
                                                name="button">Dislike - ${contentValue.countDislikeLastPost}</button>
                                    </c:if>
                                </c:if>
                                <c:if test="${contentValue.countDislikeLastPost <= 0}">
                                    <button type="submit" class="btn btn-outline-primary btn-xs"
                                            name="button">Dislike
                                    </button>
                                </c:if>
                            </form>
                            <a href="/user/post?postUUID=${contentValue.uuidPost}">
                                    <button type="submit"
                                            class="btn btn-outline-primary btn-xs"
                                            name="button">Comment
                                    </button>
                            </a>
                        </ul>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
</body>
</html>