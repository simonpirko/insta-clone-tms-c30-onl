<div class="col-3">
    <div class="sticky-top">
        <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto bg-dark">
                    <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                        <a href="/user/home" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <img width="32px" height="32px" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUMUlHga7xgNxM2E8CxJIG8_VdJf_n5itqLk5Nv5uKKXpG-2znXgjOjrhrPIBOsYgySM0&usqp=CAU">
                            <span class="fs-5 d-none d-sm-inline">Insta-clone</span>
                        </a>
                        <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="/user/home" class="nav-link align-middle px-0">
                                    <img width="32px" height="32px" src="https://st4.depositphotos.com/20524830/26030/i/450/depositphotos_260309124-stock-photo-house-icon-commerce-set-glyph.jpg">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="/user/newPost" class="nav-link px-0 align-middle">
                                    <img width="32px" height="32px" src="https://us.123rf.com/450wm/piren/piren1703/piren170301332/74444943-%D0%B7%D0%BD%D0%B0%D1%87%D0%BE%D0%BA-%C2%AB%D0%BF%D0%BB%D1%8E%D1%81%C2%BB-%D0%BD%D0%B0-%D1%87%D0%B5%D1%80%D0%BD%D0%BE%D0%BC-%D1%84%D0%BE%D0%BD%D0%B5.jpg">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">New post</span>
                                </a>
                            </li>
                            <li>
                                <a href="/user/profile/${currentUser.username}" class="nav-link px-0 align-middle">
                                    <img width="32px" height="32px" src="https://media.istockphoto.com/id/1126403787/ru/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0%D1%8F/%D0%B7%D0%BD%D0%B0%D1%87%D0%BE%D0%BA-%D0%BF%D1%80%D0%BE%D1%84%D0%B8%D0%BB%D1%8F-%D0%BD%D0%B0-%D1%87%D0%B5%D1%80%D0%BD%D0%BE%D0%BC-%D1%84%D0%BE%D0%BD%D0%B5-%D0%B4%D0%BB%D1%8F-%D0%B3%D1%80%D0%B0%D1%84%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B3%D0%BE-%D0%B8-%D0%B2%D0%B5%D0%B1-%D0%B4%D0%B8%D0%B7%D0%B0%D0%B9%D0%BD%D0%B0-%D1%81%D0%BE%D0%B2%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9-%D0%BF%D1%80%D0%BE%D1%81%D1%82%D0%BE%D0%B9.jpg?s=612x612&w=0&k=20&c=Elhcmz7nFU5Op_knlcyMjO5fDbATWuSX7L0Q8UOO1Cw=">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Profile</span>
                                </a>
                            </li>
                            <li>
                                <a href="/user/recommendations?currentUser=${currentUser.username}" class="nav-link px-0 align-middle">
                                    <img width="32px" height="32px" src="https://media.istockphoto.com/id/656121894/ru/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0%D1%8F/%D0%B3%D1%80%D1%83%D0%BF%D0%BF%D0%B0-%D0%BB%D1%8E%D0%B4%D0%B5%D0%B9-%D0%B7%D0%BD%D0%B0%D1%87%D0%BE%D0%BA-%D1%87%D0%B5%D1%80%D0%BD%D1%8B%D0%B9-%D1%84%D0%BE%D0%BD.jpg?s=170667a&w=0&k=20&c=FH9rH9jRAOQgJyY3mf4Al3DQ-zopg702aU70LDN9Dxw=">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Recommendations</span>
                                </a>
                            </li>
                            <li>
                                <form class="d-flex" action="/user/search" method="Post">
                                    <div class="container">
                                        <input class="form-control me-2" type="search" name="keyword" placeholder="Search"
                                               aria-label="Search">
                                        <div class="row">
                                            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                                <input type="radio" class="btn-check" name="btnradio" value="users" id="btnradio1"
                                                       autocomplete="off" checked>
                                                <label class="btn btn-outline-primary" for="btnradio1">
                                                    <img src="https://www.citypng.com/public/uploads/preview/white-user-member-guest-icon-png-image-701751695037005zdurfaim0y.png"
                                                         alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                                                    Users
                                                </label>

                                                <input type="radio" class="btn-check" name="btnradio" value="posts" id="btnradio2"
                                                       autocomplete="off">
                                                <label class="btn btn-outline-primary" for="btnradio2">
                                                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUMUlHga7xgNxM2E8CxJIG8_VdJf_n5itqLk5Nv5uKKXpG-2znXgjOjrhrPIBOsYgySM0&usqp=CAU"
                                                         alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                                                    Posts
                                                </label>
                                                <button class="btn btn-outline-success" type="submit">Search</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </li>
                        </ul>
                        <div class="dropdown pb-4">
                            <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="data:image/jpeg;base64,${currentUserAvatar}" alt="hugenerd" width="30" height="30" class="rounded-circle">
                                <span class="d-none d-sm-inline mx-1">${currentUser.username}</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                                <li><a class="dropdown-item" href="/user/refactorProfile/${currentUser.username}">Settings</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="/user/logout">Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>