<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.10.2024
  Time: 23:41
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
    <form class="col-4"   action="/post" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="formFile" class="form-label">Default file input example</label>
            <input class="form-control" name="photo" type="file" id="formFile">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</div>
</div>
</body>
</html>
