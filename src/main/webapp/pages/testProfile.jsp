<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.10.2024
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container text-center">
    <div class="row align-items-start">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9 bg-white">
            <img src="${pathToImage}">
        </div>
    </div>
</div>
</body>
</html>
