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
   <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="#">Home
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something else here</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Separated link</a>
          </div>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-sm-2" type="text" placeholder="Search">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
    <div class="container mt-5">
        <div class="row">
            <div class="col-6"> 
                <form class="form-control" action="${pageContext.request.contextPath}/new-country" method="post" >
                    <label class="form-label" for="countryName">Country</label>   
                    <input class="form-control" type="text" name="countryName" id="countryName" placeholder="Greece">   
                    <button type="submit" class="btn btn-primary mt-3 mb-3">Insert new Country</button>   
               </form>
               <c:if test="${insertSuccess}}">
                    <p class="display-6 mt-4 text-success">${infoSuccess}</p>
               </c:if>
               <c:if test="${insertError}">
                <p class="display-6 mt-4 text-danger">${infoError}</p>
               </c:if>
            </div>
        </div>
        
        
     
        <div class="row mt-5">
            <div class="col-6">
                <form action="${pageContext.request.contextPath}/list-countries" method="get">
                    <button type="submit" id="countriesList" class="btn btn-primary mt-3 mb3">Get list of Countries in database</button>
                </form>          
            </div>
            <div class="col- 6">
            <table>
 			<c:if test="${countryList}">
                        <c:forEach var="country" items="${countries}">
                            <tr>
                            	<td>
                                    ${country.id}
                                </td>
                                <td>
                                    ${country.name}
                                </td>
                            </tr>
                        </c:forEach>
				</c:if>
                </table>
                <c:if test="${noCountriesError}">
                	<p class="text-danger">${noListError}</p>
                </c:if>
                <c:if test="${listError}">
                    <p class="text-danger">${generalError}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>

