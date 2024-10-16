<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title page</title> <!-- вставить название страницы -->
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <!-- вставить контент -->
            <div class="container">
                <c:forEach items="${content}" var="contentValue">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="${contentValue.urlPublisher}" class="card-link">${contentValue.namePublisher}</a>
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
                        <div id="${contentValue.carouselName}" class="carousel slide">
                            <div class="carousel-inner">
                                <c:forEach items="${contentValue.photosLastPost}" var="Photo">
                                    <div class="carousel-item active">
                                        <img width="320px" height="320px" src="data:image/jpeg;base64,${Photo}" class="d-block w-100" alt="Photo">
                                    </div>
                            </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button"
                                    data-bs-target="#${contentValue.carouselName}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button"
                                    data-bs-target="#${contentValue.carouselName}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>

                        <form action="/user/home/event" method="post">
                            <button type="submit"
                                    class="btn btn-outline-primary btn-xs"
                                    name="button" value="like">${contentValue.likeBottom}</button>
                            <button type="submit"
                                    class="btn btn-outline-primary btn-xs"
                                    name="button" value="dislike">${contentValue.dislikeBottom}</button>
                            <button type="submit"
                                    class="btn btn-outline-primary btn-xs"
                                    name="button" value="comment">${contentValue.commentBottom}</button>
                        </form>

<%--                        <ul class="pagination">--%>
<%--                            <button type="button" class="btn btn-outline-primary btn-xs">${contentValue.likeBottom}</button>--%>
<%--                            <button type="button" class="btn btn-outline-primary btn-xs">${contentValue.dislikeBottom}</button>--%>
<%--                            <button type="button" class="btn btn-outline-primary btn-xs">${contentValue.commentBottom}</button>--%>
<%--                        </ul>--%>
                    </div>
                </c:forEach>
            </div>
            <!-- вставить контент -->
        </div>
    </div>
</div>
</body>
</html>