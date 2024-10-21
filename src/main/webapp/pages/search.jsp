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
            <form action="user/search" method="post">
                <div class="input-group mb-3">
                    <input  type="text" name="keyword" class="form-control"  id="exampleDataList" placeholder="Enter to search...">
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>
                <ul>
                    <c:forEach var="user" items="${users}">
                        <li><c:out value="${user}" />
                            <a href="/" class="nav-link align-middle px-0">
                                <span class="ms-1 d-none d-sm-inline">${user.username}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
