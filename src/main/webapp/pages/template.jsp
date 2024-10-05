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
                <c:if test="${isName1}">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="#" class="card-link">${nameCurrentUser}</a>
                                <p class="card-text invisible">space</p>
                                <p class="card-text">Time create post:</p>
                                <p class="card-text invisible">_</p>
                                <p class="card-text">${dateTimePost}</p>
                            </ul>
                        </div>
<%--                        <div id="carouselExample" class="carousel slide">--%>
                        <div id="${carousel}" class="carousel slide">
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
                            <button class="carousel-control-prev" type="button" data-bs-target="#${carousel}"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#${carousel}"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${textPostCurrentUser}</p>
                        </div>
                        <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                            <button type="button" class="btn btn-outline-primary">Like</button>
                            <button type="button" class="btn btn-outline-primary">Dislike</button>
                            <button type="button" class="btn btn-outline-primary">Comment</button>
                        </div>
                    </div>
                </c:if>
                <c:if test="${isName1}">
                    <div class="card" style="width: 35rem;">
                        <div class="card-body">
                            <ul class="pagination">
                                <a href="#" class="card-link">${nameCurrentUser}</a>
                                <p class="card-text invisible">space</p>
                                <p class="card-text">Time create post:</p>
                                <p class="card-text invisible">_</p>
                                <p class="card-text">${dateTimePost}</p>
                            </ul>
                        </div>
                        <div id="carouselExample2" class="carousel slide">
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
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample2"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample2"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${textPostCurrentUser}</p>
                        </div>
                        <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                            <button type="button" class="btn btn-outline-primary">Like</button>
                            <button type="button" class="btn btn-outline-primary">Dislike</button>
                            <button type="button" class="btn btn-outline-primary">Comment</button>
                        </div>
                    </div>
                </c:if>
            </div>
            <!-- вставить контент -->
        </div>
    </div>
</div>
</body>
</html>