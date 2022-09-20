$(document).ready(function(){
    
    $("#authorSearchBtn").on('click', function(){
        getBookListFromJSON($("#searchInput").val().trim())
    })
});

function getBookListFromJSON(author){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/searchByAuthor?author=${author}`, true)
    
    xhr.timeout = 20000;
    xhr.ontimeout = (e) => APIError()
    
    xhr.onreadystatechange = function(){
        
        if(xhr.readyState === 4){
            
            if(xhr.status === 200){
			
                handleBookAuthorSearchResults(JSON.parse(xhr.responseText))
                
            } else {
                APIError()
            }
        }
    }

    xhr.send();
}

function handleBookAuthorSearchResults(response){
    let books = response;
    
    if (books.length < 1){
        $("#searchResults").html("<p>No book was found</p>")
    }else {
        let output = "<div class=row>"
        for (var book of books){
            output += `<div class="col-6">
            <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
            <h5 class="card-title">${book.title}</h5>
                <p class="card-text">${book.description}</p>
            </div>
            </div>` +"<img src=\"${pageContext.request.contextPath}/static/imgs/tumbnail.jpg\" alt=\"changedAgain\"></div>";
        }
        output += "</div>"
        $("#searchResults").html(output)
    }
    
}

function APIError(){
    $("#searchResults").html("Something Went Wrong!")
}