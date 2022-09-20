$(document).ready(function(){
    $("#isbnSearchBtn").on('click', function(){
        getIsbnSearchResultsFromJSON($("#searchInput").val().trim())
    })
});

function getIsbnSearchResultsFromJSON(bookIsbn){
    let xhr = new XMLHttpRequest();

    xhr.open('GET', `/library-app/searchByIsbn?isbn=${bookIsbn}`, true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError()

    xhr.onreadystatechange = function(){
        if (xhr.readyState === 4){
            if(xhr.status === 200){
                handleIsbnSearchResults(JSON.parse(xhr.responseText))
            }else{
                APIError()
            }
        }
    }
    xhr.send()
}

function handleIsbnSearchResults(response){
    let book = response;
    if($.isEmptyObject(response)){
        $("#searchResults").html("No book was found")
    }else{
        let output = `<h1 class="display-1">${book.title}</h1>
        <div class="row mt-5">
          <div class="col-7">
            <img src="../webapp/static/imgs.tumbnail.jpg" alt="">
          </div>
          <div class="col-5">
            <p class="display-6"><span class="fw-bold text-decoration-underline">Author:</span> ${book.author.lastname}, ${book.author.firstname}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">ISBN:</span> ${book.isbn}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Category:</span> ${book.category.categoryName}, ${book.category.subcategoryName}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Language:</span> ${book.language.languageName}</p>
            <p class="display-6"><span class="fw-bold text-decoration-underline">Copies availiable:</span> ${book.copiesInLibrary}</p>
            <a type="button" class="btn btn-primary" href="">Request to borrow</a>
          </div>
        </div>
        <div class="row mt-5">
          <hr>
          <h1 class="display-1">Description</h1>
          <p class="mt-5">${book.description}</p>
        </div>`;

        $("#searchResults").html(output);
    }
}

function APIError(){
    $("#searchResults").html("Something went wrong")
}