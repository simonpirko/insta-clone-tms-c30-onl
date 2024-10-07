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
    <form class="col-7"   action="/post" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="formFileMultiple" class="form-label">Select Photos</label>
            <input class="form-control" name="photo" maxlength="5"  type="file" id="formFileMultiple" multiple>
        </div>
        <div class="mb-7">
            <label for="exampleFormControlTextarea1" class="form-label">Post Text</label>
            <textarea class="form-control" name="PostText" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</div>
</div>
</body>
</html>
