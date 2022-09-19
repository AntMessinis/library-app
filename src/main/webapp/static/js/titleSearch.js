$(document).ready(function(){
    $('#titleSearchBtn').on('click', function(){
        
        getBookFromJSON($('#searchInput').val().trim())    
    })
});

function getBookFromJSON(bookTitle){
    
    let xhr = new XMLHttpRequest()

    xhr.open('GET',`/library-app/searchByTitle?title=${bookTitle}`, true)
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError()
    
    xhr.onreadystatechange = function(){
        
        if (xhr.readyState === 4){
            
            if (xhr.status === 200){
                handleBookTitleSearchResults(JSON.parse(xhr.responseText))
                
            } else {
                alert(xhr.status)
                APIError()
            }
        }
    }
    xhr.send()
}

function handleBookTitleSearchResults(response){
    let book = response;

    if (jQuery.isEmptyObject(response)){
        $(".searchResults").html("<p>No book was found</p>");
    } else {
        
        let output = `<h1 class="display-1">${book.title}</h1>
        <div class="row mt-1">
          <div class="col">
            <img src="..." alt="">
          </div>
          <div class="col">
            <h3 class="display-6"><b>Author</b>: ${book.author.lastname}, ${book.author.firstname}</h3>
            <h3 class="display-6"><b>ISBN</b>: ${book.isbn}</h3>
            <h3 class="display-6"><b>Category</b>: ${book.category.categoryName}, ${book.category.subcategoryName}</h3>
            <h3 class="display-6"><b>Language</b>: ${book.language.languageName}</h3>
            <h3 class="display-6"><b>Copies availiable for lending</b>: ${book.copiesInLibrary}</h3>
            <a type="button" class="btn btn-primary" href="">Request to borrow</a>
          </div>
        </div>
        <div class="row mt-1">
          <hr>
          <h1 class="display-1">Description</h1>
          <p class="mt-1">${book.description}</p>
        </div>`;

        $(".searchResults").html(output);
    }
}

function APIError(){
    $(".searchResults").html("Something Went Wrong!")
}