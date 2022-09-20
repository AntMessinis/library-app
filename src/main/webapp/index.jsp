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
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
      
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
                <a class="nav-link" href="${pageContext.request.contextPath}/about-us">About Us</a>
              </li>
              <li class="nav-item">
                <a href="${pageContext.request.contextPath}/privacy-policy" class="nav-link">Privacy Policy</a>
              </li>
              <li class="nav-item">
                <a href="${pageContext.request.contextPath}/contact" class="nav-link">Contact</a>
              </li>
            </ul>
            <form class="d-flex " role="login">
              <input class="form-control me-2" type="text" placeholder="Username" placeholder="Username">
              <input class="form-control me-2" type="password" placeholder="Password" placeholder="Password">
              <button class="btn btn-primary" type="submit">Login</button>
            </form>
          </div>
        </div>
      </nav>

      <img src="../webapp/" alt="">
      <div class="container-xl mt-5">
        <form class="d-flex">
          <input id="searchInput" class="form-control me-sm-2" type="text" placeholder="Search by Title, ISBN, Author or Category...">
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
      <div id="feedback">
      </div>

      <script src="${pageContext.request.contextPath}/static/js/jQuery.min.js?version=2"></script>
      <script src="${pageContext.request.contextPath}/static/js/titleSearch.js?version=24" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/authorSearch.js?version=39" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/categorySearch.js?version=5" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/static/js/isbnSearch.js?version=2" type="text/javascript"></script> 
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>

