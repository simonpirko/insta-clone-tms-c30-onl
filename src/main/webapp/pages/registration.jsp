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
            <img src="https://localiq.com/wp-content/uploads/2022/01/how-to-add-location-on-instagram-location-example.jpg">
        </div>
        <div class="col">
            <div class="flex-d justify-content-center">
                <img src="https://1000logos.net/wp-content/uploads/2017/02/insta-logo.png" width="60px">
                <p class="fs-1">Insta-clone</p>
            </div>
            <div class="row justify-content-center">
                <p class="fs-5">Enter your name, login and password</p>
                <form class="col-4" action="/reg" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Name</label>
                        <input type="text" name="name" class="form-control" id="exampleInputEmail1" value="${name}">
                        <div style="color: red">${errorName}</div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail2" class="form-label">Username</label>
                        <input type="text" name="username" class="form-control" id="exampleInputEmail2" value="${username}">
                        <div style="color: red">${errorUsername}</div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" value="${password}">
                        <div style="color: red">${errorPassword}</div>
                    </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">SET AVATAR</label>
                            <input class="form-control" name="avatar" type="file" id="formFile">
                        </div>
                    <button type="submit" class="btn btn-primary">Registration</button>
                </form>
                <p class="fs-5">Already have an account?</p>
                <a href="/login">login</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>