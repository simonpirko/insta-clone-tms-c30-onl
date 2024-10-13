<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="by.tms.instaclone.dto.UserHomePageDto" %>--%>
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
                <c:forEach items="${listExample}" var="listValue">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="${listValue.urlPublisher}" class="card-link">${listValue.namePublisher}</a>
                                <p class="card-text invisible">space</p>
                                <c:forEach items="${listValue.createAtLastPost}" var="text">
                                    <p class="card-text">Time create post:</p>
                                    <p class="card-text invisible">_</p>
                                    <p class="card-text">${text}</p>
                                </c:forEach>
                            </ul>
                            <c:forEach items="${listValue.textLastPostPublisher}" var="textPost">
                                <p class="card-text">${textPost}</p>
                            </c:forEach>
                        </div>
                        <div id="${listValue.carouselName}" class="carousel slide">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="..." class="d-block w-100" alt="...">
                                </div>
                                <div class="carousel-item">
                                    <img src="..." class="d-block w-100" alt="...">
                                </div>
                                <div class="carousel-item">
                                    <img src="..." class="d-block w-100" alt="...">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button"
                                    data-bs-target="#${listValue.carouselName}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button"
                                    data-bs-target="#${listValue.carouselName}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                            <button type="button" class="btn btn-outline-primary">Like</button>
                            <button type="button" class="btn btn-outline-primary">Dislike</button>
                            <button type="button" class="btn btn-outline-primary">Comment</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- вставить контент -->
        </div>
    </div>
</div>
</body>
</html>