<%--
  Created by IntelliJ IDEA.
  User: Al
  Date: 07.10.2024
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container text-center">
    <div class="row align-items-start">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <div class="container">
                <div class="card mx-auto p-2 float-start" style="width: 30rem;">
                    <button type="button" class="btn btn-outline-primary"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left float-start" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8"/>
                    </svg> Publications</button>
                    <div id="carouselExample" class="carousel slide">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="${postimage1}" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="${postimage2}" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="${postimage3}" class="d-block w-100" alt="...">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${username}</h5>
                        <p class="card-text">${textpost}</p>
                        <div class="btn-group" role="group" aria-label="Basic outlined example">
                            <button type="button" class="btn btn-outline-primary"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                                <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                            </svg> </button>
                            <button type="button" class="btn btn-outline-primary"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
                                <path d="M2.678 11.894a1 1 0 0 1 .287.801 11 11 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8 8 0 0 0 8 14c3.996 0 7-2.807 7-6s-3.004-6-7-6-7 2.808-7 6c0 1.468.617 2.83 1.678 3.894m-.493 3.905a22 22 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a10 10 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105"/>
                            </svg> </button>
                            <button type="button" class="btn btn-outline-primary"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                                <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"/>
                            </svg> </button>
                        </div>
                    </div>
                </div>
                <p>
                    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Comments
                    </a>
                </p>
                <div class="collapse" id="collapseExample">
                    <h5 class="card-title">${username1}</h5>
                    <div class="card card-body">
                        ${comment1}
                    </div>
                    <div class="collapse" id="collapseExample">
                        <h5 class="card-title">${username2}</h5>
                        <div class="card card-body">
                            ${comment2}
                        </div>
                    </div>
                    <div class="collapse" id="collapseExample">
                        <h5 class="card-title">${username3}</h5>
                        <div class="card card-body">
                            ${comment3}
                        </div>
                        <div class="collapse" id="collapseExample">
                            <h5 class="card-title">${username4}</h5>
                            <div class="card card-body">
                                ${comment4}
                            </div>
                            <div class="input-group-sm mb-3 body-tertiary">
                                <span class="input-group-text"</span>
                                <input type="text" class="form-control" placeholder="Write a comment..." aria-label="Write a comment..." aria-describedby="basic-addon1">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2">send</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>