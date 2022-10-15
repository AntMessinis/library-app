$(document).ready(function(){
    
    $("#categorySearchBtn").on("click", function(){
        getBooksFromJSON($("#searchInput").val().trim())
    })
});

function getBooksFromJSON(categoryName){
    //Send a request to the server
    let xhr = new XMLHttpRequest()
    xhr.open('GET', `/library-app/searchByCategory?category=${categoryName}`, true)

    //Define timeout duration and on timeout call api error function
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();


    xhr.onreadystatechange = function(){
        //If the server's response is success, handle the json
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                handleSearchResults(JSON.parse(xhr.responseText));
            } else{
                APIError();
            }
        }
    }
    xhr.send();
}

function handleSearchResults(response){
    let books = response;
    if($.isEmptyObject(response)){
        $("#searchResults").html("<p>No books was found</p>");
    } else {
        let output = `<div class="row mb-5 align-items-center text-center">`;
        for (let book of books){
            output += `<div class="col-4 h-75 d-inline-block mt-5 align-items-center ">
            <h4>${book.title}</h4>` 
            +'<img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">'
            + ((book.copiesInLibrary > 0) ? `<p>Availiable for borrowing</p><div class="mt-3"><button id="bookDetails${book.id}" class="btn btn-primary">Details</button></div></div>`: '<p>Not Availiable for borrowing</p><div class="mt-3"><button class="btn btn-primary" disabled>Details</button></div></div>');
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

function showDetails(id, title, isbn, authorFirstname, authorLastname, description ,categoryName, subcategoryName, language, copiesInLibrary){
    let output = `<h1 class="display-1">${title}</h1>
        <div class="row mt-5">
          <div class="col-7">
            <img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">
          </div>
          <div class="col-5">
            <p class="display-6"><span class="fw-bold text-decoration-underline">LibraryID:</span> ${id}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Author:</span> ${authorLastname}, ${authorFirstname}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">ISBN:</span> ${isbn}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Category:</span> ${categoryName}, ${subcategoryName}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Language:</span> ${language}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Copies availiable:</span> ${copiesInLibrary}</p>
            <a type="button" class="btn btn-primary ${(copiesInLibrary > 0? "": "disabled")}" id="borrowButton">Request to borrow</a>
          </div>
        </div>
        <div class="row mt-5">
          <hr>
          <h1 class="display-1">Description</h1>
          <p class="mt-5">${description}</p>
        </div>`;

        $("#searchResults").html(output);
        $('#borrowButton').on('click', function(){
            borrowBook(book);
        });
}

function APIError(){
    $("#searchResults").html("Something went Wrong!");
}