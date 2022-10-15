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
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    

    <title>Document</title>
</head>
<body>
	<%@ include file="/templates/navbar.jsp" %>
	<div class="container-xxl">
	<h3 id="bookFormTitle mt-5">Add a new Title to the Library</h3>
    <form id="bookForm">
        <div class="row">
            <input id=bookId class="visually-hidden form-control me-sm-2" type="number">
        </div>
        <div class="row align-items-center">
        <div class="col-4">  
        <label for="bookTitle" class="form-label mt-3">Title</label>
        <input id="bookTitle" class="form-control me-sm-2" type="text" placeholder="Title">
        </div>
        <div class="col-4">  
        <label for="bookIsbn" class="form-label mt-3">ISBN</label>
        <input id="bookIsbn" class="form-control me-sm-2" type="text" placeholder="ISBN"></div>
        <div class="col-4">
        <input id=authorId class="visually-hidden form-control me-sm-2" type="number"> 
        <label for="authorSelect" class="form-label mt-3">Author</label>
        <select class="form-select" id="authorSelect" placeholder="Choose Author">        
        </select></div>
        </div>
        <div class="row align-items-center">
        <div class="col-4">
        <input id=categoryId class="visually-hidden form-control me-sm-2" type="number">   
        <label for="categorySelect" class="form-label mt-3">Category</label>
        <select class="form-select" id="categorySelect" placeholder="Choose Category">
        </select></div>
        <div class="col-4">
        <input id=languageId class="visually-hidden form-control me-sm-2" type="number">   
        <label for="languageSelect" class="form-label mt-3">Language</label>
        <select class="form-select" id="languageSelect" placeholder="Choose Language">        
        </select></div>
        <div class="col-4">  
        <label for="copiesInLibrary" class="form-label mt-3">Copies in Library</label>
        <input id="copiesInLibrary" class="form-control me-sm-2" type="number" min="1" max="20">
        </div>
        </div>
        <div class="row align-items-center"><label for="bookDescription" class="form-label mt-3">Description</label>
        <textarea id="bookDescription" class="form-control me-sm-2" placeholder="Description" rows="10"></textarea></div>
        <div id="bookFormButtons" class="form-group row mt-3 justify-content-between">
            <div class="col-md-3">
                <button type="submit" id="bookFormSubmitButton" class="btn btn-primary">Submit</button>
                <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
            </div>
        </div>          
    </form>
</div>
</body>