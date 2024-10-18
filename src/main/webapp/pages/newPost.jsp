<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New post</title>
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <div class="container">
                <form class="col-4" action="/user/newPost" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="photosMultiple" class="form-label">Load photos</label>
                        <input class="form-control" name="photosMultiple" type="file" id="photosMultiple" multiple>
                        <div style="color: red">${errorMax}</div>
                    </div>
                    <div class="mb-3">
                        <label for="textPost" class="form-label">Text</label>
                        <input type="text" name="textPost" class="form-control" id="textPost">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>