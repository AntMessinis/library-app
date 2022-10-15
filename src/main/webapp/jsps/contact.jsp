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