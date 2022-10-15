<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library App Antonis Messinis</title>
    <!-- CSS only -->
	 <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" >
     <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/contact.css">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    
</head>
<body>
  <%@ include file="/templates/navbar.jsp" %>
    <div class="container-xxl">
        <div class="row mt-5">
            <div class="col-7">
              <img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">
            </div>
            <div class="col-5">
              <p class="display-6"><span class="fw-bold text-decoration-underline">LibraryID:</span> ${book.id}</p>
              <p class="display-6"><span class="fw-bold text-decoration-underline">Author:</span> ${book.author.lastname}, ${book.author.firstname}</p>
              <p class="display-6"><span class="fw-bold text-decoration-underline">ISBN:</span> ${book.isbn}</p>
              <p class="display-6"><span class="fw-bold text-decoration-underline">Category:</span> ${book.category.categoryName}, ${book.category.subcategoryName}</p>
              <p class="display-6"><span class="fw-bold text-decoration-underline">Language:</span> ${book.language.languageName}</p>
              <p class="display-6"><span class="fw-bold text-decoration-underline">Copies availiable:</span> ${book.copiesInLibrary}</p>
              <a type="button" class="btn btn-primary" id="borrowButton">Request to borrow</a>
            </div>
          </div>
          <div class="row mt-5">
            <hr>
            <h1 class="display-1">Description</h1>
            <p class="mt-5 mb-5">${book.description}</p>
          </div>`;
    </div>
    
</body>