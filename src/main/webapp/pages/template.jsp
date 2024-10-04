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

            <p class="fs-5">${nameCurrentUser}</p>

            <!-- вставить контент -->
        </div>
    </div>
</div>
</body>
</html>