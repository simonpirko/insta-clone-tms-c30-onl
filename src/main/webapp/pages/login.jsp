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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container text-center">
    <div class="row align-items-start">
        <div class="col">
            <img src="https://localiq.com/wp-content/uploads/2022/01/how-to-add-location-on-instagram-location-example.jpg" >
        </div>
        <div class="col">
            <div class="flex-d justify-content-center">
                <img src="https://1000logos.net/wp-content/uploads/2017/02/insta-logo.png" width="80px">
                <p class="fs-1">Insta-clone</p>
            </div>
            <div class="row justify-content-center">
                <p class="fs-5">Enter your login and password</p>
                <form class="col-4" action="/home" method="post">
                    <div class="mb-3">
                        <label for="exampleInputEmail2" class="form-label">Login</label>
                        <input type="text" name="username" class="form-control" id="exampleInputEmail2">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
                <p class="fs-5">Don't have an account yet?</p>
                <a href="/reg">Registration</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>