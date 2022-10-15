<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Coding Factory Libra</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu" aria-controls="navMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navMenu">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
          </li>
          <li class="nav-item">
            <a href="${pageContext.request.contextPath}/privacy-policy" class="nav-link">Privacy Policy</a>
          </li>
          <li class="nav-item">
            <a href="${pageContext.request.contextPath}/contact" class="nav-link">Contact</a>
          </li>
          <c:choose>
              <c:when test="${userFound}">
              <li class="nav-item">
                <a href="${pageContext.request.contextPath}/books-borrowed" class="nav-link">Books Borrowed</a>
              </li>
             </c:when>
          </c:choose>
        </ul>
        <c:choose>
            <c:when test="${userFound}">
                <span class="nav-item text-white">Welcome ${user.firstname}</span>
                <form method="POST" action="${pageContext.request.contextPath}/logout">
                <button class="btn btn-primary" type="submit">Log Out</button>
                </form>
                </c:when>
        <c:otherwise>
            <form method="POST" action="${pageContext.request.contextPath}/login" class="d-flex " id="loginForm" role="login">
                  <input class="form-control me-2" name="userUsername"  id="username" type="text" placeholder="Username" placeholder="Username">
                  <input class="form-control me-2" name="userPassword"  id="password" type="password" placeholder="Password" placeholder="Password">
                  <button class="btn btn-primary" type="submit">Login</button>
                </form>
            <a href="${pageContext.request.contextPath}/register" class="text-white nav-link">Register</a>
        </c:otherwise>
        </c:choose>
      </div>
    </div>
  </nav>
  <c:if test="${user.isAdmin}">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Admin Panel</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor02">
          <ul class="navbar-nav me-auto">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Books</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" type="button" id="adminAddBook" href="#">Add Book</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" type="button" id="adminUpdateBook" href="#">Update / Delete Book</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Authors</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" type="button" id="adminAddAuthor" href="#">Add Author</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" type="button" id="adminSearchForAuthor" href="#">Show All Authors</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Categories</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" type="button" id="adminAddCategory" href="#">Add Category</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" id="adminShowCategoryList" href="#">Show All Categories</a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </c:if>
  
  
  <script src="${pageContext.request.contextPath}/static/js/jQuery.min.js?version=2"></script>
      <script src="${pageContext.request.contextPath}/static/js/titleSearch.js?version=34" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/authorSearch.js?version=46" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/categorySearch.js?version=34" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/isbnSearch.js?version=3" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/adminBook.js?version=93" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/adminAuthor.js?version=34" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/adminCategory.js?version=15 type="text/javascript"></script>