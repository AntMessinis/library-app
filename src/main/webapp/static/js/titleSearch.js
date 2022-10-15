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
                APIError()
            }
        }
    }
    xhr.send()
}

function handleBookTitleSearchResults(response){
    let book = response;
    if ($.isEmptyObject(response)){
        $("#searchResults").html("<p>No book was found</p>");
    } else {
        let output = `<h1 class="display-1">${book.title}</h1>
        <div class="row mt-5">
          <div class="col-7">
            <img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">
          </div>
          <div class="col-5">
            <p class="display-6"><span class="fw-bold text-decoration-underline">LibraryID:</span> ${book.id}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Author:</span> ${book.author.lastname}, ${book.author.firstname}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">ISBN:</span> ${book.isbn}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Category:</span> ${book.category.categoryName}, ${book.category.subcategoryName}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Language:</span> ${book.language.languageName}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Copies availiable:</span> ${book.copiesInLibrary}</p>
            <a type="button" class="btn btn-primary ${(book.copiesInLibrary > 0? "": "disabled")}" id="borrowButton">Request to borrow</a>
          </div>
        </div>
        <div class="row mt-5">
          <hr>
          <h1 class="display-1">Description</h1>
          <p class="mt-5">${book.description}</p>
        </div>`;

        $("#searchResults").html(output);
        $('#borrowButton').on('click', function(){
            borrowBook(book);
        });
    }
}

function borrowBook(book){
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'borrow', true);
    xhr.timeout = 10000;
    xhr.ontimeout = APIError();
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log('Status OK');
            }else{
                console.log('Status not OK');
            }
        }
    }
    let data = JSON.stringify({
        "id":book.id,
        "title":book.title,
        "isbn": book.isbn,
        "author": {
            "id": book.author.id,
            "firstname":book.author.firstname,
            "lastname": book.author.lastname,
            "countryOfOrigin": {
                "id": book.author.countryOfOrigin.id,
                "countryName": book.author.countryOfOrigin.countryName
            }
        },
        "language":{
            "id": book.language.id,
            "languageName": book.language.languageName
        },
        "category":{
            "id":book.category.id,
            "categoryName": book.category.categoryName,
            "subcategoryName": book.category.subcategoryName
        },
        "copiesInLibrary": book.copiesInLibrary,
        "currentlyBorrowed": book.currentlyBorrowed
    })
    xhr.send(data);
}

function APIError(){
    $("#searchResults").html("Something Went Wrong!")
}