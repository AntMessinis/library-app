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

  <div id="mainContainer" class="container-xxl mt-5">
    <h2 class="display-2">Become a Member</h2>
    <span id="feedback"></span>
    <form method="post" id="registrationForm">
        <div class="form-group row mt-3">
            <div class="col-md-6 mt-3">
            <label for="newUserUsername" class="form-label">Username</label>
            <input type="text" class="form-control" id="newUserUsername" name="newUserUsername" placeholder="JDoe">
          </div>
          <div class="col-md-6 mt-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" placeholder="johndoe@codingfactory.aueb">
          </div>
        </div>
        
        <div class="form-group row mt-3">
            <div class="col-md-6 mt-3">
                <label for="newUserPassword" class="form-label">Password</label>
                <input type="password" id="newUserPassword" name="newUserPassword" class="form-control">
                <small id="emailHelp" class="form-text text-muted">Password must be at least 8 characters long and must contain CAPITAL letters, lower case letters, numbers and special characters</small>
            </div>
            <div class="col-md-6 mt-3">
                <label for="repeatPassword" class="form-label">Repeat Password</label>
                <input type="password" id="repeatPassword" class="form-control">
                <small id="emailHelp" class="form-text text-muted">Password must be at least 8 characters long and must contain CAPITAL letters, lower case letters, numbers and special characters</small>
            </div>
        </div>
        <legend class="mt-5">Personal Info</legend>
        <div class="form-group row mt-3">
            <div class="col-md-6">
                <label for="firstname" class="form-label">Firstname</label>
                <input type="text" class="form-control" name="firstname" id="firstname" placeholder="John">
            </div>
            <div class="col-md-6">
                <label for="lastname" class="form-label">Lastname</label>
                <input type="text" class="form-control" name="lastname" id="lastname" placeholder="Doe">
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-md-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" name="address" id="address" placeholder="Stadiou 123">
            </div>
            <div class="col-md-3">
                <label for="postalCode" class="form-label">Postal Code</label>
                <input type="text" class="form-control" name="postalCode" id="postalCode" placeholder="15963">
            </div>
            <div class="col-md-3">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" name="city" id="city" placeholder="Athens">
            </div>
            <div class="col-md-3">
                <label for="countrySelect" class="form-label">Country</label>
                <select class="form-select" id="countrySelect" placeholder="Greece">
                  
                </select>
              </div>
        </div>
        <div class="row mt-3">
          <div class="col-md-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="+30 6990011222">
          </div>
          <div class="col-md-3">
            <label for="birthDate" class="form-label">Date of Birth</label>
            <input type="date" name="birthDate" id="birthDate" class="form-control">
          </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-md-3">
                <button type="submit" id="registrationButton" class="btn btn-primary">Submit</button>
                <a role="button" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/">Cancel</a>
            </div>
            
        </div>
    </form>
  </div>

 
  <script src="${pageContext.request.contextPath}/static/js/registration.js?version=51" type="text/javascript"></script>
</body>
</html>