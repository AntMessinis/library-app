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
  
	<body>
        <div class="container-xl bigger-margin">
            <form>
                <fieldset>
                  <h3 class="display-3 ">Contact Us</h3>
                    <div class="form-group">
                    <label for="contactEmail" class="form-label mt-4">Your Email address</label>
                    <input type="email" class="form-control" id="contactEmail" aria-describedby="emailHelp" placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                  </div>
                  <div class="form-group">
                    <label for="message" class="form-label mt-4" placeholder="Enter message">Your Message</label>
                    <textarea class="form-control" id="message" rows="10"></textarea>
                  </div>
                  <button type="submit" class="btn btn-primary">Contact Us</button>
                </fieldset>
              </form>
        </div>
</body>
</html>