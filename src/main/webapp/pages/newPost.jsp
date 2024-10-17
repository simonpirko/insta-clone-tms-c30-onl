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
            <div class="container">
                <!-- вставить контент -->
                <form action="/user/newPost" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="photosMultiple" class="form-label">Load photos (Maximum 5 photos per post)</label>
                        <input class="form-control" name="photosMultiple" type="file" id="photosMultiple" multiple>
                        <div style="color: red">${errorMax}</div>
                        <div id="gallery"></div>
                    </div>
                    <div class="mb-3">
                        <label for="textPost" class="form-label">Text</label>
                        <input type="text" name="textPost" class="form-control" id="textPost">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
                <!-- вставить контент -->
            </div>
        </div>
    </div>
</div>
<script>
    function updateImageDisplay() {
        const curFiles = document.getElementById('photosMultiple').files;
        const gallery = document.getElementById('gallery');
        gallery.innerHTML = ''; // очищаем галерею от прежних миниатюр

        for(const file of curFiles) {
            const imageObjectUrl = URL.createObjectURL(file);
            const imgElement = document.createElement('img');
            imgElement.style.maxWidth = '150px';
            imgElement.src = imageObjectUrl; // каждому изображению свойственно иметь свой URL

            gallery.appendChild(imgElement); // галерея пополняется с каждой новой загрузкой 🖼️
        }
    }

    document.getElementById('photosMultiple').addEventListener('change', updateImageDisplay);
</script>
</body>
</html>