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
    
    if ($.isEmptyObject(response)){
        $("#searchResults").html("<p>No book was found</p>")
    }else {
        let output = `<div class="row mb-5 align-items-center text-center">`;
        for (let book of books){
            output += `<div class="col-4 h-75 d-inline-block mt-5 align-items-center ">
            <h4>${book.title}</h4>` 
            +'<img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">'
            + ((book.copiesInLibrary > 0) ? `<p>Availiable for borrowing</p><div class="mt-3"><button id="bookDetails${book.id}" class="btn btn-primary">Details</button></div></div>` : `<p>Not Availiable for borrowing</p><div class="mt-3"><button class="btn btn-primary" disabled>Borrow</button></div></div>`);
        }
        output += "</div>";
        $("#searchResults").html(output);
        for (let book of books){
            $(`#bookDetails${book.id}`).on('click', function(){
                showDetails(book.id, book.title, book.isbn, book.author.firstname, book.author.lastname, book.description,book.category.categoryName, book.category.subcategoryName, book.language.languageName, book.copiesInLibrary);
            });
        }
    }
}


function APIError(){
    $("#searchResults").html("Something Went Wrong!")
}