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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		
    <title>Document</title>
</head>
<body>
   
	<%@ include file="templates/navbar.jsp" %>
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

      <div id="searchResults" class="container-xxl mt-5 searchResults">
      </div>

      <div id="feedback" class="container-xl ">
      </div>
		</c:when>
		<c:otherwise>
      <div class="container-xxl mt-5 text-center text-bold">
        <h5 class="display-5">Please log in to browse books</h5>
      </div>

		</c:otherwise>
	</c:choose>
      

    
</body>
</html>

