<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Refactor profile</title> <!-- вставить название страницы -->
</head>
<body>
<jsp:include page="_bootstrap.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="_menubar.jsp"/>
        <div class="row col-9">
            <div class="container">
                <!-- вставить контент -->
                <div class="row g-0">
                    <div class="col-md-4">
                        <img width="200px" height="200px"
                             src="data:image/jpeg;base64,${DataProfile.getAvatar()}">
                    </div>
                    <form class="col-4" action="/user/refactorProfile/setAvatar/${DataProfile.getUsername()}"
                          method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="formFile" class="form-label">SET AVATAR</label>
                            <input class="form-control" name="avatar" type="file" id="formFile">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
                <div class="row g-0">

                   <%-- <form class="col-4" action="/user/refactorProfile/setName/${DataProfile.getUsername()}"
                          method="post" enctype="multipart/form-data">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="new Name"
                                   aria-label="name" aria-describedby="button-addon1">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Sumbit</button>
                        </div>
                    </form>
                </div>
                <div class="row g-0">

                    <form class="col-4" action="/user/refactorProfile/setUsername/${DataProfile.getUsername()}"
                          method="post" enctype="multipart/form-data">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="new Username"
                                   aria-label="username" aria-describedby="button-addon2">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon2">Sumbit</button>
                        </div>
                    </form>
                </div>--%>
                <!-- вставить контент -->
            </div>
        </div>
    </div>
</div>
</body>
</html>