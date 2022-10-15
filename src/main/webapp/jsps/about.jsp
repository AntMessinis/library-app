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


      <div id="mainContainer" class="container-xxl text-center mt-5">
        <h4 class="display-4">About Antonis <br>(Creator of CF Libra)</h4>
        <div class="row">
          <div class="col-4"><img class="img-fluid rounded mx-auto d-block" src="${pageContext.request.contextPath}/static/imgs/ben-kolde-bs2Ba7t69mM-unsplash.jpg" alt=""></div>
          <div class="col-8">
            <p>I'm an aspiring Software Engineer / Backend Developer with a passion for technology and programming. My favourite programming languages are Java, C# and Rust.</p>
          </div>
        </div>
      </div>
      
      
</body>
</html>