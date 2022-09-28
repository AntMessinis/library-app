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
            output += `<div class="col-5 h-75 d-inline-block mt-5 align-items-center ">
            <h4>${book.title}</h4>` 
            +'<img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">'
            + ((book.copiesInLibrary > 0) ? '<p>Availiable for borrowing</p><div class="mt-3"><button class="btn btn-primary">Borrow</button></div></div>' : '<p>Not Availiable for borrowing</p></div>');
        }
        output += "</div>";
        $("#searchResults").html(output);
    }
}

function APIError(){
    $("#searchResults").html("Something went Wrong!");
}