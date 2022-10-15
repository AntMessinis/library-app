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
	<div id="mainContainer" class="container-xxl">
	<c:choose>
		<c:when test="${listFound}">
		<h3 class="mt-5">Books borrowed by ${user.firstname}</h3>
        <table class="table table-hover">
            <thead>
            <tr>
            <th scope="col">Title</th>
            <th scope="col">ISBN</th>
            <th scope="col">Date Borrowed</th>
            <th scope="col">Date to return</th>
            <th scope="col">Returned</th>
            </tr>
        </thead>
        <tbody></tbody>
        <c:forEach var = "detail" items="${borrowList}">
            <tr>
            	<td>${detail.book.title}</td>
                <td>${detail.book.isbn}</td>
                <td>${detail.borrowDate}</td>
                <td>${detail.returnDate}</td>
                <td>
                	<c:choose>
                		<c:when test="${detail.returned}">
                			Yes
                		</c:when>
                		<c:otherwise>
                			No
                		</c:otherwise>
                	</c:choose>
                </td>
            </tr>
	    </c:forEach>
	    </table>
		</c:when>
		<c:otherwise>
			<p>No books found</p>
		</c:otherwise>
	</c:choose>
	</div>
	
</body>