<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 22.09.2024
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container text-center">
    <div class="row align-items-start">
        <div class="col">
            <img src="https://localiq.com/wp-content/uploads/2022/01/how-to-add-location-on-instagram-location-example.jpg" >
        </div>
        <div class="col">
            <div class="flex-d justify-content-center">
                <img src="https://1000logos.net/wp-content/uploads/2017/02/insta-logo.png" width="60px">
                <p class="fs-1">Insta-clone</p>
            </div>
            <div class="row justify-content-center">
                <p class="fs-5">Enter your login and password</p>
                <form class="col-4" action="/login" method="post">
                    <div class="mb-3">
                        <label for="InputLogin" class="form-label">Login</label>
                        <input type="text" name="username" class="form-control" id="InputLogin">
                    </div>
                    <div class="mb-3">
                        <label for="InputPassword" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="InputPassword">
                        <c:if test="${isLoggingProblem}">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert"> Wrong username or password!
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
                <p class="fs-5">Don't have an account yet?</p>
                <a href="/reg">Registration</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>