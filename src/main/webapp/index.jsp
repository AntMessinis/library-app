<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library App Antonis Messinis</title>
    
    
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

     <!-- CSS only -->
	  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- JavaScript Bundle with Popper -->
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
      
    <title>Document</title>
</head>
<body>
   <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Coding Factory Libra</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu" aria-controls="navMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">About Us</a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">Contact</a>
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

      
      <div class="container-xl mt-5">
        <form class="d-flex">
          <input id="searchInput" class="form-control me-sm-2" type="text" placeholder="Search by Title, Author or Category...">
          <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Search for</button>
          <ul class="dropdown-menu">
            <li><a id="titleSearchBtn" for="#searchInput" class="dropdown-item">Title</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><button class="dropdown-item" href="${pageContext.request.contextPath}/searchByAuthor">Author</button></li>
            <li><hr class="dropdown-divider"></li>
            <li><button class="dropdown-item" href="${pageContext.request.contextPath}/searchByCategory">Category</button></li>
          </ul>
        </form>
      </div>
      <div id="searchResults" class="container-xl mt-5 searchResults">
        
      </div>
      <div id="feedback"></div>

      <script src="${pageContext.request.contextPath}/static/js/titleSearch.js" type="text/javascript"></script>
</body>
</html>

