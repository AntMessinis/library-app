<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library App Antonis Messinis</title>
     <!-- CSS only -->
	  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
      
    <title>Document</title>
</head>
<body>
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
                    <a class="dropdown-item" type="button" id="adminUpdateBook" href="#">Update Book Details</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" id="adminDeleteBook" href="#">Delete Book from database</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Authors</a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" type="button" id="adminAddAuthor" href="#">Add Author</a>
                    <a class="dropdown-item" type="button" id="adminUpdateAuthor" href="#">Update Author Details</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" type="button" id="adminDeleteAuthor" href="#">Delete Author from database</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Categories</a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" type="button" id="adminAddCategory" href="#">Add Category</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" id="adminDeleteCategory" href="#">Delete Category from database</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Analytics</a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Month Statistics</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">More Analytics</a>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </c:if>

	<c:choose>
		<c:when test="${userFound}">

     	 <div class="container-xxl mt-5" id="mainContainer">
        <h5 class="display-5">Welcome to CF Library ${user.firstname}</h5>
        <p>Search for books below</p>
        <form class="d-flex">
          <input id="searchInput" class="form-control me-sm-2" type="text" placeholder="Search books by Title, ISBN, Author or Category...">
          <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Search by</button>
          <ul class="dropdown-menu">
            <li><a type="button" id="titleSearchBtn" class="dropdown-item">Title</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a type="button" id="isbnSearchBtn" class="dropdown-item" >ISBN</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a type="button" id="authorSearchBtn" class="dropdown-item" >Author</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a type="button" id="categorySearchBtn"  class="dropdown-item" >Category</a></li>
          </ul>
        </form>
      </div>

      <div id="searchResults" class="container-xl mt-5 searchResults">
      </div>

      <div id=" container-xl feedback">
      </div>
		</c:when>
		<c:otherwise>
      <div class="container-xxl mt-5 text-center text-bold">
        <h5 class="display-5">Please log in to browse books</h5>
      </div>

		</c:otherwise>
	</c:choose>
      

      <script src="${pageContext.request.contextPath}/static/js/jQuery.min.js?version=2"></script>
      <script src="${pageContext.request.contextPath}/static/js/titleSearch.js?version=27" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/authorSearch.js?version=41" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/categorySearch.js?version=30" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/isbnSearch.js?version=2" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/adminBook.js?version=40" type="text/javascript"></script> 
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>

