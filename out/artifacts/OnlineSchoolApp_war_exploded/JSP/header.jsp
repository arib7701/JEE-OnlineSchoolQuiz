<div class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
    <div class="container">
        <a href="getHome" class="navbar-brand">OnlineSchool</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav" >
                <li class="nav-item">
                    <a class="nav-link" href="getHome">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="about">About</a>
                </li>
            </ul>

            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <c:if test="${sessionScope.intern != null}">
                        <a class="nav-link" href="profileIntern">Profil</a>
                    </c:if>
                    <c:if test="${sessionScope.teacher != null}">
                        <a class="nav-link" href="profileTeacher">Profil</a>
                    </c:if>
                    <c:if test="${sessionScope.admin != null}">
                        <a class="nav-link" href="profileAdmin">Profil</a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${sessionScope.admin == null and sessionScope.teacher == null and sessionScope.intern == null}">
                        <a class="nav-link" href="login">Log In</a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${sessionScope.admin != null or sessionScope.teacher != null or sessionScope.intern != null}">
                        <a class="nav-link" href="logout">LogOut</a>
                    </c:if>
                </li>
            </ul>

        </div>
    </div>
</div>
