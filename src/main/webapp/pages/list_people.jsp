<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recommendations page</title> <!-- вставить название страницы -->
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <div class="container">
                <c:forEach items="${userDTOS}" var="resultValue">
                    <a href="${resultValue.urlUser}" class="card mb-3" style="max-width: 360px; max-height: 70px">
                        <div class="row g-0">
                            <div class="row align-items-center">
                                <div class="col-md-4">
                                    <img src="data:image/jpeg;base64,${resultValue.avatar}" width="60" height="60" class="rounded-circle">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">${resultValue.username}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>