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
    <title>Title</title>
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
                         src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUMUlHga7xgNxM2E8CxJIG8_VdJf_n5itqLk5Nv5uKKXpG-2znXgjOjrhrPIBOsYgySM0&usqp=CAU">
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
                                <a class="nav-link" href="#">${countSubscriberProfile} Subscriber</a>
                                <!--вставить ссылку на лист пользователей на которых подписан профиль(пользователь)-->
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">${countSubscriptionProfile} Subscription</a>
                                <!--вставить ссылку на лист пользователей которые подписаны на профиль(пользователя)-->
                            </li>
                        </ul>
                        </p>
                        <c:if test="${statusSubscriptionProfile == 0}">
                            <button type="button" class="btn btn-primary">Remake</button>
                        </c:if>
                        <c:if test="${statusSubscriptionProfile == 1}">
                            <button type="button" class="btn btn-primary">Subscribe</button>
                        </c:if>
                        <c:if test="${statusSubscriptionProfile == 2}">
                            <button type="button" class="btn btn-danger">Unsubscribe</button>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="alert alert-primary" role="alert">
                Card Posts User
            </div>
        </div>
    </div>

</div>


</body>
</html>
