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
                                <a href="/user/search" class="nav-link px-0 align-middle">
                                    <img width="32px" height="32px" src="https://st3.depositphotos.com/1507819/32085/v/450/depositphotos_320853210-stock-illustration-magnifier-vector-icon-on-black.jpg">
                                    <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Search</span>
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
                        </ul>
                        <hr>
                        <div class="dropdown pb-4">
                            <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                                <span class="d-none d-sm-inline mx-1">${currentUser.username}</span> <!-- вставить логин -->
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                                <li><a class="dropdown-item" href="/user/setting">Settings</a></li>
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